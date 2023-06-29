package com.codecool.ehotel;
import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.MealDurability;
import com.codecool.ehotel.model.MealType;
import com.codecool.ehotel.service.buffet.BuffetServiceImpl;
import com.codecool.ehotel.service.guest.GuestService;
import com.codecool.ehotel.service.guest.GuestServiceImpl;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EHotelBuffetApplication {

    public static void main(String[] args) {

        BuffetServiceImpl buffetService = new BuffetServiceImpl();
        Buffet buffet = new Buffet();
        LocalDateTime now = LocalDateTime.now();
        Map<MealType, Integer> portionsPerType = new HashMap<>();
        portionsPerType.put(MealType. SCRAMBLED_EGGS, 20);
        GuestService guestService = new GuestServiceImpl();
        LocalDate seasonStart = LocalDate.of(2023, 5, 15);
        LocalDate seasonEnd = LocalDate.of(2023, 9, 15);
        Guest guest = guestService.generateRandomGuest(seasonStart, seasonEnd);
        List<Guest> listOfGuests = new ArrayList<>();
        listOfGuests.add(guest);


        System.out.println(guest);
    }
}
