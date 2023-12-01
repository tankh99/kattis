import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Coworker implements  Comparable<Coworker> {
    public int annoyance;
    public int d;
    public Coworker(int annoyance, int d) {
        this.annoyance = annoyance;
        this.d = d;
    }

    public void getHelp() {
        this.annoyance += this.d;
    }
    
    @Override
    public int compareTo(Coworker o) {
        return (this.annoyance + this.d) - (o.annoyance + o.d) != 0
                ? (this.annoyance + this.d) - (o.annoyance + o.d)
                : this.d - o.d;
    }
}
public class annoyedcoworkers {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split(" ");
        int h = Integer.parseInt(line[0]); //
        int c = Integer.parseInt(line[1]); // coworkers
        PriorityQueue<Coworker> coworkers = new PriorityQueue<>();
        int max = 0;
        for (int i = 0; i < c; i++) {
            line = reader.readLine().split(" ");
            int a = Integer.parseInt(line[0]);
            int d = Integer.parseInt(line[1]);
            Coworker coworker = new Coworker(a, d);
            max = Math.max(max, a);
            coworkers.offer(coworker);
        }

        for (int i = 0; i < h; i++) {
            Coworker cw = coworkers.poll();
            cw.getHelp();
            max = Math.max(cw.annoyance, max);
            coworkers.offer(cw);
        }

        System.out.println(max);
    }

}