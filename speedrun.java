import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Task implements Comparable<Task> {
    public int start;
    public int end;
    public Task(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getDuration() {
        return this.end - this.start;
    }


    @Override
    public int compareTo(Task o) {
//        if (this.start - o.start < 0) {
//            return this.getDuration() - o.getDuration();
//        }
//        return o.start - this.end;
        return this.end - o.end;
//        return (this.end - this.start) - (o.end - o.start);

    }
}
public class speedrun {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split(" ");
        int G = Integer.parseInt(line[0]);
        int N = Integer.parseInt(line[1]);
        int timeInDay = 24000;
        int time = 0;
        PriorityQueue<Task> taskQueue = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            line = reader.readLine().split(" ");
            int start = Integer.parseInt(line[0]);
            int end = Integer.parseInt(line[1]);
            Task task = new Task(start, end);
            taskQueue.offer(task);
        }

        int tasksCompleted = 0;
        while (!taskQueue.isEmpty() && tasksCompleted < G) {
            Task task = taskQueue.poll();
            if (task.start < time) continue;
            time = task.end;
            if (time > timeInDay) break;
            tasksCompleted++;
        }
        if (tasksCompleted >= G) System.out.println("YES");
        else System.out.println("NO");

    }
}
