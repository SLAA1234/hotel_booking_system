package com.hotelBookingSystem;

import java.sql.*;
import java.util.Scanner;

public class Program {

    Connection conn = null;
    PreparedStatement statement;
    private Admin currentAdmin;
    Scanner scanner = new Scanner(System.in);

    public void start() {
        connectToDb();
        while (true) {
            currentAdmin = adminLogin();
            if (currentAdmin != null) {
                adminOperate(currentAdmin);
            }
        }
    }

    private void adminOperate(Admin currentAdmin){
        printAdminMenu();
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice){
            case 1:
                createNewCustomer();
                break;
            case 2:
                searchAvailableRoom();
                break;
            case 3:
                cancelReservation();
                break;
            case 4:
                changeReservation();
                break;
            default:
                System.out.println("You must choose a number between 1-4.");
        }

    }

    private void changeReservation() {
    }

    private void cancelReservation() {
    }

    private void searchAvailableRoom() {
    }

    private void createNewCustomer() {
        System.out.println("You want to register a customer.");

    }

    private void printAdminMenu(){
        System.out.println("Choose option:");
        System.out.println("1.Register a customer.");
        System.out.println("2.Search available room.");
        System.out.println("3.Cancel reservation.");
        System.out.println("4.Change reservation.");
    }
    private Admin adminLogin() {
        System.out.println("Admin, please input your username: ");
        String username = scanner.nextLine();
        System.out.println("please input your password: ");
        char[] password = scanner.nextLine().toCharArray();
        try {
            statement = conn.prepareStatement("SELECT * FROM persons WHERE username = ? and password = ? And admin = 'Y';");
            statement.setString(1, username);
            statement.setString(2, String.valueOf(password));
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                System.out.println("admin, you are successfully log in!");
                Statement stmt = conn.createStatement();

                ResultSet resultSet = stmt.executeQuery("SELECT person_first_name, person_last_name FROM persons WHERE admin = 'Y'");
                while(resultSet.next()){
                    String person_first_name = resultSet.getString("person_first_name");
                    String person_last_name = resultSet.getString("person_last_name");
                    String name = person_first_name + " " + person_last_name;
                    Admin admin = new Admin(name);
                    return admin;
                }
                    conn.close();
            } else {
                System.out.println("wrong username or password.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }











    private void connectToDb(){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/hotel_booking_system?user=root&password=mysql&serverTimezone=UTC");
        } catch (Exception ex) { ex.printStackTrace(); }
    }

}
