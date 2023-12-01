import java.util.*;

public class height {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int datasets = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < datasets; i++) {
            String[] data = sc.nextLine().split(" ");
            long[] heights = new long[20];
//            List<Integer> heights = new ArrayList<>();
            for (int j = 1; j < data.length; j++) {
                heights[j-1] = Long.parseLong(data[j]);
//                heights.add(Integer.parseInt(data[j]));
            }


            int swaps = 0;
//            for (int j = 0; j < heights.size(); j++) {
//                int curr = heights.get(j);
//                for (int k = 0; k < heights.size(); k++) {
//                    if (heights.get(k) < curr) {
//                        heights.add(j, heights.get(k));
//                        heights.remove(k);
//                        swaps += k;
//                        break;
//                    }
//                }
//            }
            for (int j = 1; j < heights.length; j++) {
                long curr = heights[j];
                int k = j-1;
                while (k >= 0 && curr < heights[k]) {
//                    swap(heights, j, j-1);
                    heights[k+1] = heights[k];
                    swaps++;
                    k--;
                }
                heights[k+1] = curr;

            }
            System.out.println(data[0] +" " + swaps);
        }


        sc.close();
    }

    public static void swap(long[] array, int i, int j) {
        long temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}