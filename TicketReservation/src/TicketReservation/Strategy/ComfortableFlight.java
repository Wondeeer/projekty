package TicketReservation.Strategy;

import TicketReservation.Flights;
import TicketReservation.FlightsDatabase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ComfortableFlight implements SearchingStrategy{
    @Override
    public List<Flights> searchFlight(LocalDate departureDate, LocalDate arrivalDate, String departure, String arrival) {
        List<Flights> allFlights = FlightsDatabase.getFlights();
        List<Flights> comfortableFlight = new ArrayList<>();
        for (Flights flight : allFlights) {
            if (flight.getDeparture().equals(departure)
                    && flight.getArrival().equals(arrival)
                    && (flight.getDepartureDate().isEqual(departureDate) || flight.getDepartureDate().isAfter(departureDate))
                    && (flight.getArrivalDate().isEqual(arrivalDate) || flight.getArrivalDate().isBefore(arrivalDate))
                    && flight.getStandard().equals("business.")){
                comfortableFlight.add(flight);
            }
        }
        return comfortableFlight;
    }
}
