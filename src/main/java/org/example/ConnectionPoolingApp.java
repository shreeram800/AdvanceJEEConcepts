package org.example;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionPoolingApp {

    public static void main(String[] args) {
        MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
        ds.setUrl("jdbc:mysql://localhost:3306/enterprisejava");
        ds.setUser("root");
        ds.setPassword("shreeram");

        try {
            Connection connection=ds.getConnection();
            Statement statement=connection.createStatement();

            ResultSet resultSet=statement.executeQuery("select emp_id, name, age from employee");
            System.out.println("Sid/tsnmae/tsaddress");

            while (resultSet.next()) {
                int id=resultSet.getInt("emp_id");
                String name=resultSet.getString("name");
                int  age=resultSet.getInt("age");
                System.out.println(id+" "+name+" "+age);
            }
            //sending the connection to connection pool
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
