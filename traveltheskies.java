import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class traveltheskies {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split(" ");
        int K = Integer.parseInt(line[0]); // airports
        int N = Integer.parseInt(line[1]); // days inside airport flight departure window
        int M = Integer.parseInt(line[2]); // # flights in window


//        List<Flight> flights = new ArrayList<>();
        /*
        Nested Adjacency list
        Inner AL
        1. Index - airport
        2. Value - list of flights that day flying from this airport
        Outer AL
        1. Index - the day
        2. Value - the airport adjacency list

        Algorithm
        1. foreach day, check all flights in each airport
        2. add all remaining passengers to the same airport, and add all boarding passengers to their destination airport
        3. if flight capacity != 0 by end of the day, then throw suboptimal, else continue
        4. repeat n times and break early before we update the next day's elements in the case we're at n already
        Map<List<Flight>>

        Map of day to list of flights for that day
         */
        Map<Integer, List<Flight>> al = new HashMap<>();
        for (int i = 0; i < M; i++) {
            line = reader.readLine().split(" ");
            int U = Integer.parseInt(line[0])-1; // starting
            int V = Integer.parseInt(line[1])-1; // Ending location
            int D = Integer.parseInt(line[2])-1; // Flight day
            int Z = Integer.parseInt(line[3]); // capacity
            Flight flight = new Flight(U, V, D, Z);
            // Add flight to the airport at the specific day
            List<Flight> flights = al.getOrDefault(D, new ArrayList<>());
            flights.add(flight);
            al.put(D, flights);
        }

        // 2D matrix of airport capacity, row = day, col = airport number
        // list of maps, where index = day, value = all airports for that day
        Map<Integer, Map<Integer, Integer>> passengerMap = new HashMap<>();
        for (int i = 0; i < K*N; i++) {
            line = reader.readLine().split(" ");
            int A = Integer.parseInt(line[0])-1; // airport
            int B = Integer.parseInt(line[1])-1; // day of flight
            int C = Integer.parseInt(line[2]); // customers
            Map<Integer, Integer> passengersInDay = passengerMap.getOrDefault(B, new HashMap<>());
            passengersInDay.put(A, passengersInDay.getOrDefault(A, 0) + C);
            passengerMap.put(B, passengersInDay);
        }

//        for (int i = 0; i < al.size(); i++) {
        for (Map.Entry<Integer, List<Flight>> entry: al.entrySet()) {
            int i = entry.getKey();
            List<Flight> flights = entry.getValue();
            // All flights for that day
            for (int j = 0; j < flights.size(); j++) {
                Flight f = flights.get(j);
                if (f.to == f.from) continue;
                Map<Integer, Integer> dayPassengers = passengerMap.getOrDefault(f.flightDay, new HashMap<>());
                int fromPassengers = dayPassengers.getOrDefault(f.from, 0);
//                int toPassengers = dayPassengers.get(f.to);
                int boardingPassengers = Math.min(f.capacity, fromPassengers);
                int remainingPassengers = fromPassengers - boardingPassengers;
//                System.out.println("Airport: " + f.from + " Boarding " + boardingPassengers + " Remaining " + remainingPassengers);
                f.capacity -= boardingPassengers;
                flights.set(j, f);
                al.put(i, flights);
//                passengerMap[i][f.from] -= boardingPassengers;
                dayPassengers.put(f.from, fromPassengers - boardingPassengers);
                if (i + 1 >= passengerMap.size()) continue;
                Map<Integer, Integer> nextDayPassengers = passengerMap.getOrDefault(i+1, new HashMap<>());
                nextDayPassengers.put(f.to, nextDayPassengers.getOrDefault(f.to, 0) + boardingPassengers);
                nextDayPassengers.put(f.from, nextDayPassengers.getOrDefault(f.from, 0) + remainingPassengers);
                passengerMap.put(i+1, nextDayPassengers);
//                passengerMap[i+1][f.to] += boardingPassengers;
//                passengerMap[i+1][f.from] += remainingPassengers;
            }
//            int[] airportPassengers = passengerMap.get[i];
            Map<Integer, Integer> airportPassengers = passengerMap.getOrDefault(i, new HashMap<>());
            // Move all remaining passengers to next day
            for (Map.Entry<Integer, Integer> airportPassengersEntry: airportPassengers.entrySet()) {
                int passengers = airportPassengersEntry.getValue();
                if (passengers != 0 ) {
                    airportPassengers.put(airportPassengersEntry.getKey(), 0);
                    passengerMap.put(i, airportPassengers);
                    if (i >= al.size() - 1) continue;
                    Map<Integer, Integer> nextDayPassengers = passengerMap.getOrDefault(i+1, new HashMap<>());
                    nextDayPassengers.put(airportPassengersEntry.getKey(), nextDayPassengers.getOrDefault(airportPassengersEntry.getKey(), 0)+passengers);
                    passengerMap.put(i+1, nextDayPassengers);
                }
            }
//            for (int j = 0; j < airportPassengers.size(); j++) {
//                int passengers = airportPassengers.get;
//                if (passengers != 0) {
//
//                    if (i >= al.size() - 1) continue;
//                    passengerMap[i][j] -= passengers;
//                    passengerMap[i+1][j] += passengers;
//                }
//            }
//            printPassengerMap(passengerMap);
        }

//        printPassengerMap(passengerMap);

        for (int i = 0; i < al.size(); i++) {
            List<Flight> flightsInDay = al.get(i);
            if (flightsInDay == null) continue;
//            System.out.println("Day " + (i+1));
            for (int j = 0; j < flightsInDay.size(); j++) {
                Flight f = flightsInDay.get(j);
                if (f.capacity != 0) {
                    System.out.println("suboptimal");
                    return;
                }
//                System.out.print(f.capacity + " ");

            }
//            System.out.println();
        }
        System.out.println("optimal");
//        printFlights(flights);
//        for (int i = 0; i < flights.size(); i++) {
//            Flight f = flights.get(i);
//            System.out.println(f.from + " " + f.capacity);
//            if (f.capacity != 0) {
//                System.out.println("suboptimal");
//                return;
//            }
//        }

//        Collections.sort(arrivals, new Comparator<AirportArrival>() {
//            @Override
//            public int compare(AirportArrival o1, AirportArrival o2) {
//                // TODO: Sort by airport too if needed
//                return o1.day - o2.day;
//            }
//        });
        // TODO: Consider sorting flights by day if they are not sorted

    }

}

class Flight {
    public int from;
    public int to;
    public int flightDay;
    public int capacity;
    public Flight(int from, int to,int flightDay, int capacity) {
        this.from = from;
        this.to = to;
        this.flightDay = flightDay;
        this.capacity = capacity;
    }

//    @Override
//    public int compareTo(Flight o) {
//        int flightDiff = this.flightDay - o.flightDay;
//        int flightDiff = this.flightDay - o.flightDay;
//        if (this.flightDay - o.flightDay == 0)
//    }
}