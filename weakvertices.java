import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class weakvertices {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            int N = Integer.parseInt(reader.readLine());
            if (N == -1) {
                break;
            }
            int[][] matrix = new int[N][N];

            for (int i = 0; i < N; i++) {
                String[] line = reader.readLine().split(" ");
                List<Pair> row = new ArrayList<>();
                for (int j = 0; j < N; j++) {
                    int entry = Integer.parseInt(line[j]);
                    matrix[i][j] = entry;
    //                if (entry == 0) continue;
    //                Pair pair = new Pair(i, j);
    //                row.add(pair);
                }
    //            al.add(row);
            }

            List<Integer> weak = new ArrayList<>();
            int[] triangles = new int[N];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (findTriangle(matrix, i, j)) {
    //                    weak.add(i);
                        triangles[i] = 1;
                        triangles[j] = 1;
                    }
                }
            }

    //        Collections.sort(weak);
            for (int i = 0; i < triangles.length; i++) {
                if (triangles[i] == 0) {
                    System.out.print(i + " ");
                }
            }
            System.out.println();
//        System.out.println(weak.size());
        }


    }
    
    public static boolean findTriangle(int[][] matrix, int i, int j) {
        int value = matrix[i][j];
        for (int k = 0; k < matrix.length; k++) {
            if (matrix[k][i] != 0 && matrix[j][k] != 0 && matrix[i][j] != 0) {
                return true;
            }
        }
        return false;
    }
}

class Pair implements  Comparable<Pair> {
    public int x, y;
    public Pair (int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Pair o) {
        if (this.x != o.x) return this.x - o.x;
        return this.y - o.y;
    }

    @Override
    public String toString() {
        return this.x + " " + this.y;
    }
}
