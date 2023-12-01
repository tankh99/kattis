import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class swaptosort {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int K = Integer.parseInt(line[1]);
        UnionFind original = new UnionFind(N);
        for (int i = 0; i < K; i++) {
            line = reader.readLine().split(" ");
            int x = Integer.parseInt(line[0])-1;
            int y = Integer.parseInt(line[1])-1;
            original.unionSet(x, y);
        }

        for (int i = 0; i < N; i++) {
            int x1 = i;
            int x2 = N-i-1;
            if (!original.isSameSet(x1, x2)) {
                System.out.println("No");
                return;
            }

        }
//        original.print();
        System.out.println("Yes");
    }

}

class UnionFind {
    private int[] p, rank, setSize;
    private int numSets;

    public UnionFind(int N) {
        p = new int[N];
        rank = new int[N];
        setSize = new int[N];
        numSets = N;
        for (int i = 0; i < N; i++) {
            p[i] = i;
            rank[i] = 1;
            setSize[i] = 1;
        }
    }

    public void print() {
        for (int i = 0; i < p.length; i++) {
            System.out.print(p[i] + " ");
        }
        System.out.println();
    }
    public int findSet(int i) {
        return (p[i] == i) ? i : (p[i] = findSet(p[i]));
    }

    public boolean isSameSet(int i, int j) {
        return findSet(i) == findSet(j);
    }

    public void unionSet(int i, int j) {
        if (isSameSet(i, j)) return;
        int x = findSet(i), y = findSet(j);
        if (rank[x] > rank[y]) {
            int temp = x;
            x = y;
            y = temp;
        }
        p[x] = y;
        if (rank[x] == rank[y]) rank[y]++;
//        setSize[y] += setSize[x];
//        numSets--;
    }

}