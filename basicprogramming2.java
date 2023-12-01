import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class basicprogramming2 {
    public static void main(String[] args) throws IOException {
//        Scanner sc = new Scanner(System.in);
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

            String[] line = sc.readLine().split(" ");
            int N = Integer.parseInt(line[0]);
            int action = Integer.parseInt(line[1]);
            Map<Integer, Integer> map = new HashMap<>();
            int[] arr = new int[N];
            line = sc.readLine().split(" ");
//            for (int i = 0; i < ; i++) {
//                if (i == 0) break;
//            }

            switch (action) {
                case 1: {
                    for (int i = 0; i < N; i++) {
                        int num = Integer.parseInt(line[i]);
                        int required = 7777 - num;
                        if (map.containsKey(required)) {
                            pw.println("Yes");
                            pw.close();
                            return;
                        }
                        map.put(num, i);
                    }
                    pw.println("No");
                    break;
                }
                case 2: {
                    for (int i = 0; i < N; i++) {
                        int num = Integer.parseInt(line[i]);
                        if (map.containsKey(num)) {
                            pw.println("Contains duplicate");
                            pw.close();
                            return;
                        }
                        map.put(num, i);
                    }
                    pw.println("Unique");
                    break;
                }
                case 3: {
                    for (int i = 0; i < N; i++) {
                        int num = Integer.parseInt(line[i]);
                        if (map.containsKey(num)) {
                            map.put(num, map.get(num) + 1);
                        } else {
                            map.put(num, 1);
                        }

                        if (map.get(num) > (N / 2)) {
                            pw.println(num);
                            pw.close();
                            return;
                        }
                    }
                    pw.println(-1);
                    break;
                }
                case 4: {
                    boolean isOdd = (N / 2) % 2 != 0;
                    for (int i = 0; i < N; i++) {
                        int num = Integer.parseInt(line[i]);
                        arr[i] = num;
                    }
                    Arrays.sort(arr);
                    if (isOdd) {
                        pw.println(arr[N / 2]);
                    } else {
                        pw.println(arr[N / 2 - 1] + " " + arr[N / 2]);
                    }
                    break;
                }
                case 5: {
                    List<Integer> list = new ArrayList<>();
                    for (int i = 0; i < N; i++) {
                        int num = Integer.parseInt(line[i]);
                        if (num >= 100 && num <= 999) {
                            list.add(num);
                        }
                    }
                    Collections.sort(list);
                    for (int i = 0; i < list.size() - 1; i++) {
                        pw.print(list.get(i) + " ");
                    }
                    pw.print(list.get(list.size() - 1));
                    break;
                }
            }
        pw.close();
        sc.close();

    }
}
