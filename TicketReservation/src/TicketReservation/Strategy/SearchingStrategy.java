package TicketReservation.Strategy;

import TicketReservation.Flights;

import java.time.LocalDate;
import java.util.List;

public interface SearchingStrategy {
    List<Flights> searchFlight(LocalDate departureDate, LocalDate arrivalDate, String departure, String arrival);
}
