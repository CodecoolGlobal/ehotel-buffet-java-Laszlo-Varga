package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.MealDurability;
import com.codecool.ehotel.model.MealType;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;

public interface BuffetService {

    void refillBuffet(Map<MealType, Integer> portionsPerType, LocalDateTime timestamp);

    boolean consumeFreshest(MealType mealType);


    double collectWaste(MealDurability durability, LocalDateTime timeStamp);
}
