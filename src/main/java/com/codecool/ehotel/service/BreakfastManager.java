package com.codecool.ehotel.service;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.MealDurability;
import com.codecool.ehotel.model.MealType;
import com.codecool.ehotel.service.buffet.BuffetService;
import com.codecool.ehotel.service.buffet.BuffetServiceImpl;
import com.codecool.ehotel.service.guest.GuestService;

import java.time.LocalDateTime;
import java.util.List;

public class BreakfastManager {
    private BuffetService buffetService;
    private GuestService guestService;

    public BreakfastManager(BuffetService buffetService, GuestService guestService) {
        this.buffetService = buffetService;
        this.guestService = guestService;
    }

    public void serve(List<Guest> guests, Buffet buffet, LocalDateTime currentTime) {
        buffetService.refillBuffet(buffet, buffetService.createPortionsPerTypeMap(), currentTime);
        serveBreakfast(guests, buffet);
        discardOldMeals(buffet, currentTime);
    }

    private void serveBreakfast(List<Guest> guests, Buffet buffet) {
        int totalGuests = guests.size();
        int happyGuests = 0;
        int unhappyGuests = 0;

        for (Guest guest : guests) {
            MealType preference = guest.getRandomPreference();
            boolean success = buffetService.consumeFreshest(buffet, preference);

            if (success) {
                happyGuests++;
            } else {
                guest.setUnhappy(true);
                unhappyGuests++;
            }
        }

        System.out.println("Guests: " + totalGuests);
        System.out.println("Happy Guests: " + happyGuests);
        System.out.println("Unhappy Guests: " + unhappyGuests);
    }

    private void discardOldMeals(Buffet buffet, LocalDateTime currentTime) {
        MealDurability shortDurability = MealDurability.SHORT;
        int cyclesToDiscard = 3;

        BuffetServiceImpl buffetServiceImpl = (BuffetServiceImpl) buffetService;

        double totalWaste = 0;

        for (MealType mealType : MealType.values()) {
            if (mealType.getDurability() == shortDurability) {
                for (int i = 0; i < cyclesToDiscard; i++) {
                    double waste = buffetServiceImpl.collectWaste(buffet, mealType.getDurability(), currentTime.plusHours(i));
                    totalWaste += waste;
                }
            }
        }

        System.out.println("Total Waste: " + totalWaste);
    }

}
