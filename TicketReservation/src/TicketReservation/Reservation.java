package TicketReservation;

import TicketReservation.Strategy.CheapestFlight;
import TicketReservation.Strategy.ComfortableFlight;
import TicketReservation.Strategy.ShortestFlight;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

public class Reservation {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final FlightsDatabase flightReservation = FlightsDatabase.getInstance();

    public static void main(String[] args) throws IOException {

        FlightsDatabase.loadFlightsData();
        System.out.println("VŠECHNY LETY");
        System.out.println(flightReservation.allFlights());
        System.out.println("LETY S VÝBĚREM AEROLINEK");
        System.out.println(flightReservation.searchFlightsByAirlines("Air France,Ryanair"));
        System.out.println("LETY PODLE VÝBĚRU");
        System.out.println(flightReservation.flightSelection("Praha",null,null,null,null));

        System.out.println("""
                Zadejte výběr strategie:
                1 - Vyhledat nejlevnější let
                2 - Vyhledat nejkomfortnější let
                3 - Vyhledat nejkratší let
                4 - Vyhledat všechny""");
//
        String chooseStrategy = reader.readLine();

        switch (chooseStrategy) {
            case "1" -> flightReservation.setStrategy(new CheapestFlight());
            case "2" -> flightReservation.setStrategy(new ComfortableFlight());
            case "3" -> flightReservation.setStrategy(new ShortestFlight());
            case "4" -> System.out.println("Bez výběru strategie");
        }


        if (chooseStrategy.equals("1") || chooseStrategy.equals("2")  || chooseStrategy.equals("3") ){
            System.out.println(flightReservation.SearchFlights(LocalDate.of(2023,6,3),LocalDate.of(2023,6,3),"Vídeň","New York"));
        }
        else {
            System.out.println(flightReservation.flightSelection("Praha","Paříž",LocalDate.of(2023,6,1),LocalDate.of(2023,6,1),"Ryanair"));
        }





    }
}