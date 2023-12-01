import java.util.*;
import java.io.*;
public class sim {
    public static void main(String[] args)  throws  IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in), 8192);
        PrintWriter pw = new PrintWriter(System.out);
        int testCases = Integer.parseInt(reader.readLine());
        StringBuilder sb = new StringBuilder();

        long current = System.currentTimeMillis();
        for (int i = 0; i < testCases; i++) {
            String input = reader.readLine();
            String output = solve(input);
            sb.append(output).append('\n');

        }

        pw.println(sb.toString());
        long end = System.currentTimeMillis();
//        pw.println("Time Elapsed (ms) " + (end - current));
        pw.close();

    }

    // ] = go to end of input
    // [ = go to start of input
    // < = remove character
    public static String solve(String input) {

//        StringBuilder sb = new StringBuilder(input.length());
        List<Character> list = new LinkedList<>();
        int cursorIndex = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == ']') {
//                cursorIndex = sb.length();
                cursorIndex = list.size();
            } else if (c == '[') {
                cursorIndex = 0;
            } else if (c == '<') {
                if (cursorIndex > 0) {
//                    sb.deleteCharAt(--cursorIndex);
                    list.remove(--cursorIndex);
                }
            } else {
//                sb.insert(cursorIndex++, c);
                list.add(cursorIndex++, c);
            }
        }
//        return sb.toString();
        StringBuilder sb = new StringBuilder();
        for (char c : list) {
            sb.append(c);
        }
        return sb.toString();
    }

}
