import java.io.*;
import java.util.*;

public class bigtruck {

    public static int INF = 1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        String[] line = reader.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        line = reader.readLine().split(" ");

//        Distance[] dist = new Distance[N];
        int[] items = new int[N];
        int[] dist = new int[N];
        for (int i = 0; i < N; i++) {
            int item = Integer.parseInt(line[i]);
//            Distance d = new Distance(INF, item);
//            dist[i] = d;
            dist[i] = INF;
            items[i] = item;
        }

        int M = Integer.parseInt(reader.readLine());

        List<List<Location>> al = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            al.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            line = reader.readLine().split(" ");
            int u = Integer.parseInt(line[0])-1;
            int v = Integer.parseInt(line[1])-1;
            int w = Integer.parseInt(line[2]);
            Location ue = new Location(v, w, items[u]);
            Location ve = new Location(u, w, items[v]);
            al.get(u).add(ue);
            al.get(v).add(ve);

        }

        int[] accumItems = new int[N];
        dist[0] = 0;
        accumItems[0] = items[0];
        PriorityQueue<Location> q = new PriorityQueue<>();
        q.offer(new Location(0,0, items[0]));
        while (!q.isEmpty()) {
            Location ud = q.poll();
            int u = ud.v;
            if (ud.w != dist[ud.v]) continue; // lazy delete
            for (Location e: al.get(u)) {
                int v = e.v;
                if (dist[v] > dist[u] + e.w) {
                    dist[v] = dist[u] + e.w;
                    accumItems[v] = accumItems[u] + items[v];
                    q.offer(new Location(e.v, dist[v], accumItems[v]));
                } else if (dist[v] >= dist[u] + e.w && accumItems[v] < accumItems[u] + items[v]) {
                    accumItems[v] = accumItems[u] + items[v];
                    q.offer(new Location(e.v, dist[u], accumItems[v]));
                }
            }
        }
        if (dist[N-1] == INF) pw.println("impossible");
        else {
            pw.print(dist[N-1] + " " + accumItems[N-1]);
        }

        pw.close();

    }
}

class Location implements Comparable<Location> {
    public int v;
    public int w;
    public int items;
    public Location(int v,int w, int items) {
        this.v = v;
        this.w=w;
        this.items = items;
    }
    @Override
    public int compareTo(Location o) {
        if (this.w == o.w) return o.items - this.items;
        return this.w - o.w;
    }
}
