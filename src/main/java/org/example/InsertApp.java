package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            // Prompt for employee ID
            System.out.println("Enter the ID of your Employee:");
            int id = sc.nextInt();

            // Correct SQL query
            String sqlQuery = "SELECT name, age, salary, father_name, role FROM Employee WHERE emp_id = ?";

            DBConnection db = new DBConnection();
            // Establish connection and prepare statement
            conn = db.getConnection();
            if (conn != null) {
                pst = conn.prepareStatement(sqlQuery);
                if (pst != null) {
                    pst.setInt(1, id);

                    // Execute the query
                    rs = pst.executeQuery();
                    if (rs != null) {
                        System.out.println("Name | Age | Salary | Father Name | Role");
                        if (rs.next()) {
                            String name = rs.getString("name");
                            int age = rs.getInt("age");
                            double salary = rs.getDouble("salary");
                            String fatherName = rs.getString("father_name");
                            String role = rs.getString("role");

                            System.out.println(name + " | " + age + " | " + salary + " | " + fatherName + " | " + role);
                        } else {
                            System.out.println("No record found for Employee ID: " + id);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                DBConnection.closeConnection(null, pst, conn);
                sc.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
