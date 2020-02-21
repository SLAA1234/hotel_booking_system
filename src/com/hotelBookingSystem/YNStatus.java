package com.hotelBookingSystem;

public enum YNStatus {
    Y { public YNStatus opposite() {return N;} },
    N { public YNStatus opposite() {return Y;} };

    abstract public YNStatus opposite();
}
