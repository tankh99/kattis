import java.io.*;
import java.util.*;

public class aa_template {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        String[] line = reader.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        for (int i = 0; i < N; i++) {
            line = reader.readLine().split(" ");

        }

        pw.close();

    }
}
