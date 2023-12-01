import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class coursescheduling {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        Map<String, Integer> courseStudentCount = new HashMap<>(); // Counts number of students enroled for a particular course
        Map<String, Set<String>> studentCourseMap = new HashMap<>(); // Counts courses each student enrolled in
        for (int i = 0; i < n; i++) {
            line = reader.readLine().split(" ");
            String firstName = line[0];
            String lastName = line[1];
            String course = line[2];
            String name = firstName + " " + lastName;
//            System.out.println(name);
            if (studentCourseMap.containsKey(name) && studentCourseMap.get(name).contains(course)) continue;
            int studentCount = courseStudentCount.getOrDefault(course, 0);
            studentCount++;
            courseStudentCount.put(course, studentCount);
            Set<String> studentCourseSet = studentCourseMap.getOrDefault(name, new HashSet<>());
            studentCourseSet.add(course);
            studentCourseMap.put(name, studentCourseSet);
        }
        List<CourseSchedule> output = new ArrayList<>();

        for (Map.Entry<String, Integer> entry: courseStudentCount.entrySet()) {
//            System.out.println(entry.getKey() + " " + entry.getValue());
            CourseSchedule cs = new CourseSchedule(entry.getKey(), entry.getValue());
            output.add(cs);
        }
        Collections.sort(output);

        for (int i = 0; i < output.size(); i++) {
            System.out.println(output.get(i).course + " " + output.get(i).count);
        }
    }
}

class CourseSchedule  implements Comparable<CourseSchedule> {
    public String course;
    public int count;

    public CourseSchedule(String course, int count) {
        this.course = course;
        this.count = count;
    }

    @Override
    public int compareTo(CourseSchedule o) {
        return this.course.compareTo(o.course);
    }
}
