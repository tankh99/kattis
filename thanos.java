import java.util.Scanner;

public class thanos {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int planets = sc.nextInt();
        for (int i = 0; i < planets; i++) {
            long population = sc.nextLong();
            long growthRate = sc.nextLong();
            long productionRate = sc.nextLong();
            int yearsToDestruction = 0;
            // All are integers, not floats
            while (productionRate >= population) {
                population *= growthRate;
                yearsToDestruction++;
            }
            System.out.println(yearsToDestruction);
        }
        sc.close();
    }

}