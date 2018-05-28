package in.ogmatech.edutech.edutechservice.service;

import in.ogmatech.edutech.edutechservice.exception.AlreadyExistsException;
import in.ogmatech.edutech.edutechservice.model.Payment;
import in.ogmatech.edutech.edutechservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
@Transactional
public class PaymentServiceBean implements PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public boolean isExist(Payment payment) {
        return findById(payment.getIdPayment())!=null;
    }

    @Override
    public Payment save(Payment payment) {
        Payment existing = paymentRepository.findOne(payment.getIdPayment());

        if (existing != null) {
            throw new AlreadyExistsException(
                    String.format("There already exists a payment with id = %s", payment.getIdPayment()));
        }

        return paymentRepository.save(payment);
    }

    @Override
    public Payment findById(Long idPayment) {
        return paymentRepository.findOne(idPayment);
    }

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment update(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public void delete(Long idPayment) {
        paymentRepository.delete(idPayment);
    }

    @Override
    public void deleteAll() {
        paymentRepository.deleteAll();
    }
}
