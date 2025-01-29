package org.example;


import com.mysql.cj.jdbc.Driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBConnection {

    public static Connection getConnection() throws SQLException, IOException {

        Driver driver= new Driver();//creating driver of mysql dbz

        Properties prop = new Properties();

        DriverManager.registerDriver(driver);

        System.out.println("Driver Registered!");

        FileInputStream outputStream = new FileInputStream("C:\\Users\\shriram\\TestBootcamp\\src\\main\\resources\\jdbc.properties");
        prop.load(outputStream);

        String url = prop.getProperty("url");
        String user = prop.getProperty("user");
        String password = prop.getProperty("password");


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