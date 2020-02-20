package com.hotelBookingSystem;

import org.w3c.dom.ls.LSOutput;

import java.sql.*;
import java.util.Scanner;

public class Program {

    Connection conn = null;
    PreparedStatement statement;
    Scanner scanner = new Scanner(System.in);

    public Program(){
        connectToDb();
        adminLogin();
    }

    private void adminLogin(){
        System.out.println("Admin, please input your username: ");
        String username=scanner.nextLine();
        System.out.println("please input your password: ");
        char[] password = scanner.nextLine().toCharArray();
        try{
            statement = conn.prepareStatement("SELECT * FROM persons WHERE username = ? and password = ? And admin = 'Y';");
            statement.setString(1,username);
            statement.setString(2, String.valueOf(password));
            ResultSet result = statement.executeQuery();
            if(!result.next()){
                System.out.println("wrong username or password.");
            }
            else {
                System.out.println("admin, you are successfully log in!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

















    private void connectToDb(){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/hotel_booking_system?user=root&password=mysql&serverTimezone=UTC");
        } catch (Exception ex) { ex.printStackTrace(); }
    }

}
