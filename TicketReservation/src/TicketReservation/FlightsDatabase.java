package TicketReservation;

import TicketReservation.Strategy.SearchingStrategy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FlightsDatabase {
    private static SearchingStrategy strategy;
    private static FlightsDatabase instance = null;
    private static List<Flights> flights;
    private static final Path path = Paths.get("FlightsList.txt");

    private FlightsDatabase(){
        flights = new ArrayList<>();
    }

    public static FlightsDatabase getInstance(){
        if (instance == null){
            instance = new FlightsDatabase();
        }
        return instance;
    }

    public void setStrategy(SearchingStrategy strategy){
        FlightsDatabase.strategy = strategy;
    }

    public void setFlights(List<Flights> flights){
        FlightsDatabase.flights = flights;
    }

    public List<Flights> SearchFlights(LocalDate departureDate, LocalDate arrivalDate, String departure, String arrival){
        return strategy.searchFlight(departureDate, arrivalDate, departure,arrival);
    }
    public static List<Flights> getFlights(){
        return flights;
    }

    public int totalFlights(){
        return flights.size();
    }

    public String allFlights(){
        return flights.toString();
    }

    public void removeFlight(String id) {
        for (Flights let : flights) {
            if (let.getId().equals(id)) {
                flights.remove(let);
                return;
            }
        }
        throw new IllegalArgumentException("Let s id " + id + " nebyl nalezen.");
    }

    public List<Flights> flightSelection(String departure, String arrival, LocalDate departureDate, LocalDate arrivalDate, String airline) {
        List<Flights> result = new ArrayList<>();
        for (Flights flight : flights) {
            if (departureDate != null && !flight.getDepartureDate().equals(departureDate)) {
                continue;
            }
            if (arrivalDate != null && !flight.getArrivalDate().equals(arrivalDate)) {
                continue;
            }
            if (departure != null && !flight.getDeparture().equals(departure)) {
                continue;
            }
            if (arrival != null && !flight.getArrival().equals(arrival)) {
                continue;
            }
            if (airline != null && !flight.getAirline().equals(airline)) {
                continue;
            }
            result.add(flight);
        }
        int records = result.size();

        if (records == 0) {
            System.out.println("Bohužel, nebyl nalezen žádný let");
            return Collections.emptyList();
        } else {
            return result;
        }
    }


    public List<Flights> searchFlightsByAirlines(String airlines) {

        String[] arrayOfAirline = airlines.split(",");

        List<Flights> matchingFlights = new ArrayList<>();
        for (Flights flight : FlightsDatabase.getFlights()) {
            if (Arrays.asList(arrayOfAirline).contains(flight.getAirline())) {
                matchingFlights.add(flight);
            }
        }
        return matchingFlights;
    }


    public static void loadFlightsData() {
        List<Flights> flights = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                String departure = values[0].trim();
                String arrival = values[1].trim();
                LocalDate departureDate = LocalDate.parse(values[2].trim(), DateTimeFormatter.ofPattern("d.M.yyyy"));
                LocalDate arrivalDate = LocalDate.parse(values[3].trim(), DateTimeFormatter.ofPattern("d.M.yyyy"));
                String airline = values[4].trim();
                double price = Double.parseDouble(values[5].trim());
                String standard = (values[6].trim());
                LocalTime departureTime = LocalTime.parse(values[7].trim(),DateTimeFormatter.ofPattern("H:mm"));
                LocalTime arrivalTime = LocalTime.parse(values[8].trim(),DateTimeFormatter.ofPattern("H:mm"));


                Flights flight = new Flights(departure, arrival, departureDate, arrivalDate, airline, price, standard,departureTime,arrivalTime);
                flights.add(flight);
            }
            FlightsDatabase.getInstance().setFlights(flights);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
