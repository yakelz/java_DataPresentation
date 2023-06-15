package ManyToMany;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    private static final String FILE_NAME = "ManyToMany/data.txt";

    public static void main(String[] args) {
        Database s = new Database();
        init(s);
        s.courses.print();
        s.students.print();
        s.getStudentCourses("Леонид");
        s.getCourseStudents(1);
        s.removeStudentFromCourse("Леонид", 1);
        s.getStudentCourses("Леонид");
        s.getCourseStudents(1);
        s.removeStudent("Леонид");
        s.getStudentCourses("Леонид");
        s.getCourseStudents(2);
        s.removeCourse(2);
        s.getCourseStudents(2);
    }

    private static void init(Database s) {
        try {
            FileReader reader = new FileReader(FILE_NAME);
            Scanner scanner = new Scanner(reader);

            // Пропускаем заголовки
            scanner.nextLine();
            // Заполняем массив студентов и курсов
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String input = line.trim();
                String[] parts = input.split(";");

                s.students.insert(parts[0]);
                s.courses.insert(Integer.parseInt(parts[1]));
                s.addStudentToCourse(parts[0],Integer.parseInt(parts[1]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
