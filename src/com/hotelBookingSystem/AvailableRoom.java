package com.hotelBookingSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AvailableRoom {
    private String hotel_name;
    private int stars;
    private int score;
    private int room_number;
    private String room_type;
    private int room_price_per_day;
    private int extra_bed_availability;
    private int extra_bed_price;
    private int max_persons;
    private String meal_type;
    private int price_meal_per_person;

    public AvailableRoom(String hotel_name, int stars, int score, int room_number, String room_type, int room_price_per_day, int extra_bed_availability, int extra_bed_price, int max_persons, String meal_type, int price_meal_per_person) {
        this.hotel_name = hotel_name;
        this.stars = stars;
        this.score = score;
        this.room_number = room_number;
        this.room_type = room_type;
        this.room_price_per_day = room_price_per_day;
        this.extra_bed_availability = extra_bed_availability;
        this.extra_bed_price = extra_bed_price;
        this.max_persons = max_persons;
        this.meal_type = meal_type;
        this.price_meal_per_person = price_meal_per_person;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public int getStars() {
        return stars;
    }

    public int getScore() {
        return score;
    }

    public int getRoom_number() {
        return room_number;
    }

    public String getRoom_type() {
        return room_type;
    }

    public int getRoom_price_per_day() {
        return room_price_per_day;
    }

    public int getExtra_bed_availability() {
        return extra_bed_availability;
    }

    public int getExtra_bed_price() {
        return extra_bed_price;
    }

    public int getMax_persons() {
        return max_persons;
    }

    public String getMeal_type() {
        return meal_type;
    }

    public int getPrice_meal_per_person() {
        return price_meal_per_person;
    }

    @Override
    public String toString() {
        return  "hotel_name: " + hotel_name +
                ". stars: " + stars +
                ". score: " + score +
                ". room number: " + room_number +
                ". room type: " + room_type +
                ". room price per day: " + room_price_per_day +
                ". extra bed availability: " + extra_bed_availability +
                ". extra bed price: " + extra_bed_price +
                ". max persons: " + max_persons +
                ". meal type: " + meal_type +
                ". price meal per person: " + price_meal_per_person +
                ".";
    }
}

