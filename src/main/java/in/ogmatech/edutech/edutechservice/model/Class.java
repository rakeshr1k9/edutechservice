package in.ogmatech.edutech.edutechservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "class")
@EntityListeners(AuditingEntityListener.class)
public class Class implements Serializable{

    private Long idClass;
    private String className;
    private Byte isDeleted;
    private Date classCat;
    private Date classUat;

    @Id
    @GeneratedValue
    @Column(name = "id_class", nullable = false)
    public Long getIdClass() {
        return idClass;
    }

    public void setIdClass(Long idClass) {
        this.idClass = idClass;
    }

    @Column(name = "class_name", nullable = true, length = 45)
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Column(name = "is_deleted", nullable = false)
    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    @JsonIgnoreProperties(allowGetters = true)
    @Column(name = "class_cat", nullable = true, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    public Date getClassCat() {
        return classCat;
    }

    public void setClassCat(Date classCat) {
        this.classCat = classCat;
    }

    @JsonIgnoreProperties(allowGetters = true)
    @Column(name = "class_uat", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    public Date getClassUat() {
        return classUat;
    }

    public void setClassUat(Date classUat) {
        this.classUat = classUat;
    }
}
