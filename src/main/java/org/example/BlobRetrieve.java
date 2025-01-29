package org.example;

import java.io.*;
import java.sql.*;

public class BlobRetrieve {
    public static void main(String[] args) {
        String sql = "SELECT name, image FROM person WHERE name = ?";
        String outputFilePath = "download.jpg";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Set parameter for the query
            preparedStatement.setString(1, "shreeram");

            // Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Retrieve data
                    String name = resultSet.getString(1);
                    InputStream inputStream = resultSet.getBinaryStream(2);

                    try (FileOutputStream fos = new FileOutputStream(outputFilePath)) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            fos.write(buffer, 0, bytesRead);
                        }
                    }

                    System.out.println("Image retrieved for user: " + name);
                    System.out.println("Image saved to: " + outputFilePath);
                } else {
                    System.out.println("Record not found for the specified name.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("File I/O error: " + e.getMessage());
        }
    }
}
