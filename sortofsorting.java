//import java.util.*;
//
//
//class Student {
//    String lastName;
//
//    public Student(String lastName) {
//        this.lastName = lastName;
//    }
//
//    @Override
//    public String toString() {
//        return lastName;
//    }
//}
//
//public class sortofsorting {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        while (sc.hasNext()) {
//            List<Student> students = new ArrayList<>();
//            int noOfNames = sc.nextInt();
//            sc.nextLine();
//            for (int i = 0; i < noOfNames; i++) {
//                students.add(new Student(sc.nextLine()));
////                names[i] = sc.nextLine();
//            }
//            Collections.sort(students, new Comparator<Student>() {
//                @Override
//                public int compare(Student s1, Student s2) {
//                    String firstTwoLetters1 = s1.lastName.substring(0, Math.min(2, s1.lastName.length()));
//                    String firstTwoLetters2 = s2.lastName.substring(0, Math.min(2, s2.lastName.length()));
//                    return firstTwoLetters1.compareTo(firstTwoLetters2);
//                }
//            });
//
//            for (Student student : students) {
//                System.out.println(student);
//            }
//            System.out.println();
//
//        }
//    }
//
//}
