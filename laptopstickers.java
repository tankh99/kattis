import java.util.*;

public class laptopstickers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cols = sc.nextInt();
        int rows = sc.nextInt();
        int noOfStickers = sc.nextInt();
        sc.nextLine();
        String[][] laptop = new String[rows][cols];
        for (int i = 0; i < laptop.length; i++) {
            Arrays.fill(laptop[i], "_");
        }
        String[] stickers = new String[26];
        for (int i = 0; i < 26; i++) {
            char letter = (char) ('a' + i); // You can also use 'a' + i for lowercase letters
            stickers[i] = String.valueOf(letter);
        }
//        for (int i = 0; i < stickers.length; i++) {
//            System.out.println(stickers[i]);
//        }

        for (int i = 0; i < noOfStickers; i++) {
            String[] stickerData = sc.nextLine().split(" ");
            int stickerLength = Integer.parseInt(stickerData[0]);
            int stickerHeight = Integer.parseInt(stickerData[1]);
            int col = Integer.parseInt(stickerData[2]);
            int row = Integer.parseInt(stickerData[3]);
            for (int r = row; r < row + stickerHeight; r++) {
                if (r < 0 || r >= rows) continue;
                for (int c = col; c < col + stickerLength; c++) {
                    if (c < 0 || c >= cols) continue;
                    laptop[r][c] = stickers[i];
                }
            }
        }

        for (int i = 0; i < laptop.length; i++) {
            for (int j = 0; j < laptop[0].length; j++) {
                System.out.print(laptop[i][j]);
            }
            System.out.println();
        }
    }
}
