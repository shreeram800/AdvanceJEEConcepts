package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BlobOperation {
    public static void main(String[] args) {

        File file = new File("src/main/java/org/example/image.jpg");

        if (!file.exists()) {
            System.err.println("File not found: " + file.getAbsolutePath());
            return;
        }

        String sql = "INSERT INTO person(name, image) VALUES (?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             FileInputStream fileInputStream = new FileInputStream(file)) {

            preparedStatement.setString(1, "shreeram");

            preparedStatement.setBlob(2, fileInputStream);

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
