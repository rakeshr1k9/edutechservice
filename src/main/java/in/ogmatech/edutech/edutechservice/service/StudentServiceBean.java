package in.ogmatech.edutech.edutechservice.service;

import in.ogmatech.edutech.edutechservice.exception.AlreadyExistsException;
import in.ogmatech.edutech.edutechservice.model.Student;
import in.ogmatech.edutech.edutechservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
@Transactional
public class StudentServiceBean implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public boolean isExist(Student student) {
        return findById(student.getIdStudent())!=null;
    }

    @Override
    public Student save(Student student) {
        Student existing = studentRepository.findOne(student.getIdStudent());

        if (existing != null) {
            throw new AlreadyExistsException(
                    String.format("There already exists a student with id = %s", student.getIdStudent()));
        }

        return studentRepository.save(student);
    }

    @Override
    public Student findById(Long idStudent) {
        return studentRepository.findOne(idStudent);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student update(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void delete(Long idStudent) {
        studentRepository.delete(idStudent);
    }

    @Override
    public void deleteAll() {
        studentRepository.deleteAll();
    }
}
