package com.codecool.ehotel.service.guest;

import com.codecool.ehotel.model.Guest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GuestImpl implements GuestService {


    @Override
    public Guest generateRandomGuest(LocalDate seasonStart, LocalDate seasonEnd) {



        return null;
    }

    @Override
    public Set<Guest> getGuestsForDay(List<Guest> guests, LocalDate date) {
                   Set<Guest> guestsToday = new HashSet<>();

                        for (Guest guest : guests) {
                            if(guest.checkIn().isAfter(date) && guest.checkOut().isBefore(date) ){
                                guestsToday.add(guest);
                            }

                        }
        return guestsToday;
    }
}
