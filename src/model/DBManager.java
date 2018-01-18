package model;

import java.sql.*;

/**
 * Copyright (c) Anton on 18.01.2018.
 */
public class DBManager {

    public void saveAgent(FBIAgent agent) throws Exception{
        Connection connection = getConnection();
        String sql = "INSERT INTO AGENT (Surname) values (?)";
        PreparedStatement st = connection.prepareStatement(sql);
        st.setString(1,agent.getSurname());
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
