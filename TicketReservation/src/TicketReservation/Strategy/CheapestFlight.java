package TicketReservation.Strategy;

import TicketReservation.Flights;
import TicketReservation.FlightsDatabase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CheapestFlight implements SearchingStrategy{
    @Override
    public List<Flights> searchFlight(LocalDate departureDate, LocalDate arrivalDate, String departure, String arrival) {
        List<Flights> allFlights =  FlightsDatabase.getFlights();
        List<Flights> cheapestFlight = new ArrayList<>();
        double minPrice = Double.MAX_VALUE;
        for (Flights flights : allFlights) {
            if (flights.getDeparture().equals(departure) &&
                    flights.getArrival().equals(arrival) &&
                    flights.getDepartureDate().equals(departureDate) &&
                    flights.getArrivalDate().equals(arrivalDate)) {
                if (flights.getPrice() < minPrice) {
                    cheapestFlight.clear();
                    cheapestFlight.add(flights);
                    minPrice = flights.getPrice();
                } else if (flights.getPrice() == minPrice) {
                    cheapestFlight.add(flights);
                }
            }
        }
        return cheapestFlight;
    }

}
