import java.io.*;
import java.util.PriorityQueue;
import java.util.*;

public class doctorkattis {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        String[] line = reader.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
//        AVLTreeST<Cat, Cat> cats = new AVLTreeST<>();

        Map<String, Cat> catMap = new HashMap<>();
        int order = 0;
        for (int i = 0; i < N; i++) {
            line = reader.readLine().split(" ");
            int commandType = Integer.parseInt(line[0]);

            if (commandType == 0) {
                String name = line[1];
                int infectionLevel = Integer.parseInt(line[2]);
                Cat cat = new Cat(order++, name, infectionLevel);
//                catsQ.offer(cat);
//                infectionLevels.put(name, infectionLevel);
                cats.put(cat, cat);
                catMap.put(name, cat);
//                arriveAtClinic(cats, cat);
            } else if (commandType == 1) { // Update infection level
                String name = line[1];
                int infectionLevel = Integer.parseInt(line[2]);
//                infectionLevels.put(name, infectionLevel + infectionLevels.get(name));
                Cat cat = catMap.get(name);
                cats.delete(cat);
                cat.infectionLevel += infectionLevel;
                cats.put(cat, cat);
//                Cat newCat = cat;
//                newCat.infectionLevel += infectionLevel;
//                Cat newCat = updateCat(cats, cat, infectionLevel);
//                catMap.put(cat.name, newCat);X/
            } else if (commandType == 2) {
                String name = line[1];
                Cat cat = catMap.get(name);
//                catsQ.remove(cat);
                catMap.remove(name);
//                Cat deleted = dischargeCat(cats, cat);
                cats.delete(cat);
//                deleted = null;
            } else {
                String query = query(cats);
                pw.println(query);
//                Cat query = catsQ.peek();
//                if (query == null) pw.println("The clinic is empty");
//                else {
////                    pw.println(query.name);
//                }
            }
        }

        pw.close();

    }

    public static void arriveAtClinic(TreeMap<Cat, Cat> cats, Cat cat) {
        cats.put(cat, cat);
    }

    public static Cat updateCat(TreeMap<Cat, Cat> cats, Cat cat, int newInfectionLevel) {
        cats.remove(cat);
        cat.infectionLevel += newInfectionLevel;
        cats.put(cat, cat);
        return cat;
    }

    public static Cat dischargeCat(TreeMap<Cat, Cat> cats, Cat cat) {
        Cat deleted = cats.remove(cat);
        return deleted;
//        System.out.println(deleted);
    }

    public static String  query(AVLTreeST<Cat, Cat> cats) {
        if (cats.size() == 0) return "The clinic is empty";
//        return cats.lastEntry().getValue().name;
        return cats.max().name;
    }


}

class Cat implements Comparable<Cat> {
    public int order;
    public String name;
    public int infectionLevel;

    public Cat(int order, String name, int infectionlevel) {
        this.order = order;
        this.name = name;
        this.infectionLevel = infectionlevel;
    }

    @Override
    public int compareTo(Cat o) {
        if (this.infectionLevel - o.infectionLevel == 0) return o.order - this.order;
        return this.infectionLevel - o.infectionLevel;
    }
}

class AVLTreeST<Key extends Comparable<Key>, Value> {

    private Node root;

    /**
     * This class represents an inner node of the AVL tree.
     */
    private class Node {
        private final Key key;   // the key
        private Value val;       // the associated value
        private int height;      // height of the subtree
        private int size;        // number of nodes in subtree
        private Node left;       // left subtree
        private Node right;      // right subtree

        public Node(Key key, Value val, int height, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
            this.height = height;
        }
    }

    public AVLTreeST() {
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) return -1;
        return x.height;
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        Node x = get(root, key);
        if (x == null) return null;
        return x.val;
    }

    private Node get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 0, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, val);
        }
        else if (cmp > 0) {
            x.right = put(x.right, key, val);
        }
        else {
            x.val = val;
            return x;
        }
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return balance(x);
    }

    private Node balance(Node x) {
        if (balanceFactor(x) < -1) {
            if (balanceFactor(x.right) > 0) {
                x.right = rotateRight(x.right);
            }
            x = rotateLeft(x);
        }
        else if (balanceFactor(x) > 1) {
            if (balanceFactor(x.left) < 0) {
                x.left = rotateLeft(x.left);
            }
            x = rotateRight(x);
        }
        return x;
    }

    private int balanceFactor(Node x) {
        return height(x.left) - height(x.right);
    }

    private Node rotateRight(Node x) {
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        y.size = x.size;
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));
        return y;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        y.size = x.size;
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));
        return y;
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;
        root = delete(root, key);
    }


    private Node delete(Node x, Key key) {
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = delete(x.left, key);
        }
        else if (cmp > 0) {
            x.right = delete(x.right, key);
        }
        else {
            if (x.left == null) {
                return x.right;
            }
            else if (x.right == null) {
                return x.left;
            }
            else {
                Node y = x;
                x = min(y.right);
                x.right = deleteMin(y.right);
                x.left = y.left;
            }
        }
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return balance(x);
    }

    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("called deleteMin() with empty symbol table");
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return balance(x);
    }

    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("called deleteMax() with empty symbol table");
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return balance(x);
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
        if (isEmpty()) throw new NoSuchElementException("called floor() with empty symbol table");
        Node x = floor(root, key);
        if (x == null) return null;
        else return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        Node y = floor(x.right, key);
        if (y != null) return y;
        else return x;
    }

    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        if (isEmpty()) throw new NoSuchElementException("called ceiling() with empty symbol table");
        Node x = ceiling(root, key);
        if (x == null) return null;
        else return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0) return ceiling(x.right, key);
        Node y = ceiling(x.left, key);
        if (y != null) return y;
        else return x;
    }

    public Key select(int k) {
        if (k < 0 || k >= size()) throw new IllegalArgumentException("k is not in range 0-" + (size() - 1));
        Node x = select(root, k);
        return x.key;
    }


    private Node select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k - t - 1);
        else return x;
    }

    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to rank() is null");
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }

    public int size(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to size() is null");
        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }
}

class KeyValuePair implements Comparable<KeyValuePair> {
    int key, id;

    public KeyValuePair(int key, int id) {
        super();
        this.key = key;
        this.id = id;
    }

    public int compareTo(KeyValuePair o) {
        if (key == o.key) {
            return (int) (id - o.id);
        } else {
            return key - o.key;
        }
    }

    public String toString() {
        return "(" + Integer.toString(key) + " " + Integer.toString(id) + ")";
    }
}
