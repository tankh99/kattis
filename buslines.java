import java.io.*;
import java.util.*;

public class buslines {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        String[] line = reader.readLine().split(" ");
        int N = Integer.parseInt(line[0]); // stations , 1-indexed
        int M = Integer.parseInt(line[1]); // buslines/undirected edges
        int edges = 0;
        StringBuilder sb = new StringBuilder();
        Set<Integer> sums = new HashSet<>();
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < N; i++) {
            int u = i+1;
            if (visited.size() == N && edges == M) break;
            for (int j = i+1; j < N; j++) {
                int v = j+1;
                int sum = u+v;
                if (!sums.contains(sum)) {
                    String res = u + " " + v;
                    sb.append(res);
                    sb.append('\n');
                    edges++;
                }
                sums.add(sum);
                visited.add(u);
                visited.add(v);

            }
        }
        if (edges != M) {
            pw.println(-1);
        } else {
            pw.println(sb.toString());
        }
        pw.close();

    }
}
