import java.io.*;
import java.util.*;

public class congaline {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        String[] line = reader.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int Q = Integer.parseInt(line[1]); // instructions
//        Map<String, Partner> partners = new HashMap<>(2*N);
//        List<String> congaline = new ArrayList<>();
        Map<Integer, Congaliner> congaline = new HashMap<>(2*N+Q*2);
        int count = 0;
        DoublyLinkedListCL dll = new DoublyLinkedListCL();
        for (int i = 0; i < N; i++) {
            line = reader.readLine().split(" ");
//            Partner a = new Partner(line[0], count++);
//            Partner b = new Partner(line[1], count++);
//            partners.put(line[0], b);
//            partners.put(line[1], a);

            Congaliner c1 = new Congaliner(line[0]);
            Congaliner c2 = new Congaliner(line[1]);
            c1.partner = c2;
            c2.partner = c1;
            if (dll.head == null) {
                dll.pushFront(c1);
            } else {
                dll.insertAfter(dll.tail, c1);
            }
            dll.insertAfter(c1, c2);
//            congaline.add(line[0]);
//            congaline.add(line[1]);
        }
        Congaliner micHolder = dll.head;
        String commands = reader.readLine();
        for (int i = 0; i < commands.length(); i++) {
            char command = commands.charAt(i);
            if (command == 'B') {
                micHolder = moveBack(dll, micHolder);
            } else if (command == 'P') {
                pw.println(micHolder.partner.name);
            } else if (command == 'F') {
                micHolder = moveFront(dll, micHolder);
            } else if (command == 'R') {
                Congaliner temp = micHolder;
                micHolder = moveBack(dll, micHolder);
                dll.remove(temp);
                dll.insertAfter(dll.tail, temp);
            } else if (command == 'C') {
                Congaliner temp = micHolder;
                micHolder = moveBack(dll, micHolder);
                dll.remove(temp);
                dll.insertAfter(temp.partner, temp);

            }
        }
        pw.println();

        StringBuilder sb = new StringBuilder();
        dll.listprint(pw);
//        for (int i = 0; i < count; i++) {
//            pw.println(congaline.get(i).name);
//        }


        pw.close();

    }

    public static Congaliner moveBack(DoublyLinkedListCL dll, Congaliner micHolder) {

        Congaliner next = micHolder.next;
        if (next == null) return dll.head;
        return next;
    }

    public static Congaliner moveFront(DoublyLinkedListCL dll, Congaliner micHolder) {

        Congaliner back = micHolder.back;
        if (back == null) return dll.tail;
        return back;
    }

}


//class Congaliner {
//    public boolean deleted;
//    public int index;
//    public String name;
//    public Partner p;
//    public Congaliner (int index, String name) {
//        this.index = index;
//        this.name = name;
//    }

class Congaliner {
    String name;
    Congaliner next;
    Congaliner back;
    Congaliner partner;

    public Congaliner(String value) {
        this.name = value;
        this.next = null;
        this.back = null;
//        this.partner = partner;
    }
}
class DoublyLinkedListCL {
    public Congaliner head;
    public Congaliner tail;

    public DoublyLinkedListCL() {
        this.head = null;
        this.tail = null;
    }

    // Insert new node on the front of the list
    public void pushFront(Congaliner new_congaliner) {
        new_congaliner.next = this.head;
        if (this.head !=  null) {
            this.head.back = new_congaliner;
        } else {
            this.tail = new_congaliner;
        }
        this.head = new_congaliner; // Move head to point to new node
    }

    // Insert node after given node
    public void insertAfter(Congaliner prev_congaliner, Congaliner new_congaliner) {
        if (prev_congaliner == null) {
            System.out.print("Previous node cannot be null!");
            return;
        }

        boolean insertTail = prev_congaliner == this.tail ? true : false;

        new_congaliner.next = prev_congaliner.next;
        prev_congaliner.next = new_congaliner;
        new_congaliner.back = prev_congaliner;

        if (new_congaliner.next != null) {
            new_congaliner.next.back = new_congaliner;
        }

        if (insertTail) {
            this.tail = new_congaliner;
        }
    }

    // Remove node
    public void remove(Congaliner congaliner) {
        if (this.head == null || congaliner == null) return;

        // Case 1: node to be removed is head
        if (this.head == congaliner) {
            this.head = congaliner.next;
        }

        // Case 2: node to be removed is tail
        if (this.tail == congaliner) {
            this.tail = congaliner.back;
        }

        // Change prev reference for next node
        if (congaliner.next != null) {
            congaliner.next.back = congaliner.back;
        }

        // Change next reference for previous node
        if (congaliner.back != null) {
            congaliner.back.next = congaliner.next;
        }

        // Garbage collection
        congaliner.back = null;
        congaliner.next = null;
    }

    // Print contents of the linked list, starting from head
    public void listprint(PrintWriter pw) {
        Congaliner temp = this.head;
        while (temp != null) {
            pw.print(temp.name);
            pw.println();
            temp = temp.next;
        }
    }

}