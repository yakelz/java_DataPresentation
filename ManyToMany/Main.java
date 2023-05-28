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
        s.getCourseStudents("Программирование");
        s.removeStudentFromCourse("Леонид", "Программирование");
        s.getStudentCourses("Леонид");
        s.getCourseStudents("Программирование");
        s.removeStudent("Леонид");
        s.getStudentCourses("Леонид");
        s.getCourseStudents("Математика");
        s.removeCourse("Математика");
        s.getCourseStudents("Математика");
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

                s.students.insert(new Student(parts[0]));
                s.courses.insert(new Course(parts[1]));
                s.addStudentToCourse(parts[0], parts[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
