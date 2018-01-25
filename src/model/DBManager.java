package model;

import java.sql.*;

/**
 * Copyright (c) Anton on 18.01.2018.
 */
public class DBManager {

    public void saveAgent(FBIAgent agent) throws Exception{
        Connection connection = getConnection();
        String sql = "INSERT INTO agent(\n" +
                "            surname, \"name\", sex, nickname, physicalpower, mentalstrength, \n" +
                "            patriotism)\n" +
                "    VALUES (?, ?, ?, ?, ?, ?, \n" +
                "            ?);";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1,agent.getSurname());
        st.setString(2,agent.getName());
        st.setBoolean(3, agent.getSex());
        st.setString(4,agent.getNickname());
        st.setBoolean(5,agent.isPhysicalPower());
        st.setBoolean(6,agent.isMentalStrength());
        st.setBoolean(7,agent.isPatriotism());
     //   st.setObject(9, agent.getStatus());
     //   st.setBinaryStream(10,agent.getImage());



        st.executeUpdate();
        closeConnection(connection);
    }

    private void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/agents", "postgres", "postgrespass");
        return connection;
    }
}
