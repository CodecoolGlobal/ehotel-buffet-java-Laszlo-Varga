package com.codecool.ehotel.service.guest;

import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.GuestType;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class GuestServiceImpl implements GuestService {


    private final List<String> NAMES = List.of("Guest1", "Guest2", "Guest3", "Guest4", "Guest5", "Guest6");

    public Guest generateRandomGuest(LocalDate seasonStart, LocalDate seasonEnd) {
        String name = generateRandomName();
        GuestType guestType = generateRandomGuestType();
        LocalDate[] stayPeriod = generateRandomStayPeriod(seasonStart, seasonEnd);

        return new Guest(name, guestType, stayPeriod[0], stayPeriod[1]);
    }

    private String generateRandomName() {
        int index = ThreadLocalRandom.current().nextInt(NAMES.size());
        return NAMES.get(index);
    }

    private GuestType generateRandomGuestType() {
        GuestType[] guestTypes = GuestType.values();
        int index = ThreadLocalRandom.current().nextInt(guestTypes.length);
        return guestTypes[index];
    }

    private LocalDate[] generateRandomStayPeriod(LocalDate seasonStart, LocalDate seasonEnd) {
        long startEpochDay = seasonStart.toEpochDay();
        long endEpochDay = seasonEnd.toEpochDay();
        long seasonDays = endEpochDay - startEpochDay;

        long stayNights = ThreadLocalRandom.current().nextLong(1, Math.min(seasonDays, 7) + 1);
        long checkInDayOffset = ThreadLocalRandom.current().nextLong(0, seasonDays - stayNights + 1);

        LocalDate checkInDate = LocalDate.ofEpochDay(startEpochDay + checkInDayOffset);
        LocalDate checkOutDate = checkInDate.plusDays(stayNights);

        return new LocalDate[]{checkInDate, checkOutDate};
    }

    @Override
    public Set<Guest> getGuestsForDay(List<Guest> guests, LocalDate date) {
        Set<Guest> guestsToday = new HashSet<>();
        for (Guest guest : guests) {
            if ((date.isEqual(guest.checkIn()) || date.isAfter(guest.checkIn())) &&
                    (date.isEqual(guest.checkOut()) || date.isBefore(guest.checkOut()))) {
                guestsToday.add(guest);
            }
        }
        return guestsToday;
    }

}