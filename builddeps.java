import java.io.*;
import java.util.*;

public class builddeps {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        String[] line = reader.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        Map<String, List<String>> al = new HashMap<>();
        for (int i = 0; i < N; i++) {
            line = reader.readLine().split(" ");
            String u = line[0].substring(0, line[0].length()-1); // Remove the :
            for (int j = 1; j < line.length; j++) {
                String v = line[j];
                List<String> neighbours = al.getOrDefault(v, new ArrayList<>());
                neighbours.add(u);
                al.put(v, neighbours);
            }
        }

        String modified = reader.readLine();
        Stack<String> stack = new Stack<>();
        Map<String, Boolean> visited = new HashMap<>();
//        for (Map.Entry<String, List<String>> entry: al.entrySet()) {
//            toposort(al, stack, visited, entry.getKey());
//        }
        toposort(al, stack, visited, modified);
        while (!stack.isEmpty()) {
            pw.println(stack.pop());
        }
        pw.close();

    }

    public static void toposort(Map<String, List<String>> al, Stack<String> stack, Map<String, Boolean> visited, String u) {
        if (visited.containsKey(u)) return;
        visited.put(u, true);
        List<String> neighbours = al.getOrDefault(u, new ArrayList<>());
        for (int i = 0; i < neighbours.size(); i++) {
            String v = neighbours.get(i);
            if (visited.containsKey(v)) continue;
            toposort(al, stack, visited, v);
        }
        stack.push(u);
    }
}
