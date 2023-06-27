package com.codecool.ehotel.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Buffet {
    private static List<Food> foods;

    public Buffet() {
        foods = new ArrayList<>();
    }

    public List<Food> getFoods() {
        return Collections.unmodifiableList(foods);
    }

    public void addFood(Food food) {
        if(food != null) {
            foods.add(food);
        }
    }

    public void remove(Food food) {
        if (food != null) {
        foods.remove(food);
        }
    }
}
