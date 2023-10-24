package aop;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class University {
    private List<Student> students = new ArrayList<>();

    public void addStudents(){
        Student st1 = new Student("Nurzhan Dosmagambetov", 3, 73.24);

        Student st2 = new Student("Danil Podlesniy", 2, 62.47);
        Student st3 = new Student("Vlad Sokoloskiy", 4, 85.29);

        students.add(st1);
        students.add(st2);
        students.add(st3);
    }

    public List<Student> getStudents(){
        System.out.println("Начало работы метода getStudents");
//        System.out.println(students.get(3)); // Exception OutOfBounds
        System.out.println("Information from method getStudents: ");
        System.out.println(students);
        return students;
    }
}
