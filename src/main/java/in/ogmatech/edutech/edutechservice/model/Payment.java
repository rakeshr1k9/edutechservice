package in.ogmatech.edutech.edutechservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "payment")
@EntityListeners(AuditingEntityListener.class)
public class Payment implements Serializable {

    private Long idPayment;
    private Integer paymentAmount;
    private Date paymentDate;
    private Byte isDeleted;
    private Date paymentCat;
    private Date studentUat;
    private Long studentId;
    private Long instalmentId;

    @Id
    @GeneratedValue
    @Column(name = "id_payment", nullable = false)
    public Long getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(Long idPayment) {
        this.idPayment = idPayment;
    }

    @Column(name = "is_deleted", nullable = false)
    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Column(name = "payment_amount", nullable = true)
    public Integer getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Integer paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    @Column(name = "payment_date", nullable = true)
    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    @JsonIgnoreProperties(allowGetters = true)
    @Column(name = "payment_cat", nullable = true, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    public Date getPaymentCat() {
        return paymentCat;
    }

    public void setPaymentCat(Date paymentCat) {
        this.paymentCat = paymentCat;
    }

    @JsonIgnoreProperties(allowGetters = true)
    @Column(name = "payment_uat", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    public Date getStudentUat() {
        return studentUat;
    }

    public void setStudentUat(Date studentUat) {
        this.studentUat = studentUat;
    }

    @Column(name = "student_id", nullable = false)
    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    @Column(name = "instalment_id", nullable = false)
    public Long getInstalmentId() {
        return instalmentId;
    }

    public void setInstalmentId(Long instalmentId) {
        this.instalmentId = instalmentId;
    }
}
