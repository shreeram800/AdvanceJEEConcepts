package org.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TimeTest {

    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);

        String name;
        try {
            Connection connection=DBConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("insert into user values(?,?)");
            System.out.println("Enter the name of the user");
            name = sc.nextLine();

            System.out.println("Enter date in dd-MM-yyyy format :: ");

            String inputDate=sc.nextLine();

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

            Date date = sdf.parse(inputDate);

            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            preparedStatement.setString(1, name);

            preparedStatement.setDate(2,sqlDate);
            System.out.println("No of lines affected: "+ preparedStatement.executeUpdate());
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            sc.close();
        }



    }
}
