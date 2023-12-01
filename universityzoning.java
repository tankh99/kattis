import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Coordinate implements Comparable<Coordinate> {
    public int row;
    public int col;
    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;

    }

    public long getDistance(Coordinate c) {
        return Math.abs(this.row - c.row) + Math.abs(this.col - c.col);
    }

    // Assumption: There are no coordinate ties
    @Override
    public int compareTo(Coordinate o) {
        if (this.row - o.row == 0) {
            return this.col-  o.col;
        }
        return this.row - o.row;
    }


    @Override
    public String toString() {
        return "row: " + this.row + ", col: " + this.col;
    }
}

class Student implements Comparable<Student> {
    // NOTE: Row and col is 0-indexed in the attributes
    public Coordinate coord;
    public int position;
    public int studentNo;
    public int facultyNo;
    public long distanceToCoord;

    public Student(int row, int col, int studentNo, int facultyNo) {
        this.coord = new Coordinate(row, col);
        this.studentNo = studentNo;
        this.facultyNo = facultyNo;
    }


    @Override
    public int compareTo(Student o2) {
        if (this.studentNo < o2.studentNo) {
            return -1;
        } else if (o2.studentNo < this.studentNo) {
            return 1;
        } else {
            return 0;
        }
    }


    @Override
    public String toString() {
        return "Student (" + this.studentNo + ") " + this.coord.toString();
    }
}
class Faculty {
    public int index;
    public int cellsAssigned;
    public int startingIndex;
    public List<Coordinate> facultyCoords;
    public Faculty(int index, int cellsAssigned, List<Coordinate> facultyCoords) {
        this.index = index;
        this.cellsAssigned = cellsAssigned;
        this.facultyCoords = facultyCoords;
//        this.startingIndex = startingIndex;
    }


}

class FacultyCoordinate {
    public Coordinate coord;
    public int facultyNo;

    public FacultyCoordinate(int facultyNo, Coordinate coord) {
        this.coord = coord;
        this.facultyNo = facultyNo;
    }

}

public class universityzoning {

    /**
     * Rows and columns are 1-indexed
     * Student with lowest number mut go to the left first
     * Every faculty needs min. T students
     * Each student line will contain their row, column, student number and faculty
     * Last line contains total students enrolled in each faculty
     * (Every faculty will always have enough space for its enrolled students, just that there may be less students than available space)
     * @param args
     */
    public static void main(String[] args){
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        try {

            String[] line = sc.readLine().split(" ");
            int rows = Integer.parseInt(line[0]);
            int cols = Integer.parseInt(line[1]);;
            int noOfFaculties = Integer.parseInt(line[2]);;
            int noOfStudents = Integer.parseInt(line[3]);;
            int facultiesThatMustMeetTarget = Integer.parseInt(line[4]);

            Map<Integer, Faculty> faculties = new HashMap<>();
            int accumCellsAssigned = 0;
            List<FacultyCoordinate> facultyCoordList = new ArrayList<>();
            // Populate list of faculties
            for (int i = 0; i < noOfFaculties; i++) {
                String[] facultyData = sc.readLine().split(" ");
                int cellsAssigned = Integer.parseInt(facultyData[0]);
                List<Coordinate> facultyCoords = new ArrayList<>();
                // Populate coords that belong to faculty
                for (int j = 1; j < facultyData.length; j+=2) {
                    // Rows & cols are 1-indexed, so - 1 to the parsed result
                    int row = Integer.parseInt(facultyData[j]);
                    int col = Integer.parseInt(facultyData[j+1]);
                    Coordinate c = new Coordinate(row, col);
                    facultyCoords.add(c);
//                    System.out.println("Facultycoords " + c + " Fac no: " + (i+1));
                    facultyCoordList.add(new FacultyCoordinate(i+1, c));
                }
                Collections.sort(facultyCoords);
                faculties.put(i+1, new Faculty(i+1, cellsAssigned, facultyCoords));
//                accumCellsAssigned += cellsAssigned-1;
            }

            // List of students by faculty
            List<Student> studentsList = new ArrayList<>();

            // Populate list of ALL students regardless of faculty
            for (int i = 0; i < noOfStudents; i++) {
                // Rows & cols are 1-indexed, so - 1 to the parsed result
                line = sc.readLine().split(" ");
                int row = Integer.parseInt(line[0]);
                int col = Integer.parseInt(line[1]);
                int studentNo = Integer.parseInt(line[2]);
                int facultyNo = Integer.parseInt(line[3]);
                Student s = new Student(row, col, studentNo, facultyNo);
                studentsList.add(s);
            }

            // Sort the students first, to obtain their position
            Collections.sort(studentsList);

            // Populate students by faculty
            Map<Integer, List<Student>> facultyStudents = new HashMap<>();
            // Done only after sorting so that faculty students are also sorted
            for (int i = 0; i < studentsList.size(); i++) {
                Student s = studentsList.get(i);

                List<Student> facultyStudentsList;
                if (facultyStudents.containsKey(s.facultyNo)) {
                    facultyStudentsList = facultyStudents.get(s.facultyNo);
                } else {
                    facultyStudentsList = new ArrayList<>();
                }

                // Sets the student's position within the faculty
                s.position = facultyStudentsList.size();

                facultyStudentsList.add(s);
                facultyStudents.put(s.facultyNo, facultyStudentsList);

            }


            int index = 0;
            // Get designated coordinate
            for (int i = 0; i < studentsList.size(); i++) {
                Student s = studentsList.get(i);

                // Update the sorted students with the distance to their designated coord
                Faculty f = faculties.get(s.facultyNo);

                Coordinate designatedCoord = f.facultyCoords.get(s.position);
                s.distanceToCoord = s.coord.getDistance(designatedCoord);;
//                System.out.println(s + " faculty no " + s.facultyNo + " distance " + s.distanceToCoord + " designated coord: " + designatedCoord + " position: " + s.position);
            }


            String[] complianceTargets = sc.readLine().split(" ");


            long[] facultyDistances = new long[faculties.size()];
            // Iterate through students by faculty
            // Obtain the distances for each student and sort by student's distance
            // Collect the minimum amount of steps required for the faculty to meet compliance target into an array
            for (Map.Entry<Integer, List<Student>> entry: facultyStudents.entrySet()) {
                List<Student> students = entry.getValue();
                // Now sort by distance to coord instead of student no
                Collections.sort(students, new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        return (int)o1.distanceToCoord - (int)o2.distanceToCoord;
                    }
                });
                int complianceTarget = Integer.parseInt(complianceTargets[index]);
                int studentsCount = 0; // Trakc the number of dtudents inside current faculty cells now
                long facultySteps = 0;
                for (int i = 0 ; i < complianceTarget; i++) {
                    if (studentsCount >= complianceTarget) break;
                    studentsCount++;
                    facultySteps += students.get(i).distanceToCoord;
                }


                facultyDistances[index] = facultySteps;
                index++;
            }

            long totalSteps = 0;
            Arrays.sort(facultyDistances);
            // Sort facultyDistances and collect the min required
            for (int i = 0; i < facultiesThatMustMeetTarget; i++) {
                totalSteps += facultyDistances[i];
            }
            System.out.println(totalSteps);
        } catch (IOException ex) {
            System.err.println(ex);
        }

    }
}
