import java.io.*;
import java.util.*;

public class nicknames {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int N = Integer.parseInt(reader.readLine());
        Map<String, Set<String>> map = new HashMap<>(N*10);
        for (int i = 0; i < N; i++) {
            String name = reader.readLine();
            StringBuilder sb = new StringBuilder(name.length());
            for (int j = 0; j < name.length(); j++) {
                sb.append(name.charAt(j));
                String sub = sb.toString();
                Set<String> set = map.getOrDefault(sub, new HashSet<>(10));
                set.add(name);
                map.put(sub, set);
            }
        }
        N = Integer.parseInt(reader.readLine());
        for (int i = 0; i < N; i++) {
            String nickname = reader.readLine();
            if (!map.containsKey(nickname)) {
                pw.println(0);
                continue;
            }
            pw.println(map.get(nickname).size());
        }
        pw.close();

    }
}
