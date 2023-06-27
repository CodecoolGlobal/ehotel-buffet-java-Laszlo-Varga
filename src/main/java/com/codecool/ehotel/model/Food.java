package com.codecool.ehotel.model;

import java.time.LocalDateTime;

public record Food(MealType mealType, int portionAmount, LocalDateTime timeStamp) {
}
