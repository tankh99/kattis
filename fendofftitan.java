import java.io.*;
import java.util.*;

public class fendofftitan {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        String[] line = reader.readLine().split(" ");
        int N = Integer.parseInt(line[0]); // villages
        int M = Integer.parseInt(line[1]); // roads
        int X = Integer.parseInt(line[2])-1; // src
        int Y = Integer.parseInt(line[3])-1; // dest
//        List<Road> edges = new ArrayList<>();
        Map<Integer, List<Quadruple>> al = new HashMap<>();
        PriorityQueue<Quadruple> pq = new PriorityQueue<>();
        Distance[] dist = new Distance[N];
        Arrays.fill(dist, new Distance(Long.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE));
        for (int i = 0; i < M; i++) {
            line = reader.readLine().split(" ");
            int A = Integer.parseInt(line[0])-1;
            int B = Integer.parseInt(line[1])-1;
            int W = Integer.parseInt(line[2]); // W = weight of road
            int C = Integer.parseInt(line[3]); // 2 == titan, 1 == shaman
//            Road edge = new Road(A, B, W, C);
            int titans = C == 2 ? 1 : 0;
            int shamans = C == 1 ? 1 : 0;
            Quadruple edge = new Quadruple(B, W, titans, shamans);
//            edges.add(edge);
            List<Quadruple> neighboursA = al.getOrDefault(A, new ArrayList<>());
            neighboursA.add(edge);
            al.put(A, neighboursA);
            // TODO: See if this is necssary to add backedges
            List<Quadruple> neighboursB = al.getOrDefault(B, new ArrayList<>());
            Quadruple reverseEdge = new Quadruple(A, W, titans, shamans);
            neighboursB.add(reverseEdge);
            al.put(B, neighboursB);
        }

        int titans = 0;
        int shamans = 0;

//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                Road edge = edges.get(j);
//                int u = edge.from;
//                int v = edge.to;
//                if (dist[u] != Integer.MAX_VALUE) {
//                    dist[v] = Math.min(dist[v], dist[u] + edge.distance);
//                } else if (dist[v] != Integer.MAX_VALUE) {
//                    dist[u] = Math.min(dist[u], dist[v] + edge.distance);
//                }
//            }
//        }
//        for (int i = 0; i < neighbours.size(); i++) {
//            pq.offer(neighbours.get(i));
//        }
        dist[X] = new Distance(0, 0, 0);
        pq.offer(new Quadruple(X, dist[X].distance, dist[X].titans, dist[X].shamans));
        while (!pq.isEmpty()) {
            Quadruple u = pq.poll();
//            if (u != dist[u.v]) continue
//            TODO: Implement lazy delete;
            List<Quadruple> neighbours = al.get(u.v);
            if (neighbours == null) continue;
            for (int i = 0; i < neighbours.size(); i++) {
                Quadruple v = neighbours.get(i);

                // Must consider the enemies and distance as a WHOLE. If any enemies increase as a result, do NOT add
                // Don't want to offer any fucking node that adds enemies
                long newDistance = (dist[u.v].distance + v.distance);
                int newTitans = (dist[u.v].titans + v.titans);
                int newShamans = (dist[u.v].shamans + v.shamans);
                if (u.distance == dist[u.v].distance && u.shamans == dist[u.v].shamans && u.titans == dist[u.v].titans) {
                    if (newTitans < dist[v.v].titans
                            || (newTitans <= dist[v.v].titans && newShamans < dist[v.v].shamans)
                            || (newTitans <= dist[v.v].titans && newShamans <= dist[v.v].shamans && newDistance < dist[v.v].distance)
                    ) {
                        dist[v.v] = new Distance(newDistance, newTitans, newShamans);
                        pq.offer(new Quadruple(v.v, newDistance, newTitans, newShamans));
                    }
                }
//                dist[r.to] = Math.min(dist[r.to], r.distance);
            }
        }
        if (dist[Y].distance == Long.MAX_VALUE) {
            pw.println("IMPOSSIBLE");
            pw.close();
            return;
        }

        pw.println(dist[Y].distance + " " + dist[Y].shamans + " " + dist[Y].titans);
        pw.close();

    }
}

class Quadruple extends Distance {
    public int v;
    public Quadruple(int v, long distance, int titans, int shamans) {
        super(distance, shamans, titans);
        this.v = v;
        this.distance = distance;
        this.shamans = shamans;
        this.titans = titans;
    }
}

class Distance implements Comparable<Distance> {

    public long distance;
    public int titans;
    public int shamans;
    public Distance(long distance, int titans, int shamans) {
        this.distance = distance;
        this.shamans = shamans;
        this.titans = titans;
    }

    @Override
    public int compareTo(Distance o) {
        if (this.titans - o.titans != 0) return this.titans - o.titans;
        if (this.shamans - o.shamans != 0) return this.shamans - o.shamans;
        return Long.compare(this.distance, o.distance);
    }
}
class Pair implements  Comparable<Pair> {
    public long distance;
    public int enemy;
    public Pair(long distance, int enemy) {
        this.distance= distance;
        this.enemy = enemy;
    }

    @Override
    public int compareTo(Pair o) {
        int enemyLevel = this.enemy - o.enemy;
        if (enemyLevel != 0) return enemyLevel;
        return Long.compare(this.distance, o.distance);
    }
}