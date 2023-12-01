import java.io.*;
import java.util.*;

public class shortestpath1 {

    public static final int INF = 1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        while (true) {
            String[] line = reader.readLine().split(" ");
            int N = Integer.parseInt(line[0]);
            int M = Integer.parseInt(line[1]);
            int Q = Integer.parseInt(line[2]);
            int S = Integer.parseInt(line[3]); // source
            if (N == 0 && M == 0 && Q == 0 && S == 0) break;
            List<List<IntegerPair>> al = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                al.add(new ArrayList<>());
            }
            for (int i = 0; i < M; i++) {
                line = reader.readLine().split(" ");
                int u = Integer.parseInt(line[0]);
                int v = Integer.parseInt(line[1]);
                int w = Integer.parseInt(line[2]);
                IntegerPair pair = new IntegerPair(v, w);
                List<IntegerPair> neighbours = al.get(u);
                neighbours.add(pair);
                al.set(u, neighbours);
            }

            int[] dist = new int[N];
            Arrays.fill(dist, INF);
            dist[S] = 0;
            PriorityQueue<IntegerPair> pq = new PriorityQueue<>();
            pq.offer(new IntegerPair(S, 0));
            while (!pq.isEmpty()) {
                IntegerPair u = pq.poll();
                List<IntegerPair> neighbours = al.get(u.first());
                for (int i = 0; i < neighbours.size(); i++) {
                    IntegerPair v = neighbours.get(i);
                    if (dist[v.first()] > dist[u.first()] + v.second()) {
                        dist[v.first()] = dist[u.first()] + v.second();
                        pq.offer(neighbours.get(i));
                    }
                }
            }

            for (int i = 0; i < Q; i++) {
                int dest = Integer.parseInt(reader.readLine());
                if (dist[dest] == INF) {
                    pw.println("Impossible");
                } else {
                    pw.println(dist[dest]);
                }
            }
            pw.println();
        }


        pw.close();

    }
}

class IntegerPair implements Comparable<IntegerPair> {
    Integer _f, _s;
    public IntegerPair(Integer f, Integer s) { _f = f; _s = s; }
    public int compareTo(IntegerPair o) {
        if (!this.second().equals(o.second())) // this.first() != o.first()
            return this.second() - o.second();   // is wrong as we want to
        else                                 // compare their values,
            return this.first() - o.first(); // not their references
    }
    Integer first() { return _f; }
    Integer second() { return _s; }
}