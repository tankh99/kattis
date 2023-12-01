import java.io.*;
import java.util.LinkedList;
import java.util.*;

public class tenkindsofpeople {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        String[] line = reader.readLine().split(" ");
        int R = Integer.parseInt(line[0]);
        int C = Integer.parseInt(line[1]);
//        int[][] map = new int[R][C];
        String[] map = new String[R];
        for (int i = 0; i < R; i++) {
            map[i] = reader.readLine();
        }

        int N = Integer.parseInt(reader.readLine());
        boolean[][] visited = new boolean[R][C];
        UnionFind uf = new UnionFind(R*C);
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!visited[i][j]) {
                    bfs(map, uf, visited, new Pair(i,j, C));
                }
            }
        }

        for (int i = 0; i < N; i++) {
            line = reader.readLine().split(" ");
            int x1 = Integer.parseInt(line[0])-1;
            int y1 = Integer.parseInt(line[1])-1;
            int x2 = Integer.parseInt(line[2])-1;
            int y2 = Integer.parseInt(line[3])-1;
            Pair src = new Pair(x1, y1, C);
            Pair dest = new Pair(x2, y2, C);
            boolean found = uf.isSameSet(src.getValue(), dest.getValue());
            if (found) {
                if (getValue(map, src) == 0) {
                    pw.println("binary");
                } else {
                    pw.println("decimal");
                }
            } else {
                pw.println("neither");
            }
        }

        pw.close();

    }


//    public static void dfs(String[] map, UnionFind uf, boolean[][] visited, Pair src) {
//        Pair u = src;
//        visited[u.first()][u.second()] = true;
//        List<Pair> neighbours = getNeighbours(map, u);
//        for (int i = 0; i < neighbours.size(); i++) {
//            Pair v = neighbours.get(i);
//            if (!visited[v.first()][v.second()]  {
//                visited[v.first()][v.second()] = true;
//                uf.unionSet(u.getValue(), v.getValue());
//                dfs(map, uf, visited, v);
//            }
//        }
//    }
    public static void bfs(String[] map, UnionFind uf, boolean[][] visited, Pair src) {
        Deque<Pair> q = new LinkedList<>();
        q.offer(src);
        while (!q.isEmpty()) {
            Pair u = q.poll();
            visited[u.first()][u.second()] = true;
            List<Pair> neighbours = getNeighbours(map, u);
            for (int i = 0; i < neighbours.size(); i++) {
                Pair v = neighbours.get(i);
                if (!visited[v.first()][v.second()] &&
                        getValue(map, u) == getValue(map, v)) {
                    q.offer(v);
                    visited[v.first()][v.second()] = true;
                    uf.unionSet(u.getValue(), v.getValue());
                }
            }
        }
    }

    public static int getValue(String[] map, Pair p){
        return Integer.parseInt(String.valueOf(map[p.first()].charAt(p.second())));
    }

    public  static List<Pair> getNeighbours(String[] map, Pair p) {
        List<Pair> neighbours = new ArrayList<>();
        int C = map[0].length();
        int r = p.first();
        int c = p.second();
        if (r+1 < map.length) {
            neighbours.add(new Pair(r+1, c, C));
        }
        if (r-1 >= 0) {
            neighbours.add(new Pair(r-1, c, C));
        }
        if (c + 1 < C) {
            neighbours.add(new Pair(r, c+1, C));
        }
        if (c - 1 >= 0) {
            neighbours.add(new Pair(r, c-1, C));
        }
        return neighbours;
    }

}

class Pair implements Comparable<Pair> {
    Integer _f, _s;
    int cols;
    public Pair(Integer f, Integer s, int cols) { _f = f; _s = s; this.cols = cols; }
    public int compareTo(Pair o) {
        if (!this.first().equals(o.first())) // this.first() != o.first()
            return this.first() - o.first();   // is wrong as we want to
        else                                 // compare their values,
            return this.second() - o.second(); // not their references
    }
    Integer first() { return _f; }
    Integer second() { return _s; }

    public int getValue() {
        return this.first() * this.cols + this.second();
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