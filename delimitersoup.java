import java.util.*;
import java.io.*;

class SyntaxElement {
    public char symbol;
    public int index;
    public SyntaxElement(char symbol, int index) {
        this.symbol = symbol;
        this.index = index;
    }

    public boolean isMatching(char otherSymbol) {
        switch (this.symbol) {
            case '(':
                return otherSymbol == ')';
            case '{':
                return otherSymbol == '}';
            case '[':
                return otherSymbol == ']';
            default:
                return false;
        }

    }


}

public class delimitersoup {
    public static boolean isClosingChar(char c) {
        return c == ')' || c == ']' || c == '}';
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        int lineLength = Integer.parseInt(line);
        int[] faults = new int[lineLength];

        line = reader.readLine();

        Stack<SyntaxElement> stack = new Stack<>();
        Stack<SyntaxElement> closingStack = new Stack<>();
        String opening = "({[";
        for (int i = 0; i < lineLength; i++) {
            char c = line.charAt(i);
            if (c == ' ') continue;
            SyntaxElement se = new SyntaxElement(c, i);
            if (opening.indexOf(c) != -1) {
                stack.push(se);
            } else {
                if (stack.isEmpty() || !stack.peek().isMatching(c)) {
                    System.out.println(c + " " + i);
                    return;
                } else {
                    stack.pop();
                }
            }
        }

        System.out.println("ok so far");



    }

    public static void solve() {

    }
}
