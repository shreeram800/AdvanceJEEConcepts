package org.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class TransationApp {
    public static void main(String[] args) {
        try {
            Connection connection= DBConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
