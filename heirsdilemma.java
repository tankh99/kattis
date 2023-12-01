import java.util.*;
public class heirsdilemma {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int min = sc.nextInt();
        int max = sc.nextInt();

        int combinations = 0;
        for (int i = min; i <= max; i++) {
            String numString = String.valueOf(i);

//            if (numString.contains("0")) continue;
            boolean possibleCombination = true;
//            if (numString.contains("0")) continue;
            boolean[] existence = new boolean[10];
            for (int j = 0; j < numString.length(); j++) {
                int digit = Integer.parseInt(String.valueOf(numString.charAt(j)));
//                System.out.println(existence[digit]);
                if (existence[digit] || digit == 0 || i % digit != 0 ) {
                    possibleCombination = false;
                    break;
                }
                existence[digit] = true;
            }
            if (possibleCombination) {
                combinations++;

            }
        }
        System.out.println(combinations);
    }
}
