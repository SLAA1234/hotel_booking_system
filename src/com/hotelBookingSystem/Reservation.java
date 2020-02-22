package com.hotelBookingSystem;

import java.sql.SQLException;

public class Reservation {
    String reservation_reference;
    int total_person;
    int person_over_12 ;
    String check_in ;
    String check_out ;
    int price_total;
    int extra_bed ;
    int extra_bed_price ;
    String hotel_name ;
    String meal_type ;
    int price_meal_per_person ;
    int room_price_per_day;
    int extra_bed_availability ;
    int max_person;
    String room_type;
    int room_number;

    public Reservation(int total_person, int person_over_12 , String check_in, String check_out, int price_total, int extra_bed, int extra_bed_price, String hotel_name, String meal_type, int price_meal_per_person, int room_price_per_day, int extra_bed_availability, int max_person, String room_type, int room_number, String reservation_reference) throws SQLException {
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

    @Override
    public String toString() {
       return "hotel name: " + hotel_name + ". room number: " + room_number + ". room type: " + room_type + ". room price per day: " + room_price_per_day + ".\n" +
               "total person: " + total_person + ". person over 12: " + person_over_12 + ". check in: " + check_in + ". check out: " + check_out + ".\n" +
               "extra bed: " + extra_bed + ". extra bed price: " + extra_bed_price + ". meal type: " + meal_type + ". meal price per person: " + price_meal_per_person + ".\n" + "extra bed availability: " + extra_bed_availability + ". max person: " + max_person + "." +
               "total price: " + price_total + ". reservation_reference: " + reservation_reference + ".";
    }
}
