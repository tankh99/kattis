import java.io.*;
import java.util.*;

public class birthday {
    public static final int UNVISITED = 0;
    static String result = "Yes";
    static int dfsRoot;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        while (true) {
            String[] line = reader.readLine().split(" ");
            result = "No"; // reset each TC
            count = 0;
            int V = Integer.parseInt(line[0]);
            int E = Integer.parseInt(line[1]);
            if (V == 0 && E == 0) break;
            List<List<Integer>> al = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                al.add(new ArrayList<>());
            }
            for (int i = 0; i < E; i++) {
                line = reader.readLine().split(" ");
                int u = Integer.parseInt(line[0]);
                int v = Integer.parseInt(line[1]);
                al.get(u).add(v);
                al.get(v).add(u);
            }

            boolean[] visited = new boolean[V];
            int[] parent = new int[V];
            int time = 0;
            int[] dfs_low = new int[V];
            int[] dfs_num = new int[V];
            int[] dfs_parent = new int[V];
//            Arrays.fill(dfs_low, 0);
            Arrays.fill(dfs_num, UNVISITED);
            Arrays.fill(dfs_parent, -1);

            for (int i = 0; i < V; i++) {
                if (dfs_num[i] == UNVISITED) {
//                    dfsRoot = i;
                    dfs(i, al, dfs_num, dfs_low, dfs_parent, visited, -1);
                }
            }

            pw.println(result);
        }

        pw.close();

    }


    private static int count = 0;
//    private static int dfsRoot, rootChildren;
    public static void dfs(int u, List<List<Integer>> al, int[] dfs_num, int[] dfs_low, int[] dfs_parent, boolean[] visited, int p) {
        visited[u] = true;
        count++;
        dfs_low[u] = count;
        dfs_num[u] = count;
        for (int v: al.get(u)) {
//            if (v != dfs_parent[u]) {
            if (v != p) {
                if (visited[v]) {
                    dfs_low[u] = Math.min(dfs_low[u], dfs_num[v]);
                } else {
                    dfs_parent[v] = u;
                    dfs(v, al, dfs_num, dfs_low, dfs_parent, visited, u);
                    dfs_low[u] = Math.min(dfs_low[u], dfs_low[v]);
                    if (dfs_low[v] > dfs_num[u]) {
                        result = "Yes";
                        return;
                    }
                }
//                if (u == dfsRoot) ++rootChildren; // I think can ignore this

            }
        }
    }


}
