import java.util.*;

public class coffeecupcombo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int lectures = sc.nextInt();
        sc.nextLine();
        String str = sc.nextLine();
        int cups = 0;
        int lecturesAwake = 0;
        for (int i = 0; i < lectures; i++) {
            char present = str.charAt(i);
            boolean hasCoffeeMachine = present == '1';
            if (cups > 0 || hasCoffeeMachine) {
                lecturesAwake++;
                if (hasCoffeeMachine) {
                    cups = Math.min(cups + 2, 2);
                } else {
                    cups--;
                }
            }
            if (hasCoffeeMachine) {
                cups = Math.min(cups+2, 2);
            }
        }

        System.out.println(lecturesAwake);
        sc.close();
    }

}