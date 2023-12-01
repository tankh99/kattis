import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.*;

public class kannafriendship {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        long Q = Long.parseLong(line[1]);

        TreeMap<Integer, Interval> tree = new TreeMap<>();
        StringBuilder sb = new StringBuilder();
        int pointsCovered = 0;
        for (int i = 0; i < Q; i++) {
            line = reader.readLine().split(" ");

            int queryType = Integer.parseInt(line[0]);
            if (queryType == 1) {

                int start = Integer.parseInt(line[1]);
                int end = Integer.parseInt(line[2]);
                Interval interval = new Interval(start, end);
                Interval updatedInterval = new Interval(start, end);;

                Map.Entry<Integer, Interval> pEntry = tree.floorEntry(start);

//                NavigableMap<Integer, Interval> within = tree.subMap(start, true, end, true);
//                for (Map.Entry<Integer, Interval> entry: within.entrySet()) {
//                    tree.remove(entry.getKey());
//                }
//                SortedMap<Integer, Interval> after = tree.tailMap(start);
//                System.out.println("Floor");
                while (pEntry != null && pEntry.getValue().isConnected(interval)) {
                    Interval ivl = pEntry.getValue();
                    updatedInterval.setStart(Math.min(ivl.start, updatedInterval.start));
                    updatedInterval.setEnd(Math.max(ivl.end, updatedInterval.end));
//                    System.out.println("IVL " + ivl.start + " " + ivl.end + " | " + updatedInterval.start + " " + updatedInterval.end);
                    tree.remove(ivl.start);
                    pointsCovered -= ivl.getDistance();
                    pEntry = tree.floorEntry(start);
                }
//                System.out.println("Ceiling");
                Map.Entry<Integer, Interval> sEntry = tree.ceilingEntry(start);
                while (sEntry != null && sEntry.getValue().isConnected(interval)) {
                    Interval ivl = sEntry.getValue();
//                    System.out.println("IVL " + ivl.start + " " + ivl.end + " | " + updatedInterval.start + " " + updatedInterval.end);
                    updatedInterval.setStart(Math.min(ivl.start, updatedInterval.start));
                    updatedInterval.setEnd(Math.max(ivl.end, updatedInterval.end));
                    tree.remove(ivl.start);
                    pointsCovered -= ivl.getDistance();
                    sEntry = tree.ceilingEntry(start);
                }
//                if (tree.containsKey(updatedInterval.start)) {
//                    Interval ivl = tree.get(updatedInterval.start);
//                    updatedInterval.start = Math.min(ivl.start, updatedInterval.start);
//                    updatedInterval.end = Math.max(ivl.end, updatedInterval.end);
////                    pointsCovered -= ivl.getDistance();
//                }
//                System.out.println("Points & Curr Distance " + pointsCovered + " " + updatedInterval.getDistance());
                pointsCovered += updatedInterval.getDistance();
                tree.put(updatedInterval.start, updatedInterval);
            } else {
                sb.append(pointsCovered).append("\n");
//                getPointsCovered(tree);
//                System.out.println(pointsCovered);
            }

        }
        System.out.println(sb.toString());

    }
    public static int getCurrentPointsCovered(Interval ivl, int currPoints) {
//        int left = Math.max(ivl.start - start, 0);
//        int right = Math.max(end - ivl.end, 0);
//        return left + right;
        return Math.max(ivl.getDistance() - currPoints, 0);
    }

    public static int getPointsCovered(TreeMap<Integer, Interval> tree) {
        int points = 0;
        for (Map.Entry<Integer, Interval> entry : tree.entrySet()) {
            Interval interval = entry.getValue();
            System.out.println(interval.start + " " + interval.end);
            points += interval.getDistance();
        }
        return points;
    }
}

class Interval implements  Comparable<Interval> {
    public int start;
    public int end;
    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getDistance() {
        return this.end - this.start + 1;
    }

    public boolean isConnected(Interval o) {
        return (o.start >= this.start && o.start <= this.end) ||
                (o.end >= this.start && o.end <= this.end) ||
                (this.start >= o.start && this.start <= o.end) ||
                (this.end >= o.start && this.end <= o.end);

    }

    @Override
    public int compareTo(Interval o) {
        return this.end - o.end != 0 ? this.end - o.end : o.start - this.start;
    }
}