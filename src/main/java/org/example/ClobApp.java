package org.example;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClobApp {
    public static void main(String[] args) {

        File file = new File("cities.txt");

        if (!file.exists()) {
            System.err.println("File not found: " + file.getAbsolutePath());
            return;
        }

        String sql = "INSERT INTO cities(`name`,`history`) VALUES (?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);

             FileReader fileReader = new FileReader(file)) {

            preparedStatement.setString(1, "bangalore");

            preparedStatement.setCharacterStream(2,fileReader);

            System.out.println("Inserting image into database: " + file.getAbsolutePath());
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Number of rows affected: " + rowsAffected);

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.err.println("File error: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
