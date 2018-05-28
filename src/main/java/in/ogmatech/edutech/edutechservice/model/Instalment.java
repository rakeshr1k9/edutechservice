package in.ogmatech.edutech.edutechservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "instalment")
@EntityListeners(AuditingEntityListener.class)
public class Instalment implements Serializable {

    private Long idInstalment;
    private Integer instalmentNumber;
    private Byte isDeleted;
    private Date instalmentCat;
    private Date instalmentUat;

    @Id
    @GeneratedValue
    @Column(name = "id_instalment", nullable = false)
    public Long getIdInstalment() {
        return idInstalment;
    }

    public void setIdInstalment(Long idInstalment) {
        this.idInstalment = idInstalment;
    }

    @Column(name = "instalment_number", nullable = true)
    public Integer getInstalmentNumber() {
        return instalmentNumber;
    }

    public void setInstalmentNumber(Integer instalmentNumber) {
        this.instalmentNumber = instalmentNumber;
    }

    @Column(name = "is_deleted", nullable = false)
    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    @JsonIgnoreProperties(allowGetters = true)
    @Column(name = "instalment_cat", nullable = true, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    public Date getInstalmentCat() {
        return instalmentCat;
    }

    public void setInstalmentCat(Date instalmentCat) {
        this.instalmentCat = instalmentCat;
    }

    @JsonIgnoreProperties(allowGetters = true)
    @Column(name = "instalment_uat", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    public Date getInstalmentUat() {
        return instalmentUat;
    }

    public void setInstalmentUat(Date instalmentUat) {
        this.instalmentUat = instalmentUat;
    }
}
