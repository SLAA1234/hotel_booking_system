package com.hotelBookingSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DbQuery {
    Connection conn = null;
    PreparedStatement statement;
    Scanner scanner = new Scanner(System.in);

    void showHotelMealChoice(Reservation currentReservation) {
        try {
            statement = conn.prepareStatement(" SELECT meal_choice_id, meal_type, price_meal_per_person FROM hotel_meal_choice\n" +
                    "\t\tWHERE hotel_id = (SELECT hotel_id FROM reservations\n" +
                    "\tWHERE reservation_reference = ?);\t\t\n");
            statement.setString(1, currentReservation.reservation_reference);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                int meal_choice_id = (int) result.getObject(1);
                String meal_type = (String) result.getObject(2);
                int price_meal_per_person = (int) result.getObject(3);
                System.out.println("The hotel has below meal choice: ");
                System.out.println("meal choice id: " + meal_choice_id + ". meal type: " + meal_type + ". meal price per person per day: " + price_meal_per_person + ".");
                System.out.println("input the meal choice id: ");
                int new_meal_choid_id = Integer.parseInt(scanner.nextLine());
                System.out.println("Select the meal type: ");
                String new_meal_type = scanner.nextLine();
                try {
                    statement = conn.prepareStatement(" UPDATE reservations SET meal_choice_id = ? WHERE reservation_reference = ?;");
                    statement.setInt(1, new_meal_choid_id);
                    statement.setString(2, currentReservation.reservation_reference);
                    statement.executeQuery();
                    }
                catch (Exception ex){
                    ex.printStackTrace();
                }
                currentReservation.meal_type = new_meal_type;
                System.out.println(currentReservation);
            }
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }
}