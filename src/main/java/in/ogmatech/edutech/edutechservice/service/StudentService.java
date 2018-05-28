package in.ogmatech.edutech.edutechservice.service;

import in.ogmatech.edutech.edutechservice.model.Student;

import java.util.List;

public interface StudentService {

    boolean isExist(Student student);

    Student save(Student student);

    Student findById(Long idStudent);

    List<Student> findAll();

    Student update(Student student);

    void delete(Long idStudent);

    void deleteAll();
}
