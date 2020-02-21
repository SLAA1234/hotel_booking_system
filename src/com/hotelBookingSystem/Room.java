package com.hotelBookingSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Room {
    private int room_id;
    private int room_number;
    private String extra_bed;//boolean or string?
    private String room_type;
    private int extra_bed_price;
    private int room_price_per_day;
    private int max_persons;
    Scanner scanner = new Scanner(System.in);
    Connection conn = null;
    PreparedStatement statement;

    private void showMaxPerson(){

            System.out.println("Please input the reference of the reservation: ");
            String reservation_reference = scanner.nextLine();
            try{
                statement = conn.prepareStatement("SELECT total_person, max_persons\n" +
                        "\tFROM reservations \n" +
                        "\t\t\t\tINNER JOIN rooms\n" +
                        "\t\t\t\t\tON reservations.room_id = rooms.room_id\n" +
                        "\t\t\t\t\t\tINNER JOIN room_type\n" +
                        "\t\t\t\t\t\t\tON rooms.room_type_id = room_type.room_type_id WHERE reservation_reference = ?");
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


                    conn.close();
                } else {
                    System.out.println("Couldn't find your reservation.");
                }
            }catch (SQLException ex){
                ex.getStackTrace();
            }
        }

    }

