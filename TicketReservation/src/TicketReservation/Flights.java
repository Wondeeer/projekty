package TicketReservation;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Flights {
    private static Map<String, Integer> specialId = new HashMap<>();
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private String airline;
    private String departure;
    private String arrival;
    private double price;
    private String standard;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    protected String id;


    public Flights(String departure, String arrival, LocalDate departureDate, LocalDate arrivalDate, String airline, double price, String standard, LocalTime departureTime, LocalTime arrivalTime) {
        this.departure = departure;
        this.arrival = arrival;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.airline = airline;
        this.price = price;
        this.standard = standard;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.id = departure + getSId(departure);
    }

    protected int getSId(String departure){
        int count = specialId.getOrDefault(departure,0) + 1;
        specialId.put(departure, count);
        return count;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public String getAirline() {
        return airline;
    }

    public String getDeparture() {
        return departure;
    }

    public String getArrival() {
        return arrival;
    }

    public double getPrice() {
        return price;
    }

    public String getStandard() {
        return standard;
    }

    public String getId() {
        return id;
    }

    public LocalTime getDepartureTime(){
        return departureTime;
    }

    public LocalTime getArrivalTime(){
        return arrivalTime;
    }

    public Duration getDuration() {
        Duration duration = Duration.between(departureTime, arrivalTime);
       // long minutes = duration.toMinutes();
        return duration;// (int) minutes
    }
    @Override
    public String toString() {
        String standardString;
        if (standard.equals("business.")){
            standardString = "tento let je s business třídou";
       }else {
            standardString = "tento let je pouze s economickou třídou";
        }
        return String.format("ID letu: %s, Odlet z: %s, Finální destinace: %s, Aerolinka: %s, Odlet: %s, Přílet: %s, Cena: %s, Třída: %s, Čas odletu: %s, Čas příletu: %s" + '\'' + " ", id, departure, arrival, airline, departureDate, arrivalDate, price, standardString, departureTime, arrivalTime);
    }
}
