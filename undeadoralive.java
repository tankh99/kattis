import java.util.*;

public class undeadoralive {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        String output = "machine";
        int smileyCount = countSubstringOccurrences(text, ":)");
        int frownCount = countSubstringOccurrences(text, ":(");

        if (smileyCount > 0 && frownCount > 0) {
            output = "double agent";
        } else if (smileyCount > 0) {
            output = "alive";
        } else if (frownCount > 0) {
            output = "undead";
        }
        System.out.println(output);
        sc.close();
    }

    public static int countSubstringOccurrences(String text, String substring) {
        int count = 0;
        int index = text.indexOf(substring);
        while (index != -1) {
            count++;
            index = text.indexOf(substring, index+1);
        }
        return count;
    }
}