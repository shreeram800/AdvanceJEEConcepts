package org.example;


import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class DBConnection {

    public static Connection getConnection() throws SQLException {

        Driver driver= new Driver();//creating driver of mysql dbz

        DriverManager.registerDriver(driver);

        System.out.println("Driver Registered!");

        String url = "jdbc:mysql://localhost:3306/enterprisejava";

        String user = "root";

        String password = "shreeram";

        Connection connection = DriverManager.getConnection(url, user, password);

        System.out.println("Connection Registered!" + connection);

        System.out.println("Implementation class name: " + connection.getClass().getName());

        return connection;

    }

    public static void closeConnection(ResultSet resultSet,Statement statement,Connection connection) throws SQLException {

        if (resultSet != null) {
            resultSet.close();
        }

        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

}