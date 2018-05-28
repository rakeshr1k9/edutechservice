package in.ogmatech.edutech.edutechservice.service;

import in.ogmatech.edutech.edutechservice.model.Payment;

import java.util.List;

public interface PaymentService {

    boolean isExist(Payment payment);

    Payment save(Payment payment);

    Payment findById(Long idPayment);

    List<Payment> findAll();

    Payment update(Payment payment);

    void delete(Long idPayment);

    void deleteAll();
}
