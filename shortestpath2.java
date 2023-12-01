import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class shortestpath2 {

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
            for (int i = 0; i < N; i++) {
                al.add(new ArrayList<>());
            }

//            TimeData[] time = new TimeData[N];
            for (int i = 0; i < M; i++) {
                line = reader.readLine().split(" ");
                int u = Integer.parseInt(line[0]);
                int v = Integer.parseInt(line[1]);
                int t = Integer.parseInt(line[2]);
                int p = Integer.parseInt(line[3]);
                int w = Integer.parseInt(line[4]);
//                if (p == 0) p = 1; // SET TO PREVENT DIVISON BY 0
                Edge pair = new Edge(v, t, p, w);
                List<Edge> neighbours = al.get(u);
                neighbours.add(pair);
                al.set(u, neighbours);
//                time[v] = new TimeData(t, p);
            }


            int[] dist = new int[N];
            Arrays.fill(dist, INF);
            dist[S] = 0;
            PriorityQueue<Edge> pq = new PriorityQueue<>();
            pq.offer(new Edge(S, 0,0,0));
            while (!pq.isEmpty()) {
                Edge u = pq.poll();
                List<Edge> neighbours = al.get(u.v);
                for (int i = 0; i < neighbours.size(); i++) {
                    Edge v = neighbours.get(i);
                    int T = dist[u.v];
                    int nextTime = 0;
                    int k = (int)Math.max(0, Math.ceil((T-v.t)/(double)v.p));
                    if (v.p != 0) {
                        nextTime += (v.t + k*v.p);
                        if (dist[v.v] > nextTime + v.w) {
                            dist[v.v] = nextTime + v.w;
                            pq.offer(neighbours.get(i));
                        }
                    } else if (T <= v.t) {
                        if (dist[v.v] > v.t + v.w) {
                            dist[v.v] = v.t + v.w;
                            pq.offer(neighbours.get(i));
                        }
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

class TimeData implements Comparable<TimeData>{
    Integer v, t, p, w;
    public TimeData(Integer v, Integer t, Integer p, Integer w) { this.v = v; this.t = t; this.p = p; this.w = w;}
//    public int compareTo(TimeData o) {
//
//    }
    public int compareTo(TimeData o) {
        return this.w - o.w;
    }

}
class Edge implements Comparable<Edge> {
    Integer v, t, p, w;
    public Edge(Integer v, Integer t, Integer p, Integer w) { this.v = v; this.t = t; this.p = p; this.w = w;}
    public int compareTo(Edge o) {
        return this.w - o.w;
    }

}