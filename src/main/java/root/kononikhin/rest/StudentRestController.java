package root.kononikhin.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import root.kononikhin.CustomExceptions.StudentNotFoundException;
import root.kononikhin.pojo.Student;
import root.kononikhin.pojo.StudentErrorResponse;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> studentList;

    @PostConstruct
    public void loadData() {
        studentList = new ArrayList<>();

        studentList.add(new Student("test", "test1"));
        studentList.add(new Student("test2", "test22"));
        studentList.add(new Student("test3", "test33"));
        studentList.add(new Student("test4", "test44"));

    }

    @GetMapping("/students")
    public List<Student> getStudents() {

        return studentList;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        if (studentId >= studentList.size() || studentId < 0) {
            throw new StudentNotFoundException("Student id is not found " + studentId);
        }
        return studentList.get(studentId);
    }

}
