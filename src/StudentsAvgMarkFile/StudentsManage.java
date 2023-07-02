package StudentsAvgMarkFile;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StudentsManage {
    public static void main(String args[]) {
        String student1FirstName = "Анна";
        String student1LastName = "Гуцуляк";
        int student1CourseMark = 8;
        MasterStudent masterStudent = new MasterStudent(student1FirstName, student1LastName, student1CourseMark);

        String student2FirstName = "Виктор";
        String student2LastName = "Тихонов";
        int student2CourseMark = 9;
        BachelorStudent bachelorStudent = new BachelorStudent(student2FirstName, student2LastName, student2CourseMark);

        String courseName = "Математика";
        int courseDuration = 5;
        Course course = new Course(courseName, courseDuration);

        course.addStudent(masterStudent);
        course.addStudent(bachelorStudent);
        String report = course.report() +
                "\n\n" +
                course.studentsReport() +
                "\n\n" +
                course.avgMarkReport();

        String pathname = "StudentsReport.txt";
        createFileIfNeeded(pathname);
        writeToFile(pathname, report);
    }

    static void createFileIfNeeded(String pathname) {
        try {
            File myObj = new File(pathname);
            if (myObj.createNewFile()) {
                System.out.println("Файл создан: " + myObj.getName());
            } else {
                System.out.println("Файл уже существует.");
            }
        } catch (IOException e) {
            System.out.println("Ошибка при создании файла.");
            e.printStackTrace();
        }
    }

    static void writeToFile(String pathname, String string) {
        try {
            FileWriter myWriter = new FileWriter(pathname);
            myWriter.write(string);
            myWriter.close();
            System.out.println("Запись в файл успешно завершена.");
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл.");
            e.printStackTrace();
        }
    }
}

class StudentsArray {
    private Student arr[];
    private int count;

    public StudentsArray(int length) {
        arr = new Student[length];
    }

    Student[] getStudents() {
        return arr;
    }

    void add(Student student) {
        if (arr.length == count) {
            Student newArr[] = new Student[2 * count];
            for (int i = 0; i < count; i++) {
                newArr[i] = arr[i];
            }
            arr = newArr;
        }
        arr[count++] = student;
    }

    void remove(int index) {
        if (index < 0 || index >= arr.length) { return; }
        Student newArr[] = new Student[arr.length - 1];
        for (int i = 0, k = 0; i < arr.length; i++) {
            if (i == index) { continue; }
            newArr[k++] = arr[i];
        }
        arr = newArr;
        count = arr.length;
    }
}

class Course {
    String name;
    int duration;
    private StudentsArray studentsArray;

    public Course(String name, int duraton) throws IllegalArgumentException {
        if (name.length() == 0) { throw new IllegalArgumentException("Название курса не должно быть пустым!"); }
        if (duraton <= 0) { throw new IllegalArgumentException("Продолжительность курса должна быть положительным числом!"); }
        this.name = name;
        this.duration = duraton;
        studentsArray = new StudentsArray(1);
    }

    void addStudent(Student student) {
        studentsArray.add(student);
    }

    void removeStudent(int index) {
        studentsArray.remove(index);
    }

    String report() {
        return "Название курса: " + name +
                "\n" +
                "Продолжительность курса: " + duration + " лет";
    }

    String studentsReport() {
        Student arr[] = studentsArray.getStudents();
        String report = "";
        for (int i = 0; i < arr.length; i++) {
            Student student = arr[i];
            report += student.report(i + 1);
            if (i < arr.length - 1) { report += "\n"; }
        }
        return report;
    }

    String avgMarkReport() {
        Student arr[] = studentsArray.getStudents();
        double sum = 0;
        for (int i = 0; i < arr.length; i++) {
            Student student = arr[i];
            sum += student.courseMark;
        }
        return "Средняя оценка по группе: " + sum / arr.length;
    }
}

abstract class Student {
    String firstName, lastName;
    String type;
    int courseMark;

    String report(int index) {
        return index + ". " + "Студент: " + lastName + " " + firstName +
                "\n" +
                "Тип: " + type +
                "\n" +
                "Оценка: " + courseMark;
    }

    public Student(String firstName, String lastName, String type, int courseMark) throws IllegalArgumentException {
        if (firstName.length() == 0) { throw new IllegalArgumentException("Имя студента не должно быть пустым!"); }
        if (lastName.length() == 0) { throw new IllegalArgumentException("Фамилия студента не должна быть пустой!"); }
        if (type.length() == 0) { throw new IllegalArgumentException("Тип студента не должен быть пустым!"); }
        if (courseMark <= 0) { throw new IllegalArgumentException("Оценка студента должна быть положительной!"); }
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.courseMark = courseMark;
    }
}

class MasterStudent extends Student {
    public MasterStudent(String firstName, String lastName, int courseMark) throws IllegalArgumentException {
        super(firstName, lastName, "Мастер", courseMark);
    }
}

class BachelorStudent extends Student {
    public BachelorStudent(String firstName, String lastName, int courseMark) throws IllegalArgumentException {
        super(firstName, lastName, "Бакалавр", courseMark);
    }
}
