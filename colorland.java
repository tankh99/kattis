import java.io.*;
import java.util.*;

public class colorland {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        String[] line = reader.readLine().split(" ");
        int N = Integer.parseInt(line[0]);

        String[] allColors = new String[] {"Blue", "Orange", "Pink", "Green", "Red", "Yellow"};
        List<String> colors = new ArrayList<>();
        colors.add("start");
        Set<String> availableColors = new HashSet<>();
        for (int i = 0; i < N; i++) {
            String u = reader.readLine();
            colors.add(u);
            availableColors.add(u);
        }

        List<Map<String, Integer>> al = new ArrayList<>();
        for (int i = 0; i < N+1; i++) {
            al.add(new HashMap<>());
        }

        Map<String, Queue<Integer>> queueMap = new HashMap<>();
        for (int i = 0; i < colors.size(); i++) {
            String u = colors.get(i);
            Queue<Integer> waitList = queueMap.getOrDefault(u, new LinkedList<>());
            while (!waitList.isEmpty()) {
                int lastIndex = waitList.poll();
                Map<String, Integer> map = al.get(lastIndex);
                map.put(u, i);
                al.set(lastIndex, map);
            }

            for (String color : allColors) {
                if (availableColors.contains(color)) {
                    Queue<Integer> newQueue = queueMap.getOrDefault(color, new LinkedList<>());
                    newQueue.offer(i);
                    queueMap.put(color, newQueue);
                }
            }
        }
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] visited = new boolean[N+1];
        dist[0] = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            if (visited[u]) continue;
            visited[u] = true;

            Map<String, Integer> neighbours = al.get(u);
            for (Map.Entry<String, Integer> entry: neighbours.entrySet()) {
                int v = entry.getValue();
                if (dist[v] > dist[u] + 1) {
                    dist[v] = dist[u] + 1;
                    q.offer(v);
                }
            }
        }
        pw.println(dist[N]);
        pw.close();

    }
}
