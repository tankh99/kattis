import java.io.*;
import java.util.*;

public class buildinghighways {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        String[] line = reader.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int[] difficulties = new int[N];
        line = reader.readLine().split(" ");
        for (int j = 0; j < line.length; j++) {
            int difficulty = Integer.parseInt(line[j]);
            difficulties[j] = difficulty;
        }

        Arrays.sort(difficulties);
        int min = difficulties[0];
        long max = 0;
        for (int i = 1; i < difficulties.length; i++) {
            max += min + difficulties[i];
        }
//        List<List<Pair>> al = new ArrayList<>();
//        for (int i = 0; i < N; i++) {
//            al.add(new ArrayList<>());
//        }
//        List<Pair> edges = new ArrayList<>();
//        List
        // TODO: Try out kruskal's too
//        for (int i = 0; i < N; i++) {
//            for (int j = i+1; j < N; j++) { // construct complete graph
//                int uweight = difficulties[i];
//                int vweight = difficulties[j];
//                Pair upair = new Pair(j, uweight+vweight);
//                al.get(i).add(upair);
//
//                Pair vpair = new Pair(i, uweight+vweight);
//                al.get(j).add(vpair);
//            }
//        }

//        boolean[] visited = new boolean[N];
//        PriorityQueue<Pair> q = new PriorityQueue<>();
//        q.offer(new Pair(0, 0));
//        int weight = 0;
//        while (!q.isEmpty()) {
//            Pair u = q.poll();
//            if (visited[u.first()]) continue;
//            visited[u.first()] = true;
//            weight += u.second();
//            for (Pair v: al.get(u.first())) {
//                if (!visited[v.first()]) {
//                    q.offer(v);
//                }
//            }
//        }

        pw.println(max);

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