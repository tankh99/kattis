import java.util.*;

public class babypanda {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long days = sc.nextLong();
        long slimes = sc.nextLong();
        long timesSneezed = 0;
        while (slimes > 0) {
            if (slimes % 2 != 0) timesSneezed++;

            slimes = slimes/2;
        }
        System.out.println(timesSneezed);
    }
}