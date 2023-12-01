import java.io.*;
import java.util.*;

public class breakingbad {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        String[] line = reader.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        Map<String, List<String>> al = new HashMap<>();
        for (int i = 0; i < N; i++) {
//            line = reader.readLine().split(" ");
            String item = reader.readLine();
            al.put(item, new ArrayList<>());
        }

        int M = Integer.parseInt(reader.readLine());
        for (int i = 0; i < M; i++) {
            line = reader.readLine().split(" ");
            String u = line[0];
            String v = line[1];

            List<String> uneighbours = al.get(u);
            uneighbours.add(v);;
            al.put(u, uneighbours);

            List<String> vneighbours = al.get(v);
            vneighbours.add(u);;
            al.put(v, vneighbours);
        }

        Map<String, Integer> color = new HashMap<>();
        Map<String, Boolean> visited = new HashMap<>();


        List<String> left = new ArrayList<>();
        List<String> right = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry: al.entrySet()) {
            String s = entry.getKey();
            if (!visited.containsKey(s)) {
                Queue<String> q = new LinkedList<>();
                q.offer(s);
                color.put(s, 1);
                while (!q.isEmpty()) {
                    String u = q.poll();
                    if (visited.containsKey(u)) continue;
                    visited.put(u, true);
                    if (color.containsKey(u)) {
                        if (color.get(u) == 1) {
                            left.add(u);
                        } else {
                            right.add(u);
                        }
                    }
                    for (String v: al.get(u)) {
                        if (!color.containsKey(v)) {
                            color.put(v, 1 - color.get(u));
                            q.offer(v);
                        } else if (color.get(u).equals(color.get(v))){
                            pw.println("impossible");
                            pw.close();
                            return;
                        }
                    }
                }
            }
        }

        for (String item: left) {
            pw.print(item + " ");
        }
        pw.println();
        for (String item: right) {
            pw.print(item + " ");
        }

        pw.close();

    }
}
