package com.codecool.ehotel;
import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.MealDurability;
import com.codecool.ehotel.model.MealType;
import com.codecool.ehotel.service.BreakfastManager;
import com.codecool.ehotel.service.buffet.BuffetServiceImpl;
import com.codecool.ehotel.service.guest.GuestService;
import com.codecool.ehotel.service.guest.GuestServiceImpl;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


public class EHotelBuffetApplication {

    public static void main(String[] args) {

        BuffetServiceImpl buffetService = new BuffetServiceImpl();
        Buffet buffet = new Buffet();
        LocalDateTime now = LocalDateTime.now();
        Map<MealType, Integer> portionsPerType = createPortionsPerTypeMap(160);
        GuestService guestService = new GuestServiceImpl();
        LocalDate seasonStart = LocalDate.of(2023, 5, 15);
        LocalDate seasonEnd = LocalDate.of(2023, 9, 15);
        List<Guest> listOfGuests = generateGuests(160, seasonStart, seasonEnd);
        List<List<Guest>> breakfastCycles =  ((GuestServiceImpl) guestService).splitGuestsIntoBreakfastCycles(listOfGuests);
        BreakfastManager breakfastManager = new BreakfastManager(buffetService, guestService);
        System.out.println(portionsPerType);
        breakfastManager.serve(listOfGuests, buffet, now);

    }

    public static Map<MealType, Integer> createPortionsPerTypeMap(int remainingMeals) {
        Map<MealType, Integer> portionsPerType = new HashMap<>();
        Random random = new Random();
        int minPortionAmount = 10;
        int maxPortionAmount = 50;

        for (MealType mealType : MealType.values()) {
            int portionAmount = random.nextInt(maxPortionAmount - minPortionAmount + 1) + minPortionAmount;
            if (remainingMeals < portionAmount) {
                portionAmount = remainingMeals;
            }
            portionsPerType.put(mealType, portionAmount);
            remainingMeals -= portionAmount;
            if (remainingMeals == 0) {
                break;
            }
        }

        return portionsPerType;
    }


    public static List<Guest> generateGuests(int numberOfGuests, LocalDate seasonStart, LocalDate seasonEnd) {
        List<Guest> listOfGuests = new ArrayList<>();

        for (int i = 0; i < numberOfGuests; i++) {
            GuestService guestService = new GuestServiceImpl();
            Guest guest = ((GuestServiceImpl) guestService).generateRandomGuest(seasonStart, seasonEnd);
            listOfGuests.add(guest);
        }

        return listOfGuests;
    }

}
