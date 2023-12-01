// Tan Khang Hou
// A0265709U

import java.util.*;

/** Conditions
 * 1. Construct the background for the final frame using info from both frames
 *  - Displacement can go out of bounds, meaning it'll just disappear from frame
 * 2. Obtain initial displacement of shadow and extrapolate to the final frame
 * 3. Handle single and double backslash input
 */

/**
 * Test cases
 * 1. Shadow moving bottom-left
 */
public class falcondive2 {

    // Obtains start and end coords of target characters
    public static void scanFrame(char[][] frame, int[] coords, char targetChar) {
        boolean found = false;
        for (int i = 0; i < frame.length; i++) {
            char[] row = frame[i];
            for (int j = 0; j < row.length; j++) {
                char c = row[j];
                frame[i][j] = c;
                if (c == targetChar) {
                    if (!found) {
                        coords[0] = i;
                        coords[1] = j;
                        found = true;
                        break;
                    }
                }
            }

        }

    }

    public static String extrapolateFrame(String[] frame1, String[] frame2, int[] displacement, char targetChar) {
//        char[][] newFrame = new char[frame1.length][frame1[0].length()];
        StringBuilder sb = new StringBuilder();
        // Fill up the background only first
        List<int[]> targetCoordsList = new ArrayList<>();
        for (int i = 0; i < frame2.length; i++) {
            for (int j = 0; j < frame2[0].length(); j++) {
                char c = frame2[i].charAt(j);
                if (c == targetChar) {
                    // Get the background char from the other frame, since curr frame covered by falcon shadow
//                    newFrame[i][j] = frame1[i].charAt(j);
                    sb.append(frame1[i].charAt(j));
                    targetCoordsList.add(new int[]{i, j});
                } else {
//                    newFrame[i][j] = frame2[i].charAt(j);
                    sb.append(frame2[i].charAt(j));
                }
            }
            sb.append('\n');
        }

        // Re-add the removed target characters with the correct displacement
        for (int i = 0; i < targetCoordsList.size(); i++) {
            int x = targetCoordsList.get(i)[0];
            int y = targetCoordsList.get(i)[1];
            int newX = x + displacement[0];
            int newY = y + displacement[1];
//            System.out.println("x " + x + " y " + y + " newX " + newX + " newY " + newY);
            if (newX < frame2.length && newY < frame2[0].length() && newX >= 0 && newY >= 0) {
//                newFrame[newX][newY] = targetChar;
                // Offset the offset by the number of newlines. Since they also count when checking the index of the
                // replace offset. x is the offset they need
                int offset = newX * frame2[0].length() + newY + newX;
                sb.replace(offset, offset+1, String.valueOf(targetChar));
            }
        }
        return sb.toString();
    }

    public static void scanCoordsFromRow(String row, int[] coords, int rowIndex, char targetChar) {
        int charIndex = row.indexOf(targetChar);
        if (charIndex != -1) {
            coords[0] = rowIndex;
            coords[1] = charIndex;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // How many game sets they played
        int rows = sc.nextInt();
        int cols = sc.nextInt();
        char C = sc.next().charAt(1);

        // X and Y coords
        String[] frame1 = new String[rows];
        String[] frame2 = new String[rows];

        sc.nextLine();
        // Frame 1
        int rowIndex = 0;
        // Start and end coords
        boolean found = false;

        int[] coords1 = new int[2];
        int[] coords2 = new int[2];
        while (sc.hasNext()) {
            String row = sc.nextLine();
            if (row.isEmpty()) break;
            frame1[rowIndex] = row;
            //
            scanCoordsFromRow(row, coords1, rowIndex, C);
            rowIndex++;
        }

        // Frame 2
        rowIndex = 0;
        while (sc.hasNext()) {
            String row = sc.nextLine();
            if (row.isEmpty()) break;
            frame2[rowIndex] = row;
            scanCoordsFromRow(row, coords2, rowIndex, C);
            rowIndex++;
        }

        int[] displacement = new int[2];
        // NOTE: Do not use the 2nd row of coords. It doesn't work coz if there's only one char, then the
        // 2nd row would just be default 0
        int x1 = coords1[0];
        int x2 = coords2[0];
        int y1 = coords1[1];
        int y2 = coords2[1];

        displacement[0] = x2-x1;
        displacement[1] = y2-y1;

        String newFrame = extrapolateFrame(frame1, frame2, displacement, C);
        // For debugging purposes only
        System.out.println(newFrame);
//        for (int i = 0; i < newFrame.length; i++) {
//            for (int j = 0; j < newFrame[0].length; j++) {
//                char c = newFrame[i][j];
//                System.out.print(c);
//            }
//            System.out.println();
//        }
//        for (int i = 0 ; i < coords2.length; i++) {
//            System.out.println(coords2[0][0] + " " + coords2[0][1]);
//            System.out.println(coords2[1][0] + " " + coords2[1][1]);
//        }

//        for (int i = 0; i < frame1.length; i++) {
//            for (int j = 0; j < frame1[0].length; j++) {
//                System.out.print(frame1[i][j]);
//            }
//            System.out.println();
//        }
//        for (int i = 0; i < frame2.length; i++) {
//            for (int j = 0; j < frame2[0].length; j++) {
//                System.out.print(frame2[i][j]);
//            }
//            System.out.println();
//        }
        sc.close();
    }
}