package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Food;
import com.codecool.ehotel.model.MealType;

import java.time.LocalDateTime;
import java.util.*;

public class BuffetServiceImpl implements BuffetService {
    private Buffet buffet = new Buffet();
    @Override
    public void refillBuffet(Buffet buffet, Map<MealType, Integer> portionsPerType, LocalDateTime timeStamp) {
        for (Map.Entry<MealType, Integer> entry : portionsPerType.entrySet()) {
            MealType mealType = entry.getKey();
            int portionAmount = entry.getValue();

            for (int i = 0; i < portionAmount; i++) {
                buffet.addFood(new Food(mealType, portionAmount, timeStamp));
            }
        }
    }

    @Override
    public boolean consumeFreshest(MealType mealType) {
        List<Food> matchingMeals = new ArrayList<>();
        for (Food food : buffet.getFoods()) {
            if (food.mealType().equals(mealType)) {
                matchingMeals.add(food);
            }
        }

        if (matchingMeals.isEmpty()) {
            return false;
        }

        matchingMeals.sort(Comparator.comparing(Food::timeStamp));
        Food freshestMeal = matchingMeals.get(0);

        buffet.remove(freshestMeal);
        return true;
    }
}