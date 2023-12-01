import java.util.*;
import java.io.*;

class Book implements  Comparable<Book> {
    public String title;
    public int pages;
    public long timeFromNow;


    @Override
    public int compareTo(Book o) {
        return title.compareTo(o.title) != 0
                ? title.compareTo(o.title)
                : Long.compare(timeFromNow, o.timeFromNow);
    }

    public Book(String title, int pages) {
        this.title = title;
        this.pages = pages;
    }
}

class GiftedBook extends Book {

    public GiftedBook(String title, int pages, long timeFromNow) {
        super(title, pages);
        this.timeFromNow = timeFromNow;
    }
}

// This requires a priority queue, not a list!

public class janeeyre {
    public static void main(String[] args)  throws  IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        String[] line = reader.readLine().split(" ");
        int unreadBooks = Integer.parseInt(line[0]);
        int booksGifted = Integer.parseInt(line[1]);
        int pagesInJaneEyre = Integer.parseInt(line[2]);

        PriorityQueue<Book> queue = new PriorityQueue<>();
        List<GiftedBook> giftQueue = new ArrayList<>();
        for (int i = 0; i < unreadBooks; i++) {
//            line = reader.readLine().split(" ");
            String data = reader.readLine();
            int bookIndex = data.indexOf("\"");
            int bookEndIndex = data.indexOf("\"", bookIndex + 1);
            String title = data.substring(bookIndex+1, bookEndIndex);
            int pages = Integer.parseInt(data.substring(bookEndIndex + 2));
            Book book = new Book(title, pages);

            queue.offer(book);
        }

        Book janeEyre = new Book("Jane Eyre", pagesInJaneEyre);
        queue.offer(janeEyre);

        for (int i = 0; i < booksGifted; i++) {
            String data = reader.readLine();
            int bookIndex = data.indexOf("\"");
            int bookEndIndex = data.indexOf("\"", bookIndex + 1);
            String title = data.substring(bookIndex+1, bookEndIndex);
            long timeFromNow = Long.parseLong(data.substring(0, bookIndex-1));
            int pages = Integer.parseInt(data.substring(bookEndIndex + 2));
            GiftedBook book = new GiftedBook(title, pages, timeFromNow);
            giftQueue.add(book);
        }

        long timeTaken = 0;
        Collections.sort(giftQueue, new Comparator<GiftedBook>() {
            @Override
            public int compare(GiftedBook o1, GiftedBook o2) {
                return Long.compare(o1.timeFromNow, o2.timeFromNow);
            }
        });

//        System.out.println(giftQueue.peek().timeFromNow);
        int giftIndex = 0;
        while (queue.peek() != null) {
            Book book = queue.poll();
            timeTaken += book.pages;
//            System.out.println("Reading " + book.title);
//            System.out.println("Reading " + book.title + " | Time Elapsed " + timeTaken);
            if (book.title.equals("Jane Eyre")) {
                break;
            }

            while (giftIndex < giftQueue.size() && giftQueue.get(giftIndex).timeFromNow <= timeTaken) {
                GiftedBook giftedBook = giftQueue.get(giftIndex++);
//                System.out.println(giftedBook.timeFromNow + " " + timeTaken);
//                System.out.println("Offering " + giftedBook.title);
                queue.offer(giftedBook);
            }
//            System.out.println(queue.peek().title);
        }

        pw.println(timeTaken);
        pw.close();

    }

}
