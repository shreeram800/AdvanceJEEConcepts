package org.example;

import java.io.IOException;
import java.sql.*;

public class StoredProcedureExample {
    public static void main(String[] args) {
        Connection conn = null;
        CallableStatement cs = null;

        try {
            conn = DBConnection.getConnection();

            // Updated to match the procedure with 1 input and 3 output parameters
            String storedProcedureName = "{call getEmployee(?, ?, ?, ?)}";

            if (conn != null) {
                cs = conn.prepareCall(storedProcedureName);

                // Set input parameter
                cs.setInt(1, 1); // emp_id

                // Register output parameters
                cs.registerOutParameter(2, Types.VARCHAR);
                cs.registerOutParameter(3, Types.VARCHAR);
                cs.registerOutParameter(4, Types.INTEGER);

                // Execute the stored procedure
                cs.execute();

                // Retrieve and print the output values
                System.out.println("First Name: " + cs.getString(2));
                System.out.println("Last Name: " + cs.getString(3));
                System.out.println("Age: " + cs.getInt(4));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                DBConnection.closeConnection(null, cs, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
