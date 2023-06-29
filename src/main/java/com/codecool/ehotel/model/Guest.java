package com.codecool.ehotel.model;

import java.time.LocalDate;
import java.util.Random;

public record Guest(String name, GuestType guestType, LocalDate checkIn, LocalDate checkOut) {

    private static boolean unhappy;


    public MealType getRandomPreference() {
        MealType[] mealTypes = MealType.values();
        Random random = new Random();
        return mealTypes[random.nextInt(mealTypes.length)];
    }


    public void setUnhappy(boolean b) {
        this.unhappy = b;
    }


    public boolean isUnhappy() {
        return this.unhappy;
    }
}
