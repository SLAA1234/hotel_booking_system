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

        while (true) {//if invalid admin, how to quit normally.
            currentAdmin = adminLogin();
            if (currentAdmin != null) {
                adminOperate(currentAdmin);
            }
        }
    }

    private void adminOperate(Admin currentAdmin){
        while(true) {
            System.out.println("Choose option:");
            System.out.println("1.Register a customer.");
            System.out.println("2.Search available room.");
            System.out.println("3.Cancel reservation.");
            System.out.println("4.Change reservation.");
            System.out.println("5.Exit.");
            int choice = 999;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("You must select a number.");
            }

            switch (choice) {
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
    }

    private void changeReservation(){
        System.out.println("You want to change customer reservation: ");
        System.out.println("Please input the reference of the reservation: ");
        String reservation_reference = scanner.nextLine();
        try{
            statement = conn.prepareStatement("SELECT total_person, person_over_12, check_in, check_out, price_total, reservations.extra_bed, extra_bed_price, hotel_name, meal_type, price_meal_per_person, room_price_per_day, rooms.extra_bed_availability, max_persons, room_type.room_type, room_number\n" +
                    "\tFROM reservations \n" +
                    "\t\tinner JOIN hotel_meal_choice\n" +
                    "\t\t\tON reservations.meal_choice_id = hotel_meal_choice.meal_choice_id\n" +
                    "\t\t\t\tINNER JOIN rooms\n" +
                    "\t\t\t\t\tON reservations.room_id = rooms.room_id\n" +
                    "\t\t\t\t\t\tINNER JOIN room_type\n" +
                    "\t\t\t\t\t\t\tON rooms.room_type_id = room_type.room_type_id\n" +
                    "\t\t\t\t\t\t\t\tINNER JOIN hotels\n" +
                    "\t\t\t\t\t\t\t\t\tON hotels.hotel_id = reservations.hotel_id WHERE reservation_reference = ?");
            statement.setString(1, reservation_reference);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                String total_person = result.getObject(1).toString();
                String person_over_12 = result.getObject(2).toString();
                String check_in = result.getObject(3).toString();
                String check_out = result.getObject(4).toString();
                String price_total = result.getObject(5).toString();
                String extra_bed = result.getObject(6).toString();
                String extra_bed_price = result.getObject(7).toString();
                String hotel_name = result.getObject(8).toString();
                String meal_type = result.getObject(9).toString();
                String price_meal_per_person = result.getObject(10).toString();
                String room_price_per_day = result.getObject(11).toString();
                String extra_bed_availability = result.getObject(12).toString();
                String max_person = result.getObject(13).toString();
                String room_type = result.getObject(14).toString();
                String room_number = result.getObject(15).toString();
                
                System.out.println("hotel name: " + hotel_name + ". room number: " + room_number + ". room type: " + room_type + ". room price per day: " + room_price_per_day + ".");
                System.out.println("total person: " + total_person + ". person over 12: " + person_over_12 + ". check in: " + check_in + ". check out: "+ check_out + ".");
                System.out.println("extra bed: "+ extra_bed + ". extra bed price: " + extra_bed_price + ". meal type: "+ meal_type + ". meal price per person: " + price_meal_per_person + ".");
                System.out.println("extra bed availability: " + extra_bed_availability + ". max person: " + max_person + ".");
                System.out.println("total price: " + price_total + ".");
                System.out.println("I have found your reservation. What do you want to change?");
                changeMadeByUser();
                conn.close();
            } else {
                System.out.println("Couldn't find your reservation.");
            }
        }catch (SQLException ex){
            ex.getStackTrace();
        }
    }

    private void changeMadeByUser(){
        while(true) {
            System.out.println("1.change check in date.");
            System.out.println("2.change check out date.");
            System.out.println("3.change number of persons.");
            System.out.println("4.change number of persons over 12 years old.");
            System.out.println("5.add extra bed.");
            System.out.println("6.remove extra bed.");
            System.out.println("7.change meal choice.");
            System.out.println("8.change room.");
            System.out.println("9.Exit.");
            int choice = 999;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("You must select a number.");
            }

            switch (choice) {
                case 1:
                    changeCheckInDate();
                    break;
                case 2:
                    changeCheckOutDate();
                    break;
                case 3:
                    changeTotalPersons();
                    break;
                case 4:
                    changePersonsOver12();
                    break;
                case 5:
                    addExtraBed();
                    break;
                case 6:
                    removeExtraBed();
                    break;
                case 7:
                    changeMeal();
                    break;
                case 8:
                    searchRoom();
                    break;
                case 9:
                    System.exit(0);
                default:
                    System.out.println("You must choose a number between 1-9.");
            }
        }
    }

    private void searchRoom() {
    }

    private void changeMeal() {
    }

    private void removeExtraBed() {
    }

    private void addExtraBed() {
    }

    private void changePersonsOver12() {
    }

    private void changeTotalPersons() {
    }

    private void changeCheckOutDate() {
    }

    private void changeCheckInDate() {
    }


    private void cancelReservation() {
        System.out.println("You want to cancel a reservation: ");
        System.out.println("Please input the reference of the reservation: ");
        String reservation_reference = scanner.nextLine();
        try{
            statement = conn.prepareStatement("DELETE FROM reservations WHERE reservation_reference = ?");
            statement.setString(1, reservation_reference);
            int rows = statement.executeUpdate();
            if (rows>0) {
                System.out.println("The reservation has been cancelled.");
                conn.close();
            } else{
            System.out.println("This reservation doesn't exit.");}
        }catch (SQLException ex){
            ex.getStackTrace();
        }
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
