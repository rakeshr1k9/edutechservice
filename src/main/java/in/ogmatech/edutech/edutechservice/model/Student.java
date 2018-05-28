package in.ogmatech.edutech.edutechservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "student")
@EntityListeners(AuditingEntityListener.class)
public class Student implements Serializable {

    private Long idStudent;
    private String studentRollNo;
    private String studentName;
    private Byte isDeleted;
    private Date studentCat;
    private Date studentUat;
    private Integer studentFee;
    private Long classId;

    @Id
    @GeneratedValue
    @Column(name = "id_student", nullable = false)
    public Long getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Long idStudent) {
        this.idStudent = idStudent;
    }

    @Column(name = "student_roll_no", nullable = true, length = 10)
    public String getStudentRollNo() {
        return studentRollNo;
    }

    public void setStudentRollNo(String studentRollNo) {
        this.studentRollNo = studentRollNo;
    }

    @Column(name = "student_name", nullable = true, length = 45)
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Column(name = "is_deleted", nullable = false)
    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    @JsonIgnoreProperties(allowGetters = true)
    @Column(name = "student_cat", nullable = true, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    public Date getStudentCat() {
        return studentCat;
    }

    public void setStudentCat(Date studentCat) {
        this.studentCat = studentCat;
    }

    @JsonIgnoreProperties(allowGetters = true)
    @Column(name = "student_uat", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    public Date getStudentUat() {
        return studentUat;
    }

    public void setStudentUat(Date studentUat) {
        this.studentUat = studentUat;
    }

    @Column(name = "student_fee", nullable = true)
    public Integer getStudentFee() {
        return studentFee;
    }

    public void setStudentFee(Integer studentFee) {
        this.studentFee = studentFee;
    }

    @Column(name = "class_id", nullable = false)
    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

}
