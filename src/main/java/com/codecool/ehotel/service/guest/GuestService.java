package com.codecool.ehotel.service.guest;

import com.codecool.ehotel.model.Guest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface GuestService {


    Set<Guest> getGuestsForDay(List<Guest> guests, LocalDate date);

}
