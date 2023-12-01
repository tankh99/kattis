import java.util.*;
public class cutinline {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int noOfPeople = sc.nextInt();
        sc.nextLine();
        List<String> queue = new ArrayList<>();
        for (int i = 0; i < noOfPeople; i++) {
            String personName = sc.nextLine();
            queue.add(personName);
        }
        int noOfEvents = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < noOfEvents; i++) {
            String[] eventData = sc.nextLine().split(" ");
            String action = eventData[0];
            String person = eventData[1];
            String target = "";
            if (eventData.length > 2) {
                target = eventData[2];
            }
            switch(action) {
                case "leave":
                    queue.remove(queue.indexOf(person));
                    break;
                case "cut":
                    queue.add(queue.indexOf(target), person);
                    break;
            }
        }


        for (int i = 0; i < queue.size(); i++) {
            System.out.println(queue.get(i));
        }

    }

}
