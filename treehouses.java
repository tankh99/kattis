import java.io.*;
import java.util.*;

public class treehouses {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        String[] line = reader.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int E = Integer.parseInt(line[1]); // first E are connected
        int P = Integer.parseInt(line[2]); // cables
        List<Vertex> vertexList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            line = reader.readLine().split(" ");
            double x = Double.parseDouble(line[0]);
            double y = Double.parseDouble(line[1]);
            Vertex v = new Vertex(x, y);
            vertexList.add(v);
        }


        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < vertexList.size(); i++) {
            Vertex v = vertexList.get(i);
            for (int j = i+1; j < vertexList.size(); j++) {
                Vertex u = vertexList.get(j);
                double distance = v.getDistance(u);
                Edge edge = new Edge(i, j, distance);
                edges.add(edge);
            }
        }
        UnionFind uf = new UnionFind(N);
        for (int i = 1; i < E; i++) {
            int from = i-1;
            int to = i;
            Edge edge = new Edge(from, to, 0);
            edges.add(edge);
        }
        for (int i = 0; i < P; i++) {
            line = reader.readLine().split(" ");
            int from = Integer.parseInt(line[0])-1;
            int to = Integer.parseInt(line[1])-1;
            Edge edge = new Edge(from, to, 0);
            edges.add(edge);
        }
        Collections.sort(edges);

        double totalDistance = 0;

        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            if (edge.distance == 0) {
                uf.unionSet(edge.from, edge.to);
            } else if (!uf.isSameSet(edge.to, edge.from)) {
                totalDistance += edge.distance;
                uf.unionSet(edge.to, edge.from);
            }
//            else if (visited[edge.from] == 0) {
//                totalDistance += edge.distance;
//                visited[edge.from] = 1; // mark as visited
//                visits++;
//            }
        }


        pw.println(Math.floor(totalDistance*1e6)/1e6);

        pw.close();

    }

    public static float getDistance(float x1, float y1, float x2, float y2) {
        double distance = Math.sqrt(Math.pow(x1-x2,2) + Math.pow(y1-y2,2));
        return (float)distance;
    }
}

class Vertex {
    public double x;
    public double y;
    public Vertex(double x, double y) {
        this.x = x;
        this.y = y;
    }


    public double getDistance(Vertex v) {
        double distance = Math.sqrt(Math.pow(this.x-v.x,2) + Math.pow(this.y-v.y,2));
        return (double)distance;
    }
}

class Edge implements Comparable<Edge> {

    public int from;
    public int to;
    public double distance;
    public Edge (int from, int to, double distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    @Override
    public int compareTo(Edge o) {
        return Double.compare(this.distance, o.distance);
    }
}


// Union-Find Disjoint Sets Library written in OOP manner, using both path compression and union by rank heuristics
class UnionFind {                                              // OOP style
    private ArrayList<Integer> p, rank, setSize;
    private int numSets;

    public UnionFind(int N) {
        p = new ArrayList<>(N);
        rank = new ArrayList<>(N);
        setSize = new ArrayList<>(N);
        numSets = N;
        for (int i = 0; i < N; i++) {
            p.add(i);
            rank.add(0);
            setSize.add(1);
        }
    }

    public int findSet(int i) {
        if (p.get(i) == i) return i;
        else {
            int ret = findSet(p.get(i)); p.set(i, ret);
            return ret; } }

    public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }

    public void unionSet(int i, int j) {
        if (!isSameSet(i, j)) { numSets--;
            int x = findSet(i), y = findSet(j);
            // rank is used to keep the tree short
            if (rank.get(x) > rank.get(y)) { p.set(y, x); setSize.set(x, setSize.get(x) + setSize.get(y)); }
            else                           { p.set(x, y); setSize.set(y, setSize.get(y) + setSize.get(x));
                if (rank.get(x) == rank.get(y)) rank.set(y, rank.get(y) + 1); } } }
    public int numDisjointSets() { return numSets; }
    public int sizeOfSet(int i) { return setSize.get(findSet(i)); }
}