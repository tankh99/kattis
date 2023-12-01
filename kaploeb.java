import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Participant implements Comparable<Participant> {
    public int startNum;
    public int timing;
    public int lapsCompleted;

    public Participant(int startNum, int timing, int lapsCompleted) {
        this.startNum = startNum;
        this.timing = timing;
        this.lapsCompleted = lapsCompleted;
    }

    @Override
    public int compareTo(Participant o) {
        return this.timing - o.timing == 0
                ? this.startNum - o.startNum
                : this.timing - o.timing;
    }
}

public class kaploeb {


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split(" ");
        int l = Integer.parseInt(line[0]); // no of lap times recorded
        int k = Integer.parseInt(line[1]); // laps needed to finish race
        int s = Integer.parseInt(line[2]); // no of start numbers handed at start of race

        HashMap<Integer, Participant> participantTimings = new HashMap<>();
        for (int i = 0; i < l; i++) {
            line = reader.readLine().split(" ");
            int startNum = Integer.parseInt(line[0]);

            String time = line[1];
            int seconds = parseTime((time));

            if (!participantTimings.containsKey(startNum)) {
                Participant participant = new Participant(startNum, seconds, 1);
                participantTimings.put(startNum, participant);
            } else {
                Participant participant = participantTimings.get(startNum);
                participant.timing += seconds;
                participant.lapsCompleted += 1;
                participantTimings.put(startNum, participant);
            }
        }

        List<Participant> ranking = new ArrayList<>();
        for (Map.Entry<Integer, Participant> entry: participantTimings.entrySet()) {
            if (entry.getValue().lapsCompleted < k) continue;
            ranking.add(entry.getValue());
        }

        Collections.sort(ranking);
        for (int i = 0; i < ranking.size(); i++) {
            System.out.println(ranking.get(i).startNum);
        }
    }

    public static int convertCharToInt(char c) {

        return Integer.parseInt(String.valueOf(c));
    }

    public static int parseTime(String time) {
        int seconds = 0;
        int minute = convertCharToInt(time.charAt(0)) * 10 + convertCharToInt(time.charAt(1));
        int second = convertCharToInt(time.charAt(3)) * 10 + convertCharToInt(time.charAt(4));
//        System.out.println(time + ":" + minute + ":" + second);
        seconds += minute * 60 + second;
//        LocalTime localTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("mm.ss"));
//        System.out.println(localTime);
        return seconds;
    }
}
