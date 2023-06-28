package com.codecool.ehotel;
import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.MealDurability;
import com.codecool.ehotel.model.MealType;
import com.codecool.ehotel.service.buffet.BuffetServiceImpl;
import com.codecool.ehotel.service.guest.GuestServiceImpl;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


public class EHotelBuffetApplication {

    public static void main(String[] args) {

        BuffetServiceImpl buffetService = new BuffetServiceImpl();
        Buffet buffet = new Buffet();
        LocalDateTime now = LocalDateTime.now();
        Map<MealType, Integer> portionsPerType = new HashMap<>();
        portionsPerType.put(MealType. SCRAMBLED_EGGS, 20);

    }
}
