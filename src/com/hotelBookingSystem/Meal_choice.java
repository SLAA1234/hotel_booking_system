package com.hotelBookingSystem;

public class Meal_choice {
    private int meal_choice_id;
    private String meal_type;
    private int price_meal_per_person;

    public Meal_choice(int meal_choice_id, String meal_type, int price_meal_per_person) {
        this.meal_choice_id = meal_choice_id;
        this.meal_type = meal_type;
        this.price_meal_per_person = price_meal_per_person;
    }

    public int getMeal_choice_id() {
        return meal_choice_id;
    }

    public String getMeal_type() {
        return meal_type;
    }

    public int getPrice_meal_per_person() {
        return price_meal_per_person;
    }

    @Override
    public String toString() {
        return "Hotel Meal choice:\n" +
                "meal_choice_id: " + meal_choice_id +
                ". meal_type: " + meal_type +
                ". price_meal_per_person: " + price_meal_per_person + ".";
    }
}
