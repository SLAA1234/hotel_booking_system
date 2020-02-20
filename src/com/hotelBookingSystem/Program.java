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
            currentAdmin = adminLogin();//sometimes, even admin has login, still ask username and password? need fix
            if(currentAdmin == null){
                adminLogin();
            }
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
            case 5:
                System.exit(0);
            default:
                System.out.println("You must choose a number between 1-5.");
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
        System.out.println("Input first name: ");
        String person_first_name = scanner.nextLine();
        System.out.println("Input last name: ");
        String person_last_name= scanner.nextLine();
        System.out.println("Input pass number: ");
        String person_pass = scanner.nextLine();
        System.out.println("Input country of customer: ");
        String person_country = scanner.nextLine();
        System.out.println("Input email of customer: ");
        String person_email = scanner.nextLine();
        System.out.println("Input telephone: ");//number save as varchar
        String person_telephone =  scanner.nextLine();

        try{
            statement = conn.prepareStatement("INSERT INTO persons SET person_first_name = ?, person_last_name = ?, person_pass = ?, person_country = ?, person_email = ?, person_telephone = ?;");
            statement.setString(1,person_first_name);
            statement.setString(2,person_last_name);
            statement.setString(3,person_pass);
            statement.setString(4,person_country);
            statement.setString(5,person_email);
            statement.setString(6,person_telephone);
            statement.executeUpdate();
            System.out.println(person_first_name + " " + person_last_name + " has been successfully registered as customer.");

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    private void printAdminMenu(){
        System.out.println("Choose option:");
        System.out.println("1.Register a customer.");
        System.out.println("2.Search available room.");
        System.out.println("3.Cancel reservation.");
        System.out.println("4.Change reservation.");
        System.out.println("5.Exit.");
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
                    while (resultSet.next()) {
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
