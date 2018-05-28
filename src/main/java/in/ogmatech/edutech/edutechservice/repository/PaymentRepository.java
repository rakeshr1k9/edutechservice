package in.ogmatech.edutech.edutechservice.repository;

import in.ogmatech.edutech.edutechservice.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
