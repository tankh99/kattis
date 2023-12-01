import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class jobbyte {
    /**
     * Problems
     * 1. how do we determine when and who to sort?
     * @param args
     */
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int N = Integer.parseInt(reader.readLine());
            int swaps = 0;
            String[] jobs = reader.readLine().split(" ");
            int[] positions = new int[jobs.length];
            for (int i = 0; i < N; i++) {
                int job = Integer.parseInt(jobs[i])-1;
                positions[job] = i;
            }
            for (int i = 0; i < positions.length; i++) {
//                System.out.println(i + ": " + positions[i]);
            }
            for (int i = 0; i < positions.length; i++) {
                while (i != positions[i]) {
//                    System.out.println(i + ": " + positions[i]);
                    for (int j = 0; j < positions.length; j++) {
                        System.out.print(positions[j] + " ");
                    }
                    swap(positions, i, positions[i]);

                    System.out.println();
                    swaps++;
                }
            }
            for (int j = 0; j < positions.length; j++) {
                System.out.print(positions[j] + " ");
            }
            System.out.println();
//            for (int i = 0; i < positions.length; i++) {
//                System.out.println(i + ": " + positions[i]);
//            }
            System.out.println(swaps);

        } catch (IOException ex) {
            System.err.println(ex);
        }

    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


}
