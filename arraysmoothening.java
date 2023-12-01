import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class arraysmoothening {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        String[] line = reader.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int K = Integer.parseInt(line[1]);
        PriorityQueue<Number> pq = new PriorityQueue<>(Collections.reverseOrder());
        Map<Integer, Number> freqs = new HashMap<>();
        line = reader.readLine().split(" ");
        for (int j = 0; j < line.length; j++) {
            int num = Integer.parseInt(line[j]);
            Number numObj = freqs.getOrDefault(num, new Number(num, 0));
            numObj.freq++;
            freqs.put(num, numObj);

        }

        for (Map.Entry<Integer, Number> entry: freqs.entrySet()) {
            Number number = entry.getValue();
            pq.offer(number);
//            max = Math.max(max, entry.getValue());
        }
        for (int i = 0; i < K && !pq.isEmpty(); i++) {
            Number removed = pq.poll();
            if (!pq.isEmpty() && pq.peek().freq != 0) {
                removed.freq--;
                pq.add(removed);
            }
        }
        int min = 0;
        if (!pq.isEmpty()) min = pq.peek().freq;
        pw.println(min);

        pw.close();

    }
}

class Number implements Comparable<Number> {
    public int num;
    public int freq;
    public Number(int num, int freq) {
        this.num = num;
        this.freq = freq;
    }

    @Override
    public int compareTo(Number o) {
        return this.freq - o.freq;
    }

}