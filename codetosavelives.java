import java.util.*;

public class codetosavelives {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Loop through all pairs
        int pairs = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < pairs; i++) {
            int sum = 0;

            // Loop through one pair
            for (int j = 0; j < 2; j++) {
                // Loop through digits of a number
                String[] tokens = sc.nextLine().trim().split(" ");
                for (int k = 0; k < tokens.length; k++) {
                    double digitsPlace = Math.pow(10, tokens.length - k - 1);
                    int digit = Integer.parseInt(tokens[k]);
                    sum += digit * digitsPlace;
                }
            }
            for (String digit: String.valueOf(sum).split("")) {
                System.out.print(digit + " ");
            }
            System.out.println("");
        }
        sc.close();
    }
}