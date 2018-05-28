package in.ogmatech.edutech.edutechservice.repository;

import in.ogmatech.edutech.edutechservice.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
