package in.ogmatech.edutech.edutechservice.controller;

import in.ogmatech.edutech.edutechservice.exception.AlreadyExistsException;
import in.ogmatech.edutech.edutechservice.model.Student;
import in.ogmatech.edutech.edutechservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /* Create a student */
    @PostMapping("student")
    public ResponseEntity<Student> createStudent(@RequestBody Student student, UriComponentsBuilder ucBuilder) {

        if (studentService.isExist(student)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        studentService.save(student);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("student/{id}").buildAndExpand(student.getIdStudent()).toUri());

        return new ResponseEntity<>(student, headers, HttpStatus.CREATED);
    }

    /* Reading single student */
    @GetMapping(value = "student/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> getStudent(@PathVariable("id") long idStudent) {

        Student student = studentService.findById(idStudent);

        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    /*Reads all student*/
    @GetMapping(value = "student",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> listAllStudent() {

        List<Student> student = studentService.findAll();

        if (student.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    /*Update a student*/
    @PutMapping("student/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") long idStudent, @RequestBody Student student) {

        Student currentStudent = studentService.findById(idStudent);

        if (currentStudent == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentStudent.setStudentName(student.getStudentName());
        currentStudent.setIsDeleted(student.getIsDeleted());

        studentService.update(currentStudent);

        return new ResponseEntity<>(currentStudent, HttpStatus.OK);
    }

    /*Delete a student */
    @DeleteMapping("student/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable("id") long idStudent) {

        Student student = studentService.findById(idStudent);

        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        studentService.delete(idStudent);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*Delete all student*/
    @DeleteMapping("student")
    public ResponseEntity<Student> deleteAllStudent() {

        studentService.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleStudentAlreadyExistsException(AlreadyExistsException exception) {

        return exception.getMessage();
    }
}
