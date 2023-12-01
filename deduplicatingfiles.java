import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class deduplicatingfiles {
    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner reader = new Scanner(System.in);

        while (reader.hasNext()) {
            Map<Integer, Map<String, Integer>> map = new HashMap<>();
            int N = reader.nextInt();
            if (N == 0) break;
            reader.nextLine();

            Set<String> uniques = new HashSet<>();
            int collisions = 0;
            for (int i = 0; i < N; i++) {
//            line = reader.readLine().split(" ");

                String file = reader.nextLine();
                int result = hash(file);
                uniques.add(file);
                if (map.containsKey(result)) {
                    Map<String, Integer> sentenceMap = map.get(result);
                    // May have same hash value, but not necessarily same string
                    sentenceMap.put(file, sentenceMap.getOrDefault(file, 0) + 1);
                } else {
                    Map<String, Integer> sentenceMap = new HashMap<>();
                    sentenceMap.put(file, 1);
                    map.put(result, sentenceMap);
                }

            }

            int expectedCollisions = getCollisions(map);
            collisions += expectedCollisions;
            System.out.println(uniques.size() + " " + collisions);


        }

    }

    public static int hash(String line) {
        byte[] bytes = line.getBytes();
        int result = bytes[0];
        for (int j = 1; j < bytes.length; j++) {
            result = (result ^ bytes[j]);
        }
        return result;
    }

    public static int getCollisions(Map<Integer, Map<String, Integer>> map) {
        int collisions = 0;

        for (Map.Entry<Integer, Map<String, Integer>> imap: map.entrySet()) {
            int hash = imap.getKey();
            Map<String, Integer> sentenceMap = map.get(hash);
            List<Integer> counts = new ArrayList<>();
            for (Map.Entry<String, Integer> entry: sentenceMap.entrySet()) {
                counts.add(entry.getValue());
//                System.out.println(entry.getKey() + " " + entry.getValue());
            }
            for (int i = 0; i < counts.size()-1; i++) {
                for (int j = i+1; j < counts.size(); j++) {
                    collisions += counts.get(i) * counts.get(j);
                }
            }
        }

//        for (Map.Entry<String, Integer> entry : sentenceMap.entrySet()) {
//            collisions += entry.getValue()
//        }
//        for (int j = 0; j < chain.size(); j++) {
//            if (!chain.get(j).equals(file)) {
//                expectedCollisions++;
//                // Don't break because we want to count all the files that are different but produce same hash
//            }
//        }
        return collisions;
    }

}
