import java.util.*;

public class sifferprodukt {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String numStr = sc.nextLine();
        int result = reduceNum(numStr);
        System.out.println(result);
        sc.close();
    }

    public static int reduceNum(String numStr) {
        int num = Integer.parseInt(numStr);
        if (num < 10) return num;
        int newProduct = 1;
        for (int i = 0; i < numStr.length(); i++) {
            int digit = Integer.parseInt(String.valueOf(numStr.charAt(i)));
            if (digit != 0) {
                newProduct *= digit;
            }
        }
        return reduceNum(String.valueOf(newProduct));
    }
}