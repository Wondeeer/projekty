package TicketReservation.Strategy;

import TicketReservation.Flights;
import TicketReservation.FlightsDatabase;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ShortestFlight implements SearchingStrategy{
    @Override
    public List<Flights> searchFlight(LocalDate departureDate, LocalDate arrivalDate, String departure, String arrival) {
        List<Flights> allFlights =  FlightsDatabase.getFlights();
        List<Flights> shortestFlight = new ArrayList<>();
        Duration shortestDuration = Duration.ofHours(20);

        for (Flights flights : allFlights) {
            if (flights.getDeparture().equals(departure) &&
                    flights.getArrival().equals(arrival) &&
                    flights.getDepartureDate().isEqual(departureDate) &&
                    flights.getArrivalDate().isEqual(arrivalDate)) {
                Duration duration = flights.getDuration(); //Duration.between(flights.getDepartureTime(), flights.getArrivalTime());
                if (duration.isNegative()) {
                    duration = duration.negated();
                }
                if (duration.toMinutes() < shortestDuration.toMinutes()) {
                    shortestFlight.clear();
                    shortestFlight.add(flights);
                    shortestDuration = duration;
                } else if (duration.toMinutes() == shortestDuration.toMinutes()) {
                    shortestFlight.add(flights);
                }
            }
        }
        return shortestFlight;
    }
}
