package com.hotelBookingSystem;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Program {

    Connection conn = null;
    PreparedStatement statement;
    private Admin currentAdmin;
    DbQuery dbQuery;


    Scanner scanner = new Scanner(System.in);

    public Program() throws SQLException {
    }

    public void start() throws SQLException {
        connectToDb();

        while (true) {//if invalid admin, how to quit normally.
            currentAdmin = adminLogin();
            if (currentAdmin != null) {
                adminOperate(currentAdmin);
            }
        }
    }

    private void adminOperate(Admin currentAdmin) throws SQLException {
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
                    Reservation currentReservation = findReservation();
                    if(currentReservation!=null){
                    changeReservation(currentReservation);}
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("You must choose a number between 1-5.");
            }
        }
    }


    private void test(){
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
                int total_person = (int) result.getObject(1);
                int person_over_12 = (int) result.getObject(2);
                String check_in = result.getObject(3).toString();
                String check_out = result.getObject(4).toString();
                int price_total = (int) result.getObject(5);
                int extra_bed = (int) result.getObject(6);
                int extra_bed_price = (int) result.getObject(7);
                String hotel_name = result.getObject(8).toString();
                String meal_type = result.getObject(9).toString();
                int price_meal_per_person = (int) result.getObject(10);
                int room_price_per_day = (int) result.getObject(11);
                int extra_bed_availability = (int) result.getObject(12);
                int max_person = (int) result.getObject(13);
                String room_type = result.getObject(14).toString();
                int room_number = (int) result.getObject(15);
                
                System.out.println("hotel name: " + hotel_name + ". room number: " + room_number + ". room type: " + room_type + ". room price per day: " + room_price_per_day + ".");
                System.out.println("total person: " + total_person + ". person over 12: " + person_over_12 + ". check in: " + check_in + ". check out: "+ check_out + ".");
                System.out.println("extra bed: "+ extra_bed + ". extra bed price: " + extra_bed_price + ". meal type: "+ meal_type + ". meal price per person: " + price_meal_per_person + ".");
                System.out.println("extra bed availability: " + extra_bed_availability + ". max person: " + max_person + ".");
                System.out.println("total price: " + price_total + ".");
                System.out.println("I have found your reservation. What do you want to change?");

            } else {
                System.out.println("Couldn't find your reservation.");
            }
        }catch (SQLException ex){
            ex.getStackTrace();
        }
    }

    private void changeReservation(Reservation currentReservation) throws SQLException {
            while (true) {
                System.out.println("You want to change customer reservation. What do you want to change? ");
                System.out.println("1.change check in date.");
                System.out.println("2.change check out date.");
                System.out.println("3.change number of total persons/persons over 12 years old.");
                System.out.println("4.change extra bed status.");
                System.out.println("5.change meal choice.");
                System.out.println("6.change room.");
                System.out.println("7.Exit.");
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
                        if(currentReservation!=null){
                            changePersons(currentReservation);
                        }
                        break;
                    case 4:
                        if(currentReservation!=null){
                            changeExtraBed(currentReservation);
                        }
                        break;
                    case 5:
                        if(currentReservation!=null) {
                            changeMeal(currentReservation);
                        }

                        break;
                    case 6:
                        searchRoom();
                        break;
                    case 7:
                        System.exit(0);
                    default:
                        System.out.println("You must choose a number between 1-9.");
                }
            }
        }


    private void searchRoom() {
    }
//what if create a method calculate total price seperately.
    private void changeMeal(Reservation currentReservation) {
        showHotelMealChoice(currentReservation);
        System.out.println("input the meal choice id: ");//try catch here, must select no shows between. it can choose
        //other number and it changes in database.
        int new_meal_choice_id = Integer.parseInt(scanner.nextLine());

        try {
            statement = conn.prepareStatement(" UPDATE reservations SET meal_choice_id = ? WHERE reservation_reference = ?;");
            statement.setInt(1, new_meal_choice_id);
            statement.setString(2, currentReservation.reservation_reference);
            statement.executeUpdate();
                }
                catch (SQLException ex){
                    ex.printStackTrace();
                }

        //how to print new currentreservation status? fix it
        // find new meal price and meal_type by meal id. calculate total price.
    }



    private void showHotelMealChoice(Reservation currentReservation) {
        System.out.println("The hotel has below meal choice: ");
        try {
            statement = conn.prepareStatement(" SELECT meal_choice_id, meal_type, price_meal_per_person FROM hotel_meal_choice\n" +
                    "\t\tWHERE hotel_id = (SELECT hotel_id FROM reservations\n" +
                    "\tWHERE reservation_reference = ?);\t\t\n");
            statement.setString(1, currentReservation.reservation_reference);
            ArrayList<Meal_choice> hotelMealChoiceList = new ArrayList<>();
            ResultSet result = statement.executeQuery();
            while (result.next()) {

                int meal_choice_id = Integer.parseInt(result.getObject(1).toString());
                String meal_type = (String) result.getObject(2);
                int price_meal_per_person = (int) result.getObject(3);

                Meal_choice meal_choice = new Meal_choice(meal_choice_id, meal_type, price_meal_per_person);
                hotelMealChoiceList.add(meal_choice);
            }
            for (int i = 0; i < hotelMealChoiceList.size(); i++) {
                System.out.println(hotelMealChoiceList.get(i));
            }
        }catch (SQLException ex){
            ex.getStackTrace();
        }
    }

    private void changeExtraBed(Reservation currentReservation) {
        System.out.println("Your current extra bed status is: " + currentReservation.extra_bed + ". I will help you change it.");
        
       if (currentReservation.extra_bed_availability == 1 && currentReservation.extra_bed == 1) {
            try {
                statement = conn.prepareStatement(" update reservations SET extra_bed = ? Where reservation_reference = ?;");
                statement.setInt(1, 0);
                statement.setString(2, currentReservation.reservation_reference);
                statement.executeUpdate();
                System.out.println("Now your extra bed status is: 0. Your new reservation details: ");
                currentReservation.extra_bed = 0;
                System.out.println(currentReservation);

            } catch (SQLException ex) {
                ex.getStackTrace();
            }
        }
        else if (currentReservation.extra_bed_availability==1 && currentReservation.extra_bed==0) {
           try {
               statement = conn.prepareStatement(" update reservations SET extra_bed = ? Where reservation_reference = ?;");
               statement.setInt(1, 1);
               statement.setString(2, currentReservation.reservation_reference);
               statement.executeUpdate();
               System.out.println("Now your extra bed status is: 1. Your new reservation details: ");
               currentReservation.extra_bed = 1;
               System.out.println(currentReservation);// total_price should change. fix it.

           } catch (SQLException ex) {
               ex.getStackTrace();
           }
       }
        else{
            System.out.println("Oh! This room doesn't allow to add extra bed.");
            }
        }




    private void changePersons(Reservation currentReservation) {
        System.out.println("How many guests over 12 years old will come? ");
        int new_total_person_over_12 = Integer.parseInt(scanner.nextLine());
        System.out.println("How many guests in total will come? ");
        int new_total_person = Integer.parseInt(scanner.nextLine());

        if(currentReservation!=null && currentReservation.max_person>=new_total_person) {
            try {
                statement = conn.prepareStatement(" update reservations SET person_over_12 = ?, total_person = ? Where reservation_reference = ?;");
                statement.setInt(1, new_total_person_over_12);
                statement.setInt(2, new_total_person);
                statement.setString(3, currentReservation.reservation_reference);
                statement.executeUpdate();
                System.out.println("The guests number has been changed. The new reservation details: ");
                currentReservation.person_over_12 = new_total_person_over_12;
                currentReservation.total_person = new_total_person;
                System.out.println(currentReservation);//total_price should fix.


            }catch (Exception ex){
                ex.printStackTrace();
            }
        }else{
            System.out.println("The room has reached its max capacity.");
        }
    }


    private void changeCheckOutDate() {
    }

    private void changeCheckInDate() {
        System.out.println("When is the new check in date?");
        String newCheckIn = scanner.nextLine();//change to date form
    }

    private String checkReservationReference(String checkReference) {
            try {
                statement = conn.prepareStatement("SELECT * FROM reservations WHERE reservation_reference = ? ;");
                statement.setString(1, checkReference);
                ResultSet result = statement.executeQuery();
                if (result.next()) {
                    System.out.println("There is a reservation with this reservation reference.");
                    return checkReference;
                } else {
                    System.out.println("No such reservation.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }


    private Reservation findReservation(){
        System.out.println("Please input the reference of the reservation: ");
        String checkReference = checkReservationReference(scanner.nextLine());
        if(checkReference!=null){
           try {
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
               statement.setString(1, checkReference);
               ResultSet result = statement.executeQuery();
               if (result.next()) {
                   int total_person = (int) result.getObject(1);
                   int person_over_12 = (int) result.getObject(2);
                   String check_in = result.getObject(3).toString();
                   String check_out = result.getObject(4).toString();
                   int price_total = (int) result.getObject(5);
                   int extra_bed = (int) result.getObject(6);
                   int extra_bed_price = (int) result.getObject(7);
                   String hotel_name = result.getObject(8).toString();
                   String meal_type = result.getObject(9).toString();
                   int price_meal_per_person = (int) result.getObject(10);
                   int room_price_per_day = (int) result.getObject(11);
                   int extra_bed_availability = (int) result.getObject(12);
                   int max_person = (int) result.getObject(13);
                   String room_type = result.getObject(14).toString();
                   int room_number = (int) result.getObject(15);
                   Reservation currentReservation = new Reservation(total_person,person_over_12,check_in,check_out,price_total,extra_bed, extra_bed_price,hotel_name,meal_type,price_meal_per_person, room_price_per_day,extra_bed_availability,max_person,room_type,room_number,checkReference);

                   System.out.println("hotel name: " + hotel_name + ". room number: " + room_number + ". room type: " + room_type + ". room price per day: " + room_price_per_day + ".");
                   System.out.println("total person: " + total_person + ". person over 12: " + person_over_12 + ". check in: " + check_in + ". check out: " + check_out + ".");
                   System.out.println("extra bed: " + extra_bed + ". extra bed price: " + extra_bed_price + ". meal type: " + meal_type + ". meal price per person: " + price_meal_per_person + ".");
                   System.out.println("extra bed availability: " + extra_bed_availability + ". max person: " + max_person + ".");
                   System.out.println("total price: " + price_total + ".");
                   System.out.println("I have found your reservation. What do you want to change?");
                   return currentReservation;
               }
           } catch (SQLException ex) {
               ex.getStackTrace();
           }
       }
    return null;
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
            conn = DriverManager.getConnection("jdbc:mysql://localhost/hotel_booking_system?user=root&password=mysql&serverTimezone=UTC&allowMultiQueries=true");
        } catch (Exception ex) { ex.printStackTrace(); }
    }

}
