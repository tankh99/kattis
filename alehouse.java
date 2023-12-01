import java.util.*;
import java.io.*;
public class alehouse {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int k = Integer.parseInt(line[1]); // milliseconds that i'll be there
        List<Event> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            line = reader.readLine().split(" ");
            int enterTime = Integer.parseInt(line[0]);
            int exitTime = Integer.parseInt(line[1]);
//            ConsistentCitizen citizen = new ConsistentCitizen(enterTime, exitTime);
            Event a = new Event(enterTime, 0);
            Event b = new Event(exitTime + k, 1);
            list.add(a);
            list.add(b);
        }
        Collections.sort(list);
        int max = 0;
        int window = 0;
        for (Event e: list) {
            window += e.leaving == 0 ? 1 : -1;
            max = Math.max(max, window);
        }

        System.out.println(max);

//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i).time + " Leaving: " + list.get(i).leaving);
//        }
    }
}

class Event implements Comparable<Event> {
    public int time;
    public int leaving;
    public Event(int time, int leaving) {
        this.time = time;
        this.leaving = leaving;
    }


    @Override
    public int compareTo(Event o) {
        if (this.time == o.time) return this.leaving - o.leaving;
        return this.time - o.time;
    }
}
