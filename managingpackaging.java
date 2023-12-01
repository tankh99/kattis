import java.io.*;
import java.util.*;

public class managingpackaging {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        while (true) {

            String[] line = reader.readLine().split(" ");
            int N = Integer.parseInt(line[0]);
            if (N == 0) break;
            Map<String, Integer> in = new HashMap<>();
            Map<String, List<String>> al = new HashMap<>();
            for (int i = 0; i < N; i++) {
                line = reader.readLine().split(" ");
                String v = line[0];
                int indeg = in.getOrDefault(v, 0);
                for (int j = 1; j < line.length; j++) {
                    String u = line[j];
                    List<String> neighbours = al.getOrDefault(u, new ArrayList<>());
                    neighbours.add(v);

                    indeg++;
                    al.put(u, neighbours);
                    int uIn = in.getOrDefault(u, 0);
                    in.put(u, uIn);
                }
                List<String> neighbours = al.getOrDefault(v, new ArrayList<>());
                al.put(v, neighbours);
                in.put(v, indeg);
            }

            for (Map.Entry<String, List<String>> entry: al.entrySet()) {
                Collections.sort(entry.getValue());
            }

            PriorityQueue<String> q = new PriorityQueue<>();

            for (Map.Entry<String, Integer> entry: in.entrySet()) {
                if (entry.getValue() == 0) q.offer(entry.getKey());
            }

            int out = 0;
            List<String> list = new ArrayList<>();
            while (!q.isEmpty()) {
                String u = q.poll();
//                stack.push(u);
                list.add(u);
                for (String v: al.get(u)) {
                    int newInDeg = in.get(v) - 1;
                    in.put(v, newInDeg);
                    if (in.get(v) == 0) {
                        q.offer(v);
//                        list.add(v);
                    }
                }
                out++;
            }
            if (out != N) pw.println("cannot be ordered");
            else {
//            return true;
                for (int i = 0; i < list.size(); i++) {
                    pw.println(list.get(i));
                }
            }
            pw.println();
        }


        pw.close();

    }
}
