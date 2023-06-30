package com.codecool.ehotel.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Buffet {
    private List<Food> foods;

    public Buffet() {
        foods = new ArrayList<>();
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

    public List<Food> getFoodByMealType(MealType mealType) {
        List<Food> matchingMeals = new ArrayList<>();
        for (Food food : foods) {
            if (food.mealType().equals(mealType)) {
                matchingMeals.add(food);
            }
        }
        return matchingMeals;
    }

    public List<Food> getFoodByDurability(MealDurability durability){
        List<Food> matchingMealsByDurability = new ArrayList<>();

        for (Food food : foods) {
            if (food.mealType().getDurability() == durability) {
                matchingMealsByDurability.add(food);
            }
        }
        return matchingMealsByDurability;
    }
}
