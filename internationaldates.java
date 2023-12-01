import java.util.*;

public class internationaldates {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String date = sc.nextLine().trim();
        String[] dateComponents = date.split("/");
        System.out.println(date);
        if (Integer.parseInt(dateComponents[0]) > 12) {
            System.out.println("EU");
            return;
        } else if (Integer.parseInt(dateComponents[1]) > 12) {
            System.out.println("US");
            return;
        }
        System.out.println("either");
        return;
    }
}