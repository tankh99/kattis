import java.util.Scanner;

public class mjehuric {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] orderString = sc.nextLine().split(" ");
        int[] order = new int[orderString.length];
        for (int i = 0; i <order.length; i++) {
            order[i] = Integer.parseInt(orderString[i]);
        }

        while (!isSorted(order)) {
            for (int i = 0; i < order.length-1; i++) {
                int curr = order[i];
                int next = order[i+1];
                if (curr > next) {
                    swap(order, i, i+1);
                }
            }
        }


        sc.close();
    }

    public static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length-1; i++) {
            if (array[i]+1 != array[i+1]) return false;
        }
        return true;
    }


    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        printList(array);
    }

    public static void printList(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }


}