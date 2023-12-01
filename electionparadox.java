import java.util.*;

public class electionparadox {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int regions = sc.nextInt();
        List<Integer> peopleCount = new ArrayList<>();
        for (int i = 0; i < regions; i++) {
            int peopleInRegion = sc.nextInt();
            peopleCount.add(peopleInRegion);
        }
        Collections.sort(peopleCount);
        double minRegionsToLose = Math.ceil(regions/2.0);
        int maxVotes = 0;
        for (int i = 0; i < peopleCount.size(); i++) {
            int people = peopleCount.get(i);
            if (i < minRegionsToLose) {
                if (people % 2 == 0) {
                    maxVotes += people/2-1;
                } else {
                    maxVotes += people/2;
                }
            } else {
                maxVotes += people;
            }

//            System.out.println("i: " + minRegionsToLose + " " + people + " " + maxVotes);
        }
        System.out.println(maxVotes);
    }
}