package com.hotelBookingSystem;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Program {
    Connection conn = null;
    PreparedStatement statement;
    private Admin currentAdmin;
    Scanner scanner = new Scanner(System.in);

    public Program() throws SQLException {
    }

    public void start() throws SQLException {
        connectToDb();

        while (true) {
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
            System.out.println("2.Search available room And make a reservation.");
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
                    CustomerSearchCriteria customerSearchCriteria = checkCustomerSearchCriteria();
                    if(customerSearchCriteria!= null){
                        searchAndBookRoom(customerSearchCriteria);
                    } ;
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


    private void changeReservation(Reservation currentReservation) throws SQLException {
            while (true) {

                System.out.println("1.change number of total persons/persons over 12 years old.");
                System.out.println("2.change extra bed status.");
                System.out.println("3.change meal choice.");
                System.out.println("4.Exit.");
                int choice = 999;

                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (Exception e) {
                    System.out.println("You must select a number.");
                }

                switch (choice) {

                    case 1:
                        if (currentReservation != null) {
                            changePerson(currentReservation);
                            showUpdatedReservation(currentReservation);
                        }
                        break;
                    case 2:
                        if(currentReservation!=null){
                            changeExtraBed(currentReservation);
                            showUpdatedReservation(currentReservation);
                        }
                        break;
                    case 3:
                        if(currentReservation!=null) {
                            changeMeal(currentReservation);
                            showUpdatedReservation(currentReservation);
                        }
                        break;
                    case 4:
                        System.exit(0);
                    default:
                        System.out.println("You must choose a number between 1-4.");
                }
            }
        }

    public void showUpdatedReservation(Reservation currentReservation){
        try {
            statement = conn.prepareStatement("SELECT * FROM reservation_all_useful_info WHERE reservation_reference = ?");
            statement.setString(1, currentReservation.reservation_reference);
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
                String reservation_reference = result.getObject(16).toString();
                Reservation newReservation = new Reservation(total_person,person_over_12,check_in,check_out,price_total,extra_bed, extra_bed_price,hotel_name,meal_type,price_meal_per_person, room_price_per_day,extra_bed_availability,max_person,room_type,room_number,reservation_reference);
                System.out.println(newReservation);

            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }

    }

    public Reservation getUpdatedReservation(Reservation currentReservation){
        try {
            statement = conn.prepareStatement("SELECT * FROM reservation_all_useful_info WHERE reservation_reference = ?");
            statement.setString(1, currentReservation.reservation_reference);
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
                String reservation_reference = result.getObject(16).toString();
                Reservation newReservation = new Reservation(total_person,person_over_12,check_in,check_out,price_total,extra_bed, extra_bed_price,hotel_name,meal_type,price_meal_per_person, room_price_per_day,extra_bed_availability,max_person,room_type,room_number,reservation_reference);
                return newReservation;

            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        return null;
    }

    private void changeMeal(Reservation currentReservation) {
        showHotelMealChoice(currentReservation);
        System.out.println("input the meal choice id: ");
        try {int new_meal_choice_id = Integer.parseInt(scanner.nextLine());
            statement = conn.prepareStatement(" UPDATE reservations SET meal_choice_id = ? WHERE reservation_reference = ?;UPDATE reservation_all_useful_info SET price_total = (room_price_per_day + person_over_12 * price_meal_per_person + extra_bed * extra_bed_price) * DATEDIFF(check_out, check_in) WHERE reservation_reference = ?;");
            statement.setInt(1, new_meal_choice_id);
            statement.setString(2, currentReservation.reservation_reference);
            statement.setString(3, currentReservation.reservation_reference);
            statement.executeUpdate();
                }
                catch (Exception ex){
                    System.out.println("You must input a number.");
                   ex.printStackTrace();
                }
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
        System.out.println("I will help you change your extra bed status.");
        Reservation newReservation = getUpdatedReservation(currentReservation);
        if (newReservation.extra_bed_availability == 1 && newReservation.extra_bed == 1) {
            try {
                statement = conn.prepareStatement(" update reservations SET extra_bed = ? Where reservation_reference = ?;UPDATE reservation_all_useful_info SET price_total = (room_price_per_day + person_over_12 * price_meal_per_person + extra_bed * extra_bed_price) * DATEDIFF(check_out, check_in) WHERE reservation_reference = ?");
                statement.setInt(1, 0);
                statement.setString(2, currentReservation.reservation_reference);
                statement.setString(3, currentReservation.reservation_reference);
                statement.executeUpdate();
                System.out.println("Your new reservation details: ");
            } catch (SQLException ex) {
                ex.getStackTrace();
            }
        } else if (newReservation.extra_bed_availability == 1 && newReservation.extra_bed == 0) {
            try {
                statement = conn.prepareStatement(" update reservations SET extra_bed = ? Where reservation_reference = ?; UPDATE reservation_all_useful_info SET price_total = (room_price_per_day + person_over_12 * price_meal_per_person + extra_bed * extra_bed_price) * DATEDIFF(check_out, check_in) WHERE reservation_reference = ?");
                statement.setInt(1, 1);
                statement.setString(2, currentReservation.reservation_reference);
                statement.setString(3, currentReservation.reservation_reference);
                statement.executeUpdate();
                System.out.println("Your new reservation details: ");
            } catch (SQLException ex) {
                ex.getStackTrace();
            }
        } else {
            System.out.println("Oh! This room doesn't allow to add extra bed.");
        }
    }

    public void changePerson(Reservation currentReservation){
        System.out.println("How many guests over 12 years old will come? ");

        try{
            int new_total_person_over_12 = Integer.parseInt(scanner.nextLine());
            System.out.println("How many guests in total will come? ");
            int new_total_person = Integer.parseInt(scanner.nextLine());

            if(currentReservation!=null && currentReservation.max_person>=new_total_person) {
                statement = conn.prepareStatement("UPDATE reservation_all_useful_info SET total_person = ?, person_over_12 =? WHERE reservation_reference = ?; UPDATE reservation_all_useful_info SET price_total = (room_price_per_day + person_over_12 * price_meal_per_person + extra_bed * extra_bed_price) * DATEDIFF(check_out, check_in) WHERE reservation_reference = ?");
                statement.setInt(1, new_total_person);
                statement.setInt(2, new_total_person_over_12);
                statement.setString(3, currentReservation.reservation_reference);
                statement.setString(4, currentReservation.reservation_reference);
                statement.executeUpdate();
            }else{
                System.out.println("The room has reached its max capacity.");
            }
        }catch (Exception ex){
            System.out.println("You must input a number.");
        }
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
            } else{
            System.out.println("This reservation doesn't exit.");}
        }catch (SQLException ex){
            ex.getStackTrace();
        }
    }

    private void searchAndBookRoom(CustomerSearchCriteria customerSearchCriteria){

        System.out.println("Do you want to display search result by:\n 1.room price (from low to high) \n 2.customer review score (from high to low)");
        int choice = 999;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            e.printStackTrace();
        }

            switch (choice) {
                case 1:
                    if(customerSearchCriteria!=null) {
                        searchAndBookRoomByPrice(customerSearchCriteria);
                    }
                    break;
                case 2:
                    if(customerSearchCriteria!=null) {
                        searchAndBookRoomByScore(customerSearchCriteria);
                    }
                    break;
                default:
                    System.out.println("You must choose a number between 1-2.");
            }
        }

    private void searchAndBookRoomByPrice (CustomerSearchCriteria customerSearchCriteria) {
            try {
                statement = conn.prepareStatement(
                        "SELECT hotel_name, stars, score, room_number, room_type.room_type, room_price_per_day,extra_bed_availability, extra_bed_price, max_persons, meal_type, price_meal_per_person\n" +
                                "\tFROM hotels\n" +
                                "\t\tinner JOIN rooms\n" +
                                "\t\t\tON rooms.hotel_id = hotels.hotel_id\n" +
                                "\t\t\t\tINNER JOIN room_type\n" +
                                "\t\t\t\t\tON rooms.room_type_id = room_type.room_type_id\n" +
                                "\t\t\t\t\t\tINNER JOIN hotel_meal_choice\n" +
                                "\t\t\t\t\t\t\tON hotel_meal_choice.hotel_id = hotels.hotel_id\n" +
                                "\t\t\t\t\t\t\t\n" +
                                "\t\t\t\t\t\t\t\tWHERE children_pool = ? And children_club = ? And evening_entertainment = ? AND distance_to_beach < ? AND distance_to_city_center < ? AND max_persons > ? AND meal_type = ?\n" +
                                "\t\t\t\t\t\t\t\t\t\tAND room_number NOT IN\n" +
                                "\t\t(SELECT  Distinct room_number FROM rooms\n" +
                                "\t\t\tLEFT JOIN reservations\n" +
                                "\t\t\t\tON reservations.room_id = rooms.room_id\n" +
                                "\t\t\t\t\tWHERE ?  BETWEEN check_in AND check_out\n" +
                                "\t\t\t\t\t\tOr ?  BETWEEN check_in AND check_out\n" +
                                "\t\t\t\t\t\t\tOr check_in  BETWEEN ? AND ?\n" +
                                "\t\t\t\t\t\t\t\tOr check_out  BETWEEN ? AND ? ORDER BY room_price_per_day)");
                statement.setString(1, customerSearchCriteria.getChildren_pool());
                statement.setString(2, customerSearchCriteria.getChildren_club());
                statement.setString(3, customerSearchCriteria.getEvening_entertainment());
                statement.setInt(4, customerSearchCriteria.getDistance_to_beach());
                statement.setInt(5, customerSearchCriteria.getDistance_to_city_center());
                statement.setInt(6, customerSearchCriteria.getMax_person_greater_than());
                statement.setString(7, customerSearchCriteria.getMeal_choice());
                statement.setString(8, customerSearchCriteria.getCheck_in());
                statement.setString(9, customerSearchCriteria.getCheck_out());
                statement.setString(10, customerSearchCriteria.getCheck_in());
                statement.setString(11, customerSearchCriteria.getCheck_out());
                statement.setString(12, customerSearchCriteria.getCheck_in());
                statement.setString(13, customerSearchCriteria.getCheck_out());
                ResultSet result = statement.executeQuery();

                ArrayList<AvailableRoom> availableRoomList = new ArrayList<>();
                while (result.next()) {
                    String hotel_name = result.getObject(1).toString();
                    int stars = Integer.parseInt(result.getObject(2).toString());
                    Integer score = Integer.parseInt(result.getObject(3).toString());
                    int room_number = Integer.parseInt(result.getObject(4).toString());
                    String room_type = result.getObject(5).toString();
                    int room_price_per_day = Integer.parseInt(result.getObject(6).toString());
                    int extra_bed_availability = Integer.parseInt(result.getObject(7).toString());
                    int extra_bed_price = Integer.parseInt(result.getObject(8).toString());
                    int max_persons = Integer.parseInt(result.getObject(9).toString());
                    String meal_type = result.getObject(10).toString();
                    int price_meal_per_person = Integer.parseInt(result.getObject(11).toString());
                    AvailableRoom availableRoom = new AvailableRoom(hotel_name, stars, score, room_number, room_type, room_price_per_day, extra_bed_availability, extra_bed_price, max_persons, meal_type, price_meal_per_person);
                    availableRoomList.add(availableRoom);
                }

                if (availableRoomList.size() > 0) {

                    for (int a = 1, i = 0; a < availableRoomList.size(); a++, i++) {
                        System.out.println("Available Room case number: " + a + "\n " + availableRoomList.get(i) + "\n");
                    }
                    System.out.println("\nPlease input the case number you want to book: ");
                    int booking_room_number = 999;
                    try {
                        booking_room_number = Integer.parseInt(scanner.nextLine());
                    } catch (Exception ex) {
                        System.out.println("You must input a number.");
                    }

                    int extra_bed = 999;
                    if (availableRoomList.get(booking_room_number - 1).getExtra_bed_availability() == 1) {
                        System.out.println("Do you want to have an extra bed? 0: No  1: Yes\nPlease input 0 Or 1.");
                        try {
                            extra_bed = checkExBed(Integer.parseInt(scanner.nextLine()));
                        } catch (Exception ex) {
                            System.out.println("You must input a number");
                        }
                    } else {
                        extra_bed = 0;
                    }
                    System.out.println("Admin is making the reservation for current guest...\nAdmin, input a random and secret booking reference: ");
                    String reservation_reference = scanner.nextLine();

                    int price_total = (int) ((availableRoomList.get(booking_room_number - 1).getRoom_price_per_day() + customerSearchCriteria.getPerson_over_12() * availableRoomList.get(booking_room_number - 1).getPrice_meal_per_person() + extra_bed * availableRoomList.get(booking_room_number - 1).getExtra_bed_price()) * calculateDays(customerSearchCriteria.getCheck_in(), customerSearchCriteria.getCheck_out()));

                    Reservation new_reservation = new Reservation(customerSearchCriteria.getTotal_person(), customerSearchCriteria.getPerson_over_12(), customerSearchCriteria.getCheck_in(), customerSearchCriteria.getCheck_out(), price_total, extra_bed, availableRoomList.get(booking_room_number - 1).getExtra_bed_price(), availableRoomList.get(booking_room_number - 1).getHotel_name(), customerSearchCriteria.getMeal_choice(), availableRoomList.get(booking_room_number - 1).getPrice_meal_per_person(), availableRoomList.get(booking_room_number - 1).getRoom_price_per_day(), availableRoomList.get(booking_room_number - 1).getExtra_bed_availability(), availableRoomList.get(booking_room_number - 1).getMax_persons(), availableRoomList.get(booking_room_number - 1).getRoom_type(), availableRoomList.get(booking_room_number - 1).getRoom_number(), reservation_reference);
                    System.out.println("This is your reservation detail:\n " + new_reservation + "\n");
                    try{
                        statement= conn.prepareStatement ("SELECT room_id, rooms.hotel_id, meal_choice_id FROM rooms INNER JOIN hotel_meal_choice ON rooms.hotel_id = hotel_meal_choice.hotel_id WHERE room_number = ? AND meal_type = ?;");
                        statement.setInt(1,availableRoomList.get(booking_room_number - 1).getRoom_number());
                        statement.setString(2,customerSearchCriteria.getMeal_choice());
                        ResultSet result1 = statement.executeQuery();
                        while (result1.next()) {
                            int room_id = Integer.parseInt(result1.getObject(1).toString());
                            int hotel_id = Integer.parseInt(result1.getObject(2).toString());
                            int meal_choice_id = Integer.parseInt(result1.getObject(3).toString());
                            try {
                                statement = conn.prepareStatement("INSERT INTO reservations\n" +
                                        "SET hotel_id = ?, room_id = ?, total_person = ?, person_over_12 = ?, meal_choice_id= ?, extra_bed = ?, check_in = ?, \n" +
                                        "check_out=?, reservation_reference=?, price_total=?;");
                                statement.setInt(1,hotel_id);
                                statement.setInt(2,room_id);
                                statement.setInt(3,customerSearchCriteria.getTotal_person());
                                statement.setInt(4,customerSearchCriteria.getPerson_over_12());
                                statement.setInt(5,meal_choice_id);
                                statement.setInt(6,extra_bed);
                                statement.setString(7,customerSearchCriteria.getCheck_in());
                                statement.setString(8,customerSearchCriteria.getCheck_out());
                                statement.setString(9,reservation_reference);
                                statement.setInt(10,price_total);
                                statement.executeUpdate();
                            } catch (SQLException ex) {
                                ex.getStackTrace();
                            }
                        }

                    }catch (SQLException ex){
                        ex.getStackTrace();
                    }

                }else{
                    System.out.println("There is no room available now.\n");
                    System.out.println("Do you want: \n1.exit.\n2.make new search.");
                    int choice=999;
                    try{
                        choice=Integer.parseInt(scanner.nextLine());
                    }catch (Exception ex){
                        System.out.println("You must enter a number.");
                    }
                    switch (choice){
                        case 1:
                            System.exit(0);
                            break;
                        case 2:
                            searchByPriceAgain();
                            break;
                        default:
                            System.out.println("You must input number between 1-2.");
                    }
                }
                } catch(SQLException e){
                    e.printStackTrace();
                }
    }

    private void searchByPriceAgain() {
        CustomerSearchCriteria newCriteria = checkCustomerSearchCriteria();
        if(newCriteria!=null) {
            searchAndBookRoomByPrice(newCriteria);
        }
    }

    private Float calculateDays(String check_in, String check_out){
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-mm-dd");

        try {
            Date dateBefore = myFormat.parse(check_in);
            Date dateAfter = myFormat.parse(check_out);
            long difference = dateAfter.getTime() - dateBefore.getTime();
            float daysBetween = (difference / (1000*60*60*24));
            return daysBetween;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private int checkExBed(int userInput) {
        if(userInput==0 || userInput ==1){
            return userInput;
        }
        return 0;
    }


    private void searchAndBookRoomByScore(CustomerSearchCriteria customerSearchCriteria){
            try {
                statement = conn.prepareStatement(
                        "SELECT hotel_name, stars, score, room_number, room_type.room_type, room_price_per_day,extra_bed_availability, extra_bed_price, max_persons, meal_type, price_meal_per_person\n" +
                                "\tFROM hotels\n" +
                                "\t\tinner JOIN rooms\n" +
                                "\t\t\tON rooms.hotel_id = hotels.hotel_id\n" +
                                "\t\t\t\tINNER JOIN room_type\n" +
                                "\t\t\t\t\tON rooms.room_type_id = room_type.room_type_id\n" +
                                "\t\t\t\t\t\tINNER JOIN hotel_meal_choice\n" +
                                "\t\t\t\t\t\t\tON hotel_meal_choice.hotel_id = hotels.hotel_id\n" +
                                "\t\t\t\t\t\t\t\n" +
                                "\t\t\t\t\t\t\t\tWHERE children_pool = ? And children_club = ? And evening_entertainment = ? AND distance_to_beach < ? AND distance_to_city_center < ? AND max_persons > ? AND meal_type = ?\n" +
                                "\t\t\t\t\t\t\t\t\t\tAND room_number NOT IN\n" +
                                "\t\t(SELECT  Distinct room_number FROM rooms\n" +
                                "\t\t\tLEFT JOIN reservations\n" +
                                "\t\t\t\tON reservations.room_id = rooms.room_id\n" +
                                "\t\t\t\t\tWHERE ?  BETWEEN check_in AND check_out\n" +
                                "\t\t\t\t\t\tOr ?  BETWEEN check_in AND check_out\n" +
                                "\t\t\t\t\t\t\tOr check_in  BETWEEN ? AND ?\n" +
                                "\t\t\t\t\t\t\t\tOr check_out  BETWEEN ? AND ? ORDER BY score DESC)");
                statement.setString(1, customerSearchCriteria.getChildren_pool());
                statement.setString(2, customerSearchCriteria.getChildren_club());
                statement.setString(3, customerSearchCriteria.getEvening_entertainment());
                statement.setInt(4, customerSearchCriteria.getDistance_to_beach());
                statement.setInt(5, customerSearchCriteria.getDistance_to_city_center());
                statement.setInt(6, customerSearchCriteria.getMax_person_greater_than());
                statement.setString(7, customerSearchCriteria.getMeal_choice());
                statement.setString(8, customerSearchCriteria.getCheck_in());
                statement.setString(9, customerSearchCriteria.getCheck_out());
                statement.setString(10, customerSearchCriteria.getCheck_in());
                statement.setString(11, customerSearchCriteria.getCheck_out());
                statement.setString(12, customerSearchCriteria.getCheck_in());
                statement.setString(13, customerSearchCriteria.getCheck_out());
                ResultSet result = statement.executeQuery();
                ArrayList<AvailableRoom> availableRoomList = new ArrayList<>();
                while (result.next()) {
                    String hotel_name = result.getObject(1).toString();
                    int stars = Integer.parseInt(result.getObject(2).toString());
                    Integer score = Integer.parseInt(result.getObject(3).toString());
                    int room_number = Integer.parseInt(result.getObject(4).toString());
                    String room_type = result.getObject(5).toString();
                    int room_price_per_day = Integer.parseInt(result.getObject(6).toString());
                    int extra_bed_availability = Integer.parseInt(result.getObject(7).toString());
                    int extra_bed_price = Integer.parseInt(result.getObject(8).toString());
                    int max_persons = Integer.parseInt(result.getObject(9).toString());
                    String meal_type = result.getObject(10).toString();
                    int price_meal_per_person = Integer.parseInt(result.getObject(11).toString());
                    AvailableRoom availableRoom = new AvailableRoom(hotel_name, stars, score, room_number, room_type, room_price_per_day, extra_bed_availability, extra_bed_price, max_persons, meal_type, price_meal_per_person);
                    availableRoomList.add(availableRoom);
                }

                if (availableRoomList.size() > 0) {

                    for (int a = 1, i = 0; a < availableRoomList.size(); a++, i++) {
                        System.out.println("Available Room case number: " + a + "\n " + availableRoomList.get(i) + "\n");
                    }
                    System.out.println("\nPlease input the case number you want to book: ");
                    int booking_room_number = 999;
                    try {
                        booking_room_number = Integer.parseInt(scanner.nextLine());
                    } catch (Exception ex) {
                        System.out.println("You must input a number.");
                    }

                    int extra_bed = 999;
                    if (availableRoomList.get(booking_room_number - 1).getExtra_bed_availability() == 1) {
                        System.out.println("Do you want to have an extra bed? 0: No  1: Yes\nPlease input 0 Or 1.");
                        try {
                            extra_bed = checkExBed(Integer.parseInt(scanner.nextLine()));
                        } catch (Exception ex) {
                            System.out.println("You must input a number");
                        }
                    } else {
                        extra_bed = 0;
                    }
                    System.out.println("Admin is making the reservation for current guest...\nAdmin, input a random and secret booking reference: ");
                    String reservation_reference = scanner.nextLine();

                    int price_total = (int) ((availableRoomList.get(booking_room_number - 1).getRoom_price_per_day() + customerSearchCriteria.getPerson_over_12() * availableRoomList.get(booking_room_number - 1).getPrice_meal_per_person() + extra_bed * availableRoomList.get(booking_room_number - 1).getExtra_bed_price()) * calculateDays(customerSearchCriteria.getCheck_in(), customerSearchCriteria.getCheck_out()));

                    Reservation new_reservation = new Reservation(customerSearchCriteria.getTotal_person(), customerSearchCriteria.getPerson_over_12(), customerSearchCriteria.getCheck_in(), customerSearchCriteria.getCheck_out(), price_total, extra_bed, availableRoomList.get(booking_room_number - 1).getExtra_bed_price(), availableRoomList.get(booking_room_number - 1).getHotel_name(), customerSearchCriteria.getMeal_choice(), availableRoomList.get(booking_room_number - 1).getPrice_meal_per_person(), availableRoomList.get(booking_room_number - 1).getRoom_price_per_day(), availableRoomList.get(booking_room_number - 1).getExtra_bed_availability(), availableRoomList.get(booking_room_number - 1).getMax_persons(), availableRoomList.get(booking_room_number - 1).getRoom_type(), availableRoomList.get(booking_room_number - 1).getRoom_number(), reservation_reference);
                    System.out.println("This is your reservation detail:\n " + new_reservation + "\n");

                    try{
                        statement= conn.prepareStatement ("SELECT room_id, rooms.hotel_id, meal_choice_id FROM rooms INNER JOIN hotel_meal_choice ON rooms.hotel_id = hotel_meal_choice.hotel_id WHERE room_number = ? AND meal_type = ?;");
                        statement.setInt(1,availableRoomList.get(booking_room_number - 1).getRoom_number());
                        statement.setString(2,customerSearchCriteria.getMeal_choice());
                        ResultSet result1 = statement.executeQuery();
                        while (result1.next()) {
                            int room_id = Integer.parseInt(result1.getObject(1).toString());
                            int hotel_id = Integer.parseInt(result1.getObject(2).toString());
                            int meal_choice_id = Integer.parseInt(result1.getObject(3).toString());
                            try {
                                statement = conn.prepareStatement("INSERT INTO reservations\n" +
                                        "SET hotel_id = ?, room_id = ?, total_person = ?, person_over_12 = ?, meal_choice_id= ?, extra_bed = ?, check_in = ?, \n" +
                                        "check_out=?, reservation_reference=?, price_total=?;");
                                statement.setInt(1,hotel_id);
                                statement.setInt(2,room_id);
                                statement.setInt(3,customerSearchCriteria.getTotal_person());
                                statement.setInt(4,customerSearchCriteria.getPerson_over_12());
                                statement.setInt(5,meal_choice_id);
                                statement.setInt(6,extra_bed);
                                statement.setString(7,customerSearchCriteria.getCheck_in());
                                statement.setString(8,customerSearchCriteria.getCheck_out());
                                statement.setString(9,reservation_reference);
                                statement.setInt(10,price_total);
                                statement.executeUpdate();
                            } catch (SQLException ex) {
                                ex.getStackTrace();
                            }
                        }

                    }catch (SQLException ex){
                        ex.getStackTrace();
                    }

                }else{
                    System.out.println("There is no room available now.\n");
                    System.out.println("Do you want: \n1.exit.\n2.make new search.");
                    int choice=999;
                    try{
                        choice=Integer.parseInt(scanner.nextLine());
                    }catch (Exception ex){
                        System.out.println("You must enter a number.");
                    }
                    switch (choice){
                        case 1:
                            System.exit(0);
                            break;
                        case 2:
                            searchByScoreAgain();
                            break;
                        default:
                            System.out.println("You must input number between 1-2.");
                    }
                }
            } catch(SQLException e){
                e.printStackTrace();
            }
    }

    private void searchByScoreAgain() {
        CustomerSearchCriteria newCriteria = checkCustomerSearchCriteria();
        if(newCriteria!=null) {
            searchAndBookRoomByScore(newCriteria);
        }
    }

    private CustomerSearchCriteria checkCustomerSearchCriteria () {

            System.out.println("Having children pool? please answer with Y/N .");
            try {
                String children_pool = checkYN(scanner.nextLine().toUpperCase());
                System.out.println("Having children club?  Y/N .");
                String children_club = checkYN(scanner.nextLine().toUpperCase());
                System.out.println("Having evening entertainment?  Y/N .");
                String evening_entertainment = checkYN(scanner.nextLine().toUpperCase());
                System.out.println("What meal choice do you want? We have breakfast, halvpension, helpension, all inclusive, no meal.");
                String meal_choice = checkMealInput(scanner.nextLine().toLowerCase());
                System.out.println("When do you want to check in?(Available from 2020-06-01 to 2020-07-30)\n Answer with yyyy-mm-dd form.");
                String check_in = checkTime(scanner.nextLine());
                System.out.println("When do you want to check out? (Available from 2020-06-02 to 2020-07-31)\nAnswer with yyyy-mm-dd form.");
                String check_out = checkOutTime(scanner.nextLine());
                System.out.println("What's the max distance to beach? please answer with positive integer.");
                try {
                    int distance_to_beach = Integer.parseInt(scanner.nextLine());//check if need sepearte later
                    System.out.println("What's the max distance to city center? please answer with positive integer.");
                    int distance_to_city_center = Integer.parseInt(scanner.nextLine());
                    System.out.println("How many guests in total will come?(max persons in largest room: 6)");
                    int total_person = checkPersons(Integer.parseInt(scanner.nextLine()));
                    int max_person_greater_than = total_person - 1;// set like this in order to search max_person > max_person_bigger_than later
                    System.out.println("Children younger than 12 years old eat free in all our group hotel.\n So how many persons over 12 years old will come?");
                    int person_over_12 = checkPersons(Integer.parseInt(scanner.nextLine()));
                    CustomerSearchCriteria customerSearchCriteria = new CustomerSearchCriteria(children_pool, children_club, evening_entertainment, distance_to_beach, distance_to_city_center, total_person, person_over_12, check_in, check_out, max_person_greater_than, meal_choice);
                    return customerSearchCriteria;
                } catch (Exception ex) {
                    System.out.println("you must input numbers.");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }

    private String checkOutTime(String userInput) {
        if(userInput.compareTo("2020-06-01") <= 0){
            return "2020-06-02";
        }
        if(userInput.compareTo("2020-08-01")>=0){
            return "2020-07-31";
        }
        return userInput;
    }

    private String checkTime(String userInput) {
            if(userInput.compareTo("2020-05-31") <= 0){
                return "2020-06-01";
            }
            if(userInput.compareTo("2020-07-31")>=0){
                return "2020-07-30";
            }
            return userInput;
    }

    private String checkMealInput(String userInput) {
            if(userInput.equals("breakfast") ||userInput.equals("halvpension")||userInput.equals("helpension")||userInput.equals("all inclusive") ||userInput.equals("no meal")){
                return userInput;
            }
            return "no meal";
        }

    private int checkPersons(int userInput) {
            if(userInput<=6 ){
                return userInput;
            }
            return 6;
    }

    private String checkYN(String userInput) {
            if(userInput.equals("Y")){
                return "Y";
            }
            return "N";
    }

    private void createNewCustomer () {
            System.out.println("You want to register a customer.");
            System.out.println("Input first name: ");
            try {
                String person_first_name = scanner.nextLine();
                System.out.println("Input last name: ");
                String person_last_name = scanner.nextLine();
                System.out.println("Input pass number: ");
                String person_pass = scanner.nextLine();
                System.out.println("Input country of customer: ");
                String person_country = scanner.nextLine();
                System.out.println("Input email of customer: ");
                String person_email = scanner.nextLine();
                System.out.println("Input telephone: ");
                String person_telephone = scanner.nextLine();


                statement = conn.prepareStatement("INSERT INTO persons SET person_first_name = ?, person_last_name = ?, person_pass = ?, person_country = ?, person_email = ?, person_telephone = ?;");
                statement.setString(1, person_first_name);
                statement.setString(2, person_last_name);
                statement.setString(3, person_pass);
                statement.setString(4, person_country);
                statement.setString(5, person_email);
                statement.setString(6, person_telephone);
                statement.executeUpdate();
                System.out.println(person_first_name + " " + person_last_name + " has been successfully registered as customer.");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        private Admin adminLogin () {
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
