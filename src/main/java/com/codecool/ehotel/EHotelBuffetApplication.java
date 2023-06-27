package com.codecool.ehotel;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.service.guest.GuestServiceImpl;
import java.time.LocalDate;


public class EHotelBuffetApplication {

    public static void main(String[] args) {


        GuestServiceImpl guestService = new GuestServiceImpl();
        LocalDate seasonStart = LocalDate.of(2023, 7, 1);
        LocalDate seasonEnd = LocalDate.of(2023, 7, 31);

        for (int i = 0; i < 10; i++) {
            Guest guest = guestService.generateRandomGuest(seasonStart, seasonEnd);
            System.out.println("Guest name: " + guest.name());
            System.out.println("Guest type: " + guest.guestType());
            System.out.println("Check-in date: " + guest.checkIn());
            System.out.println("Check-out date: " + guest.checkOut());
            System.out.println("-------------------------");
        }


    }

  
}
