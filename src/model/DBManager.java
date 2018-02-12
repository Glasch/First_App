package model;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * Copyright (c) Anton on 18.01.2018.
 */
public class DBManager {

    public void saveAgent(FBIAgent agent) throws Exception {
        Connection connection = getConnection();
        String sql = "INSERT INTO agent(\n" +
                "             surname, \"name\", sex, nickname, physicalpower, mentalstrength, \n" +
                "            patriotism, status, image, other_comments)\n" +
                "    VALUES (?, ?, ?, ?, ?, ?, \n" +
                "            ?, ?::agentstatus, ?, ?)";
        PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        st.setString(1, agent.getSurname());
        st.setString(2, agent.getName());
        st.setBoolean(3, agent.getSex());
        st.setString(4, agent.getNickname());
        st.setBoolean(5, agent.isPhysicalPower());
        st.setBoolean(6, agent.isMentalStrength());
        st.setBoolean(7, agent.isPatriotism());
        st.setString(8, agent.getStatus().toString());
        st.setBytes(9, agent.getImage());
        st.setString(10,agent.getOtherComments());

        st.executeUpdate();
        ResultSet keys = st.getGeneratedKeys();
        keys.next();
        int agentId = keys.getInt(1);

        for (FBIAgentPreviousTask previousTask : agent.getPreviousTasks()) {
            saveAgentPreviousTask(connection,previousTask,agentId);
        }


        closeConnection(connection);
    }

    private void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }

    public void saveAgentPreviousTask(Connection connection, FBIAgentPreviousTask previousTask, int agentID) throws Exception {
        String sql = "INSERT INTO previous_tasks(agent_id, start_date, end_date, city) VALUES ( ?, ?, ?, ?)";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1,agentID);
        st.setDate(2, Date.valueOf(previousTask.getStartDate()));
        st.setDate(3, Date.valueOf(previousTask.getEndDate()));
        st.setObject(4,previousTask.getCity());

        st.executeUpdate();


    }


    public FBIAgent loadAgent(String surname) throws Exception {


        Connection connection = getConnection();
        String sql = "SELECT * FROM agent where surname = ?";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1,surname);
        ResultSet rs = st.executeQuery();


        if (rs.next()) {
            FBIAgent fbiAgent = new FBIAgent();
            fbiAgent.setId(rs.getInt("id"));
            fbiAgent.setSurname(rs.getString("surname"));
            fbiAgent.setName(rs.getString("name"));
            fbiAgent.setSex(rs.getBoolean("sex"));
            fbiAgent.setPhysicalPower(rs.getBoolean("physicalpower"));
            fbiAgent.setMentalStrength(rs.getBoolean("mentalstrength"));
            fbiAgent.setPatriotism(rs.getBoolean("patriotism"));
            String status = rs.getString("status");
            fbiAgent.setStatus(FBIAgentStatus.valueOf(status));
            fbiAgent.setImage(rs.getBytes("image"));
            loadPreviousTasks(connection,fbiAgent);
            fbiAgent.setOtherComments(rs.getString("other_comments"));
            connection.close();
            return fbiAgent;
        }
        connection.close();
        return null;
    }


    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/agents", "postgres", "postgrespass");
        return connection;
    }

    public void loadPreviousTasks (Connection connection, FBIAgent fbiAgent) throws Exception{

        String sql = "SELECT id, start_date, end_date, city" +
                " FROM previous_tasks WHERE agent_id = ? ";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1,fbiAgent.getId());
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            FBIAgentPreviousTask fbiAgentPreviousTask = new FBIAgentPreviousTask();
            fbiAgentPreviousTask.setId(rs.getInt("id"));
            fbiAgentPreviousTask.setStartDate(rs.getDate("start_date").toLocalDate());
            fbiAgentPreviousTask.setEndDate(rs.getDate("end_date").toLocalDate());
            fbiAgentPreviousTask.setCity(rs.getString("city"));
            fbiAgentPreviousTask.setFbiAgent(fbiAgent);
            fbiAgent.getPreviousTasks().add(fbiAgentPreviousTask);

        }



    }

}
