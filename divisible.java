import java.io.*;
import java.util.*;

public class divisible {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        String[] line = reader.readLine().split(" ");
        int C = Integer.parseInt(line[0]);

        for (int i = 0; i < C; i++) {
            line = reader.readLine().split(" ");
            int D = Integer.parseInt(line[0]);
            int N = Integer.parseInt(line[1]);
            long[] prefix = new long[N];
            long sum = 0;
            int count = 0;
            Map<Long, Long> map = new HashMap<>();

            line = reader.readLine().split(" ");
            for (int j = 0; j < line.length; j++) {
                long num = Long.parseLong(line[j]);
                sum += num;
                prefix[j] = sum;
            }
            int l = 0, r = 0;
            for (int j = 0; j < prefix.length; j++) {
                long prefixSum = prefix[j];
                long target = (prefixSum - D) % D;
                int freq = 0;
                if (map.containsKey(target) || target == 0) {
                    count += map.getOrDefault(target, (long)0)+1;
                    freq++;
                }

                map.put(prefixSum%D, map.getOrDefault(prefixSum%D, (long)0) + freq);
            }

            pw.println(count);
        }

        pw.close();

    }
}
