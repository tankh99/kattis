import java.io.*;
import java.util.*;
import java.util.PriorityQueue;

public class clinic {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        String[] line = reader.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int K = Integer.parseInt(line[1]);
        PriorityQueue<Patient> pq = new PriorityQueue<>(Collections.reverseOrder());
        Map<String, Boolean> deleted = new HashMap<>();
        PriorityQueue<WaitingPatient> wpq = new PriorityQueue<WaitingPatient>();
        for (int i = 0; i < N; i++) {
            line = reader.readLine().split(" ");
            int Q = Integer.parseInt(line[0]);
            int T = Integer.parseInt(line[1]);
            if (Q == 1) {
                String M = line[2];
                int S = Integer.parseInt(line[3]);
                WaitingPatient p = new WaitingPatient(T, M, S, K);
                wpq.offer(p);
//                pq.offer(p);
            } else if (Q == 2) {
                while (wpq.peek() != null && wpq.peek().arrivalTime < T) {
                    WaitingPatient wp = wpq.poll();
                    pq.offer(wp);
                }
                Patient p = null;
                if (pq.peek() != null && pq.peek().arrivalTime > T) {
                    pw.println("doctor takes a break");
                } else {
                    do {
                        p = pq.poll();
                        if (p == null) break;
                    } while (deleted.containsKey(p.name));
                    if (p == null) pw.println("doctor takes a break");
                    else pw.println(p.name);
                }
            } else if (Q == 3) {
                String name = line[2];
                deleted.put(name, true);
            }
        }

        pw.close();

    }
}

class WaitingPatient extends Patient {

    public WaitingPatient(int arrivalTime, String name, int severity, int K) {
        super(arrivalTime, name, severity, K);
    }

    public int compareTo(WaitingPatient o) {
        return Long.compare(this.arrivalTime, o.arrivalTime);
    }
}
class Patient implements Comparable<Patient> {
    public long severity;
    public long arrivalTime;
    public String name;
    public long K;
    public Patient(int arrivalTime, String name, int severity, int K) {
        this.arrivalTime = arrivalTime;
        this.name = name;
        this.severity = severity;
        this.K = K;
    }

    @Override
    public int compareTo(Patient o) {
        // severity <
        // K + waiting time
        long priority = ((this.severity - o.severity) + (K * (o.arrivalTime - this.arrivalTime)));
        if (priority == 0) return o.name.compareTo(this.name);
        return priority > 0 ? 1 : -1;
    }


}
