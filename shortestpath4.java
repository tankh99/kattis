import java.io.*;
import java.util.*;

public class shortestpath4 {
    public static final int INF = 1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        String[] line = reader.readLine().split(" ");
        int TC = Integer.parseInt(line[0]);
        for (int i = 0; i < TC; i++) {
            reader.readLine();
            int V = Integer.parseInt(reader.readLine());
            List<List<Edge>> al = new ArrayList<>();
            for (int j = 0; j < V; j++) {
                al.add(new ArrayList<>());
            }
            for (int j = 0; j < V; j++) {
                line = (reader.readLine()).split(" ");
                int E = Integer.parseInt(line[0]);
                for (int k = 1; k < E*2+1; k+=2) {
                    int v = Integer.parseInt(line[k]);
                    int w = Integer.parseInt(line[k+1]);
                    Edge edge = new Edge(v, w, 1);
                    al.get(j).add(edge);
                }
            }
            /**
             * 2D matrix that has the shortest distance based on K
             */
            int Q = Integer.parseInt(reader.readLine());
            for (int j = 0; j < Q; j++) {
                line = reader.readLine().split(" ");
                int u = Integer.parseInt(line[0]);
                int v = Integer.parseInt(line[1]);
                int k = Integer.parseInt(line[2]);
                // Idea: traverse thru everything first and use a stack for any shorter paths, while keeping the safer paths in the stack
                // Stack<Distance>[]

                if (u != v && k < 2) {
                    pw.println(-1);
                } else if (u == v) {
                    pw.println(0);
                } else {
                    Distance[][] dist = new Distance[V][k];
                    for (int l = 0; l < V; l++) {
                        Arrays.fill(dist[l], new Distance(INF, INF));;
                    }

                    dist[u][0] = new Distance(0, 1);
                    int ans = traverse(al, dist, u, v, k);
                    pw.println(ans);
                }
            }
            pw.println();
        }

        pw.close();

    }

    // State-space, uses multidimensional array
    // Cols = vertex number
    // Rows = value of K
    public static int traverse(List<List<Edge>> al, Distance[][] distances, int src, int dest, int k) {
        PriorityQueue<Edge> q = new PriorityQueue<>();
        q.offer(new Edge(src, 0, 1));
        int ans = -1;
        while (!q.isEmpty()) {
            Edge u = q.poll();
            Distance[] uDistArr = distances[u.v];

//            if (uDistArr[k-1].junctionsTraversed == INF) continue;
            List<Edge> neighbours = al.get(u.v);
            if (u.v == dest) {
                ans = u.w;
                break;
            };
            if (u.junctions < k) {
                for (int i = 0; i < neighbours.size(); i++) {
                    Edge v = neighbours.get(i);
                    Distance[] vDistArr = distances[v.v];

                    if (vDistArr[u.junctions].distance > uDistArr[u.junctions-1].distance + v.w) {
                        vDistArr[u.junctions] =  new Distance(
                                uDistArr[u.junctions-1].distance + v.w,
                                u.junctions+1
                        );
                        q.offer(new Edge(v.v, (int)uDistArr[u.junctions-1].distance + v.w, u.junctions+1));
                    }
                }
            }
        }
        return ans;
    }
}

class Edge implements Comparable<Edge> {
    Integer v, w, junctions;
    public Edge(Integer v, Integer w, Integer junctions) { this.v = v; this.w = w; this.junctions = junctions;}
    public int compareTo(Edge o) {
        if (this.w != o.w) return this.w - o.w;
        return this.v - o.v;
    }
}

class Distance {
    long distance;
    int junctionsTraversed;
    public Distance(long distance, int junctionsTraversed) {
        this.distance = distance;
        this.junctionsTraversed = junctionsTraversed;
    }
}