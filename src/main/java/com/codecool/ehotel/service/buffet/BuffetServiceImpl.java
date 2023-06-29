package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Food;
import com.codecool.ehotel.model.MealDurability;
import com.codecool.ehotel.model.MealType;

import java.time.LocalDateTime;
import java.util.*;

public class BuffetServiceImpl implements BuffetService {

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
    public boolean consumeFreshest(Buffet buffet, MealType mealType) {
        List<Food> matchingMeals = buffet.getFoodByMealType(mealType);

        if (matchingMeals.isEmpty()) {
            return false;
        }

        matchingMeals.sort(Comparator.comparing(Food::timeStamp));
        Food freshestMeal = matchingMeals.get(0);

        buffet.remove(freshestMeal);
        return true;
    }

    @Override
    public double collectWaste(Buffet buffet, MealDurability durability, LocalDateTime timeStamp) {
        double sumCost = 0;

        List<Food> mealsByDurability = buffet.getFoodByDurability(durability);

        for (Food food : mealsByDurability) {
            if (food.timeStamp().isAfter(timeStamp)) {
                buffet.remove(food);
                sumCost += food.mealType().getCost();
            }
        }

        return sumCost;
    }

    @Override
    public Map<MealType, Integer> createPortionsPerTypeMap() {
        Map<MealType, Integer> portionsPerType = new HashMap<>();

        Random random = new Random();
        int minPortionAmount = 10;
        int maxPortionAmount = 50;

        for (MealType mealType : MealType.values()) {
            int portionAmount = random.nextInt(maxPortionAmount - minPortionAmount + 1) + minPortionAmount;
            portionsPerType.put(mealType, portionAmount);
        }

        return portionsPerType;
    }

}