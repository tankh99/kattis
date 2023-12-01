import java.io.*;
import java.util.*;

public class flyingsafely {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        String[] line = reader.readLine().split(" ");
        int TC = Integer.parseInt(line[0]);
        for (int i = 0; i < TC; i++) {
            line = reader.readLine().split(" ");
            int N = Integer.parseInt(line[0]);
            int M = Integer.parseInt(line[1]);
            Deque<Integer> q = new LinkedList<>();
            int[] visited = new int[N];
            int trusted = 0;
            List<List<Integer>> al = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                al.add(new ArrayList<>());
            }
            for (int j = 0; j < M; j++) {
                line = reader.readLine().split(" ");
                int u = Integer.parseInt(line[0])-1;
                int v = Integer.parseInt(line[1])-1;
                List<Integer> outAl = al.get(u);
                outAl.add(v);
                al.set(u, outAl);

                List<Integer> inAl = al.get(v);
                inAl.add(u);
                al.set(v, inAl);
            }

            q.offer(0);
            visited[0] = 1;
            while (!q.isEmpty()) {
                int u = q.poll();
//                visited[u] = 1;
                List<Integer> neighbours = al.get(u);
                for (int j = 0; j < neighbours.size(); j++) {
                    int v = neighbours.get(j);
                    if (visited[v] == 0) {
                        trusted++;
                        q.offer(neighbours.get(j));
                        visited[v] = 1;
                    }
                }

            }
            pw.println(trusted);
        }

        pw.close();

    }
}