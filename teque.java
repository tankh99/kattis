import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.*;

public class teque {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        String[] line = reader.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        Map<Integer, Integer> front = new HashMap<>();
        Map<Integer, Integer> back = new HashMap<>();

        int frontHead = 0;
        StringBuilder sb = new StringBuilder();
        TequeDS q = new TequeDS();
        for (int i = 0; i < N; i++) {
            line = reader.readLine().split(" ");
            String command = line[0];
            int val = Integer.parseInt(line[1]);
            if (command.equals("push_back")) {
                q.pushLast(val);
//                l.add(val);
            } else if (command.equals("push_front")) {
                q.pushFirst(val);
//                l.add(0, val);
            } else if (command.equals("push_middle")) {
                q.pushMiddle(val);
//                l.add((l.size()+1)/2,val);
            } else {
//                System.out.println(q.get(val));
                sb.append(q.get(val));
                sb.append('\n');
            }
            q.balance();
        }
        System.out.println(sb.toString());

        pw.close();

    }
}

class TequeDS {
    public Map<Integer, Integer> front;
    public Map<Integer, Integer> back;
    /*
        front head - for the first elements
        front tail - for the middle elements
        back tail - for last elements
     */
    public int frontHead = -1;
    public int frontTail = 0;
    public int backHead = -1;
    public int backTail = 0;
    int N;

    public TequeDS() {
        N = 0;
        front = new HashMap<>();
        back = new HashMap<>();

    }

    public void pushMiddle(int x) {
        front.put(frontTail++, x);
    }

    public void pushFirst(int x) {
        front.put(frontHead--, x);
    }

    public void pushLast(int x) {
        back.put(backTail++, x);
    }

    public void balance() {
        if (front.size() < back.size()) { // Move back element to front map
            front.put(frontTail++, back.get(backHead+1));
            back.remove(++backHead);
        } else if (front.size() - 1 > back.size()) { // Move front tail element to back
            back.put(backHead--, front.get(frontTail-1));
            front.remove(--frontTail);
        }
    }

    public int get(int index) {
        if (index < front.size()) {
            return front.get(index + frontHead + 1);
        } else {
            return back.get(index - front.size() + backHead + 1);
        }
//        if (index > front.size() - 1) {
//            return back.get(index-front.size());
//        } else {
//            int frontN = front.size()-frontTail;
//            // Is within the first part
//            if (index < frontN) {
//                return front.get(-index-1);
//            } else { // is at the middle part (front tail) + because fronthead is negative
//                return front.get((front.size()-frontN)-1);
//            }
//
//        }

    }


}
