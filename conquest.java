import java.io.*;
import java.util.*;

public class conquest {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        String[] line = reader.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int M = Integer.parseInt(line[1]);
        List<List<Integer>> al = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            al.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            line = reader.readLine().split(" ");
            int u = Integer.parseInt(line[0])-1;
            int v = Integer.parseInt(line[1])-1;
            al.get(u).add(v);
            al.get(v).add(u);;
        }
        int maxTroops = 0;
        int[] troops = new int[N];
        for (int i = 0; i < N; i++) {
            int t = Integer.parseInt(reader.readLine());
            troops[i] = t;
        }

        boolean[] visited = new boolean[N];
        PriorityQueue<Pair> q = new PriorityQueue<>();
        q.offer(new Pair(0, troops[0]));

        boolean firstIsland = true;
        while (!q.isEmpty()) {
            Pair u = q.poll();
            if (visited[u.first()]) continue;
            visited[u.first()] = true;
            if (maxTroops <= troops[u.first()] && !firstIsland) continue;
            maxTroops += troops[u.first()];
            firstIsland = false;
            for (int v: al.get(u.first())) {
                if (!visited[v]) {
                    q.offer(new Pair(v, troops[v]));
                }
//                    troops[v] = troops[v] + u.second();
            }
        }

        pw.println(maxTroops);
        pw.close();

    }
}

class Pair implements Comparable<Pair> {
    Integer _f, _s;
    public Pair(Integer f, Integer s) { _f = f; _s = s; }
    public int compareTo(Pair o) {
        if (!this.second().equals(o.second())) // this.first() != o.first()
            return this.second() - o.second();   // is wrong as we want to
        else                                 // compare their values,
            return this.first() - o.first(); // not their references
    }
    Integer first() { return _f; }
    Integer second() { return _s; }
}