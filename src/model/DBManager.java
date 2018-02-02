package model;

import java.sql.*;

/**
 * Copyright (c) Anton on 18.01.2018.
 */
public class DBManager {

    public void saveAgent(FBIAgent agent) throws Exception {
        Connection connection = getConnection();
        String sql = "INSERT INTO agent(\n" +
                "             surname, \"name\", sex, nickname, physicalpower, mentalstrength, \n" +
                "            patriotism, status, image)\n" +
                "    VALUES (?, ?, ?, ?, ?, ?, \n" +
                "            ?, ?::agentstatus, ?)";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1, agent.getSurname());
        st.setString(2, agent.getName());
        st.setBoolean(3, agent.getSex());
        st.setString(4, agent.getNickname());
        st.setBoolean(5, agent.isPhysicalPower());
        st.setBoolean(6, agent.isMentalStrength());
        st.setBoolean(7, agent.isPatriotism());
        st.setString(8, agent.getStatus().toString());
        st.setBytes(9, agent.getImage());

        st.executeUpdate();
        closeConnection(connection);
    }

    private void closeConnection(Connection connection) throws SQLException {
        connection.close();
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

            return fbiAgent;

        }
        return null;
    }


    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/agents", "postgres", "postgrespass");
        return connection;
    }
}
