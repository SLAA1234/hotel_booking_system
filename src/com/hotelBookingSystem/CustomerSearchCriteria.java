package com.hotelBookingSystem;

public class CustomerSearchCriteria {
    private String children_pool;
    private String children_club;
    private String evening_entertainment;
    private int distance_to_beach;
    private int distance_to_city_center;
    private int total_person;
    private int person_over_12;
    private String check_in;
    private String check_out;
    private int max_person_greater_than;
    private String meal_choice;

    public CustomerSearchCriteria(String children_pool, String children_club, String evening_entertainment, int distance_to_beach, int distance_to_city_center, int total_person, int person_over_12, String check_in, String check_out, int max_person_greater_than, String meal_choice) {
        this.children_pool = children_pool;
        this.children_club = children_club;
        this.evening_entertainment = evening_entertainment;
        this.distance_to_beach = distance_to_beach;
        this.distance_to_city_center = distance_to_city_center;
        this.total_person = total_person;
        this.person_over_12 = person_over_12;
        this.check_in = check_in;
        this.check_out = check_out;
        this.max_person_greater_than = max_person_greater_than;
        this.meal_choice = meal_choice;
    }

    public String getChildren_pool() {
        return children_pool;
    }

    public String getChildren_club() {
        return children_club;
    }

    public String getEvening_entertainment() {
        return evening_entertainment;
    }

    public int getDistance_to_beach() {
        return distance_to_beach;
    }

    public int getDistance_to_city_center() {
        return distance_to_city_center;
    }

    public int getTotal_person() {
        return total_person;
    }

    public int getPerson_over_12() {
        return person_over_12;
    }

    public String getCheck_in() {
        return check_in;
    }

    public String getCheck_out() {
        return check_out;
    }

    public int getMax_person_greater_than() {
        return max_person_greater_than;
    }

    public String getMeal_choice() {
        return meal_choice;
    }

    @Override
    public String toString() {
        return "CustomerSearchCriteria:\n" +
                "children pool: " + children_pool +
                ". children club: " + children_club +
                ". evening entertainment: " + evening_entertainment +
                ".\n distance to beach: " + distance_to_beach +
                ". distance to city center: " + distance_to_city_center +
                ". total person: " + total_person +
                ".\n person over 12: " + person_over_12 +
                ". max person greater than: " + max_person_greater_than + ". meal choice: " + meal_choice +
                ".\n check in: " + check_in +
                ". check out: " + check_out +
                ".";
    }
}
