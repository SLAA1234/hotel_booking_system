package com.hotelBookingSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Reservation {
    String reservation_reference;
    String total_person ;
    String person_over_12 ;
    String check_in ;
    String check_out ;
    String price_total;
    String extra_bed ;
    String extra_bed_price ;
    String hotel_name ;
    String meal_type ;
    String price_meal_per_person ;
    String room_price_per_day;
    String extra_bed_availability ;
    String max_person;
    String room_type;
    String room_number;

    public Reservation(String total_person, String person_over_12, String check_in, String check_out, String price_total, String extra_bed, String extra_bed_price, String hotel_name, String meal_type, String price_meal_per_person, String room_price_per_day, String extra_bed_availability, String max_person, String room_type, String room_number, String reservation_reference) throws SQLException {
        this.total_person = total_person;
        this.person_over_12 = person_over_12;
        this.check_in = check_in;
        this.check_out = check_out;
        this.price_total = price_total;
        this.extra_bed = extra_bed;
        this.extra_bed_price = extra_bed_price;
        this.hotel_name = hotel_name;
        this.meal_type = meal_type;
        this.price_meal_per_person = price_meal_per_person;
        this.room_price_per_day = room_price_per_day;
        this.extra_bed_availability = extra_bed_availability;
        this.max_person = max_person;
        this.room_type = room_type;
        this.room_number = room_number;
        this.reservation_reference = reservation_reference;
    }


}
