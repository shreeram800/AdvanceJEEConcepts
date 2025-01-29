package org.example;

import java.io.*;
import java.sql.*;

public class ClobRetrieve {
    public static void main(String[] args) {
        String sql = "SELECT name, history FROM cities WHERE name = ?";
        String outputFilePath = "history.txt";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Set query parameter
            preparedStatement.setString(1, "bangalore");

            // Execute query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString(1);
                    try (Reader reader = resultSet.getCharacterStream(2);
                         FileWriter fileWriter = new FileWriter(outputFilePath)) {

                        // Read CLOB data and write to file
                        char[] buffer = new char[1024]; // 1 KB buffer

                        int bytesRead;
                        while ((bytesRead = reader.read(buffer)) != -1) {
                            fileWriter.write(buffer, 0, bytesRead);
                        }

                        System.out.println("History retrieved for city: " + name);
                        System.out.println("History saved to: " + outputFilePath);
                    }
                } else {
                    System.out.println("Record not found for the specified city.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("File I/O error: " + e.getMessage());
        }
    }
}
