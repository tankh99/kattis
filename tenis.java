// Tan Khang Hou
// A0265709U

import java.util.*;

/** Invalid Conditions
 * 1. < 6 sets played for both sides
 * 2. Only one side has >= 6 games won, but diff is < 2 OR (both sides have >= 6 games but diff is < 1
 *  AND its the first two sets)
 * 3. Both sides have the same number of sets won without a tiebreaker
 * 4. Federer loses ANY set
 * 5, A tiebreaker game is played without actually needing it (e.g. 6:0, 6:0, 7:6)
 * 6. > 3 sets or < 2 sets played
 */
public class tenis {
    public static boolean isValid(String[] gameData, String[] names) {
        // Case 6
        if (gameData.length > 3 || gameData.length < 2) return false;
        int leftWins = 0;
        int rightWins = 0;
        for (int i = 0; i < gameData.length; i++) {
            String data = gameData[i];
            String[] scoreStrs = data.split(":");
            int[] scores = new int[scoreStrs.length];
            for (int j = 0; j < scoreStrs.length; j++) {
                scores[j] = Integer.parseInt(scoreStrs[j]);
            }
            int leftScore = scores[0];
            int rightScore = scores[1];
            // Case 5: Someone already won, should not be looping AGAIN
            if (leftWins == 2 || rightWins == 2) return false;
            // case 1: Not 6 games played yet
            if (Math.max(leftScore, rightScore) < 6) return false;

            // Case 2b: Games played >= 6, but still a tie
            if (leftScore == rightScore) return false;

            // Case 2a: Score diff != 2 (Valid only if tiebreaker rounds for 1st and 2nd)
            int scoreDiff = Math.abs(leftScore - rightScore);
            boolean isTieBreaker = leftScore >= 6 && rightScore >= 6;
            if (i < 2) {
                // False if
                // 1. is the 3rd set (since it needs 2 conseucitve games)
                // 2. 1st and 2nd set, but it is NOT a tiebreaker round
                // 3. If 3rd around BUT diff is not equals to 2 if both scores are beyond
                // 4. Ensure only one final game is played for rounds 1 and 2
                if (isTieBreaker && Math.max(leftScore, rightScore) > 7) return false;
                if (!isTieBreaker && scoreDiff < 2) return false;
            } else {
                if (scoreDiff != 2 && isTieBreaker) return false;
            }

            // Case 4: Federer lost a game
            if ((names[0].equals("federer") && leftScore < rightScore) ||
                    names[1].equals("federer") && rightScore < leftScore) {
                return false;
            }

            leftWins += leftScore > rightScore ? 1 : 0;
            rightWins += leftScore < rightScore ? 1 : 0;
        }

        // Case 3: Same wins without tiebreaker
        if (leftWins == rightWins) return false;

        return true;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] names = sc.nextLine().split(" ");
        // How many game sets they played
        int games = sc.nextInt();
        sc.nextLine();
        int counter = 1;

        while (sc.hasNext()) {
            String[] gameData = sc.nextLine().split(" ");
            boolean valid = isValid(gameData, names);
            if (valid) {
                System.out.println("da");
            } else {
                System.out.println("ne");
            }
        }
        sc.close();
    }
}