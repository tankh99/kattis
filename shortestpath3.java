import java.io.*;
import java.util.*;

public class shortestpath3 {
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
            List<List<Edge>> al = new ArrayList<>();
            List<Edge> edges = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                al.add(new ArrayList<>());
            }

//            TimeData[] time = new TimeData[N];
            int min = INF;
            for (int i = 0; i < M; i++) {
                line = reader.readLine().split(" ");
                int u = Integer.parseInt(line[0]);
                int v = Integer.parseInt(line[1]);
                int w = Integer.parseInt(line[2]);
//                if (p == 0) p = 1; // SET TO PREVENT DIVISON BY 0
                Edge pair = new Edge(u, v, w);
                edges.add(pair);;

                List<Edge> neighbours = al.get(u);
                neighbours.add(pair);
                al.set(u, neighbours);
                min = Math.min(min, w);
            }

            Collections.sort(edges);

            int[] dist = new int[N];
            Arrays.fill(dist, INF);
            dist[S] = 0;
            for (int i = 1; i < N; i++) { // relax N -1 edges
                for (int j = 0; j < M; j++) {
                    Edge edge = edges.get(j);
//                    int u = edges.get(j).u;
//                    int v = edges.get(j).v;
                    if (dist[edge.u] != INF &&
                        dist[edge.v] > dist[edge.u] + edge.w) {
                        dist[edge.v] = dist[edge.u] + edge.w;
                    }
                }
            }
//            boolean[] negativeCycles = new boolean[N];
//            boolean negativeCycle = true;
//            while (negativeCycle) {
//                negativeCycle = false;
//                for (int i = 0; i < M; i++) {
//                    Edge edge = edges.get(i);
//                    if (dist[edge.u] != INF &&
//                            !negativeCycles[edge.v] &&
//                            dist[edge.v] > dist[edge.u] + edge.w) {
//                        dist[edge.v] = -INF;
//                        negativeCycle = true;
//                        negativeCycles[edge.v] = true;
//                    }
//                }
//            }
            for (int i = 0; i < N-1; i++) {
                for (int j = 0; j < M; j++) {
                    Edge edge = edges.get(j);
                    if (dist[edge.u] != INF &&
                            dist[edge.v] > dist[edge.u] + edge.w) {
                        dist[edge.v] = -INF;
    //                    loops.add(edge.v);
                    }
                }
            }

            for (int i = 0; i < Q; i++) {
                int dest = Integer.parseInt(reader.readLine());
                if (dist[dest] == -INF) {
                    pw.println("-Infinity");
                }  else if (dist[dest] == INF) {
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
class Edge implements Comparable<Edge> {
    Integer u, v, w;
    public Edge(Integer u, Integer v, Integer w) { this.u = u; this.v = v; this.w = w;}
    public int compareTo(Edge o) {
        if (this.w != o.w) return this.w - o.w;
        if (this.u != o.u) return this.u - o.u;
        return this.v - o.v;
    }

}