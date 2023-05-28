package ManyToMany;

public class Database {
    public StudentsDictionary students;
    public CoursesDictionary courses;

    public Database() {
        students = new StudentsDictionary();
        courses = new CoursesDictionary();
    }

    // добавить студента на конкретный курс
    public void addStudentToCourse(String student, String course){
        // Смотрим, есть ли у нас такой студент в списке вообще
        Student s = students.findStudent(student);
        if (s == null) {
            throw new RuntimeException("addStudentToCourse(): Студент не найден");
        }
        // Есть ли у нас такой курс в списке вообще
        Course c = courses.findCourse(course);
        if (c == null) {
            throw new RuntimeException("addStudentToCourse(): Курс не найден");
        }
        Relation relation = new Relation(null, null);

        // Если студент не подписан на никакие курсы
        if (s.courses == null) {
            relation.student = s;
        }
        // Если у него есть какие либо курсы
        else {
            //Смотрим, подписан ли он на конкретно этот курс
            if (isStudentSubscribed(s,c)) {
                // Если подписан, то добавлять его не нужно
                return;
            }
            else {
                relation.student = s.courses;
            }
        }
        s.courses = relation;

        // Если у курса нет никаких студентов
        if (c.students == null) {
            relation.course = c;
        }
        // Если есть какие либо студенты
        else {
            //Смотрим, есть ли среди студентов уже этот студент
            if (isCourseMember(s,c)) {
                // Если студент уже на курсе, добавлять его не нужно
                return;
            }
            else {
                relation.course = c.students;
            }
        }
        c.students = relation;
    }

    // Удалить студента с курса
    public void removeStudentFromCourse(String student, String course){
        // Смотрим, есть ли у нас такой студент в списке вообще
        Student s = students.findStudent(student);
        if (s == null) {
            throw new RuntimeException("removeStudentFromCourse(): Студент не найден");
        }
        // Есть ли у нас такой курс в списке вообще
        Course c = courses.findCourse(course);
        if (c == null) {
            throw new RuntimeException("removeStudentFromCourse(): Курс не найден");
        }

        // Если студент не является участником курса
        if (!isStudentSubscribed(s, c) || !isCourseMember(s, c)) {
            System.out.println("Cтудент не является участником курса");
            return;
        }
        // Находим и убираем связи
        Entity relation = getRelation(s,c);
        removeRelation(relation, s);
        removeRelation(relation, c);
    }

    // Список студентов, посещающих конкретный курс
    public void getCourseStudents(String name){
        // Смотрим есть ли у нас такой курс в списке вообще
        Course c = courses.findCourse(name);
        if (c == null) {
            throw new RuntimeException("getCourseStudents(): Курс не найден");
        }
        // Смотрим список студентов, если его нет => нет студентов на курсе
        Entity students = c.students;
        if (students == null) {
            System.out.println("На курсе «" + c.name + "» нет студентов");
            return;
        }

        // Выводим студентов курса
        System.out.println("Студенты курса «" + c.name + "»: ");
        int i = 0;
        // Смотрим всех студентов: идем по связям до реального класса студента
        while (students.isRelation()) {
            Entity student = students;
            while (student.isRelation())
                student = ((Relation) student).student;
            // Вывели имя студента
            System.out.println(++i + ") " + ((Student) student).name);
            // Идем по следующей связи
            students = ((Relation) students).course;
        }
        System.out.println();
    }

    // Список всех курсов у студента
    public void getStudentCourses(String name){
        // Смотрим, есть ли у нас такой студент в списке вообще
        Student s = students.findStudent(name);
        if (s == null) {
            throw new RuntimeException("getStudentCourses(): Студент не найден");
        }
        // Смотрим его курсы, если их нет => значит студент никуда не записан
        Entity courses = s.courses;
        if (courses == null) {
            System.out.println("Студент никуда не записан");
            return;
        }
        // Выводим курсы студента
        System.out.println("Курсы " + name + ": ");
        int i = 0;
        // Смотрим все курсы: идем по связям до реального класса курса
        while (courses.isRelation()) {
            Entity course = courses;
            while (course.isRelation())
                course = ((Relation) course).course;
            // Вывели имя курса
            System.out.println(++i + ") " + ((Course) course).name);
            // Идем по следующей связи
            courses = ((Relation) courses).student;
        }
        System.out.println();
    }

    // Удалить студента со всех курсов (если студент, допустим, отчислился)
    public void removeStudent(String name) {
        // Смотрим, есть ли у нас такой студент в списке вообще
        Student s = students.findStudent(name);
        if (s == null) {
            throw new RuntimeException("removeStudent(): Студент не найден");
        }
        // Смотрим, есть ли уже какие-либо курсы у студента
        Entity courses = s.courses;
        if (courses == null) {
            System.out.println("Студент никуда не записан");
            return;
        }

        // Смотрим все курсы: идем по связям до реального класса курса
        while (courses.isRelation()) {
            Entity course = courses;
            while (course.isRelation())
                course = ((Relation) course).course;
            // И удаляем связи студента с курсом и курса со студентом
            removeRelation(courses, (Course) course);
            removeRelation(courses, s);
            // Идем по следующей связи
            courses = ((Relation) courses).student;
        }
    }

    // Удалить всех студентов с курса
    public void removeCourse(String name) {
        // Смотрим есть ли у нас такой курс в списке вообще
        Course c = courses.findCourse(name);
        if (c == null) {
            throw new RuntimeException("removeCourse(): Курс не найден");
        }
        // Смотрим, есть ли уже какие-либо студенты на курсе?
        Entity students = c.students;
        if (students == null) {
            System.out.println("На курсе «" + c.name + "» нет студентов");
            return;
        }

        // Смотрим всех студентов: идем по связям до реального класса студента
        while (students.isRelation()) {
            Entity student = students;
            while (student.isRelation())
                student = ((Relation) student).student;
            // И удаляем связи курса со студентом и студента с курсом
            removeRelation(students, (Student) student);
            removeRelation(students, c);
            // Идем по следующей связи
            students = ((Relation) students).course;
        }
    }

    // Поиск записи о студенте "s" в списке студентов курса "c"
    // Если запись найдена - true
    // => студент уже является участником курса
    private boolean isCourseMember (Student s, Course c) {
        // Список студентов записанных на курс
        Entity record = c.students;
        // Смотрим всех студентов: идем по связям до реального класса студента
        while (record.isRelation()) {
            Entity student = record;
            while (student.isRelation())
                student = ((Relation) student).student;
            // И проверяем, является ли этот студент участником курса?
            if ((Student) student == s)
                return true;
            // Смотрим следующую связь
            record = ((Relation) record).course;
        }
        return false;
    }

    // Поиск записи о курсе "c" в списке курсов студента "s"
    // Если запись найдена - true
    // => студент уже подписан на данный курс
    private boolean isStudentSubscribed (Student s, Course c) {
        // Список курсов у студента
        Entity record = s.courses;
        // Смотрим все курсы: идем по связям до реального класса курса
        while (record.isRelation()) {
            Entity course = record;
            while (course.isRelation())
                course = ((Relation) course).course;
            // И проверяем записан ли студент на данный курс?
            if ((Course) course == c)
                return true;
            // Смотрим следующую связь
            record = ((Relation) record).student;
        }
        return false;
    }

    // Найти нужную связь студента и курса
    private Entity getRelation(Student s, Course c) {
        // Идем по курсам студента
        Entity course = s.courses;
        // Смотрим все курсы: идем по связям до реального класса курса
        while (course.isRelation()) {
            Entity student = course;
            while (student.isRelation())
                student = ((Relation) student).course;
            // Если курс совпадает возвращаем связь
            if (((Course) student) == c)
                return course;
            // Идем дальше по связям
            course = ((Relation) course).student;
        }
        return course;
    }

    // Найти предыдущую связь от студента
    private Relation getPreviousRelation(Entity relation, Student s) {
        // Идем по курсам студента
        Entity current = s.courses;
        Entity prev = null;
        // Смотрим все курсы: идем по связям до реального класса курса
        while (current.isRelation()) {
            // Все время присваиваем prev все адреса связей
            // Пока не дойдем до нужной связи
            // Дошли до нужной => возвращаем предыдущую
            if (current == relation)
                return (Relation) prev;
            prev = current;
            //Следующая связь
            current = ((Relation) current).student;
        }
        return (Relation) prev;
    }

    // Найти предыдущую связь от курса
    private Relation getPreviousRelation(Entity relation, Course c) {
        // Идем по списку студентов на курсе
        Entity current = c.students;
        Entity prev = null;
        // Смотрим всех студентов: идем по связям до реального класса студента
        while (current.isRelation()) {
            // Все время присваиваем prev все адреса связей
            // Пока не дойдем до нужной связи
            // Дошли до нужной => возвращаем предыдущую
            if (current == relation)
                return (Relation) prev;
            prev = current;
            //Следующая связь
            current = ((Relation) current).course;
        }
        return (Relation) prev;
    }

    // Удаление связи
    // relation - текущая связь между студентом и курсом
    // student - студент, из которого нужно удалить связь
    private void removeRelation(Entity relation, Student student) {
        // Является ли связь первой в списке?
        // Если да, то это означает, что связь хранится сразу в поле courses
        if (student.courses == relation) {
            // Проверяем, есть ли дальше какие либо связи?
            if (student.courses.student.isRelation()) {
                // Если да, то меняем первую запись на следующую
                student.courses = (Relation) student.courses.student;
            }
            // Если нет, просто удаляем связь
            else {
                student.courses = null;
            }
        }
        // Если связь в списке не первая, то находим предыдущую связь
        // и присваиваем ей следующую связь, пропуская ту, которую нам нужно удалить
        else {
            Relation st = getPreviousRelation(relation, student);
            st.student = ((Relation) st.student).student;
        }
    }

    // Удаление связи
    // relation - текущая связь между студентом и курсом
    // course - курс, из которого нужно удалить связь
    private void removeRelation(Entity relation, Course course) {
        // Является ли связь первой в списке?
        // Если да, то это означает, что связь хранится сразу в поле students
        if (course.students == relation) {
            // Проверяем, есть ли дальше какие либо связи?
            if (course.students.course.isRelation()) {
                // Если да, то меняем первую запись на следующую
                course.students = (Relation) course.students.course;
            }
            // Если нет, просто удаляем связь
            else {
                course.students = null;
            }
        }
        // Если связь в списке не первая, то находим предыдущую связь
        // и присваиваем ей следующую связь, пропуская ту, которую нам нужно удалить
        else {
            Relation cour = getPreviousRelation(relation, course);
            cour.course = ((Relation) cour.course).course;
        }
    }
}
