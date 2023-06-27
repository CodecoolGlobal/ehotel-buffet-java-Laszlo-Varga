package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.MealType;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;

public interface BuffetService {

    void refillBuffet(Buffet buffet, Map<MealType, Integer> portionsPerType, LocalDateTime timestamp);

    boolean consumeFreshest(MealType mealType);

}
