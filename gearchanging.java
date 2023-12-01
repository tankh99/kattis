import java.util.*;
import java.io.*;

public class gearchanging {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        double p = sc.nextDouble();
        p /= 100;

        List<Double> v1 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            v1.add(sc.nextDouble());
        }

        List<Double> v2 = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            v2.add(sc.nextDouble());
        }

        TreeSet<Double> s = new TreeSet<>();
        for (double i : v1) {
            for (double j : v2) {
                s.add(i / j);
            }
        }

        double curr = s.first();
        s.remove(curr);
        boolean works = true;
        for (double i : s) {
            if (curr + curr * p < i) {
                works = false;
            }
            curr = i;
        }

        if (works) {
            System.out.println("Ride on!");
        } else {
            System.out.println("Time to change gears!");
        }
    }
}
