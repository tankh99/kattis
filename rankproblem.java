import java.util.*;

public class rankproblem {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int teams = sc.nextInt();
        int gamesPlayed = sc.nextInt();
        sc.nextLine();
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < teams; i++) {
            positions.add(i+1);
        }
        while (sc.hasNext()) {
            String[] contestants = sc.nextLine().split(" ");
            int team1Val = Integer.parseInt(contestants[0].substring(1));
            int team2Val = Integer.parseInt(contestants[1].substring(1));
            // Find index of the team value inside the positions array
//            System.out.println("Team 1 Val " + team1Val);
//            System.out.println("Team 2 Val " + team2Val);

            int team1Pos = positions.indexOf(team1Val);
            int team2Pos = positions.indexOf(team2Val);

//            System.out.println("Team 1 Pos " + team1Pos);
//            System.out.println("Team 2 Pos " + team2Pos);
            if (team1Pos > team2Pos) {

                positions.remove(team2Pos);
                positions.add(team1Pos, team2Val);
            }


        }
        for (int i = 0; i < positions.size(); i++) {
            System.out.print("T" + positions.get(i) + " ");
        }
        System.out.println();
        sc.close();
    }

    public static void swapAndShift(int[] array, int winnerIndex, int loserIndex) {
        // Winner was originally higher-placed than loser, so ignore
        if (winnerIndex < loserIndex) return;
        int temp = array[loserIndex];
        for (int i = loserIndex; i < winnerIndex; i++) {
            array[i] = array[i+1];
        }
        array[winnerIndex] = temp;
    }
    public static int findIndex(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) return i;
        }
        return -1;
    }
}