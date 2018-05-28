package in.ogmatech.edutech.edutechservice.controller;

import in.ogmatech.edutech.edutechservice.exception.AlreadyExistsException;
import in.ogmatech.edutech.edutechservice.model.Payment;
import in.ogmatech.edutech.edutechservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    /* Create a payment */
    @PostMapping("payment")
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment, UriComponentsBuilder ucBuilder) {

        if (paymentService.isExist(payment)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        paymentService.save(payment);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("payment/{id}").buildAndExpand(payment.getIdPayment()).toUri());

        return new ResponseEntity<>(payment, headers, HttpStatus.CREATED);
    }

    /* Reading single payment */
    @GetMapping(value = "payment/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Payment> getPayment(@PathVariable("id") long idPayment) {

        Payment payment = paymentService.findById(idPayment);

        if (payment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    /*Reads all payment*/
    @GetMapping(value = "payment",produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<List<Payment>> listAllPayments() {

        List<Payment> payment = paymentService.findAll();

        if (payment.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    /*Update a payment*/
    @PutMapping("payment/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable("id") Long idPayment, @RequestBody Payment payment) {

        Payment currentPayment = paymentService.findById(idPayment);

        if (currentPayment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentPayment.setPaymentAmount(payment.getPaymentAmount());

        paymentService.update(currentPayment);

        return new ResponseEntity<>(currentPayment, HttpStatus.OK);
    }

    /*Delete a payment */
    @DeleteMapping("payment/{id}")
    public ResponseEntity<Payment> deletePayment(@PathVariable("id") long idPayment) {

        Payment payment = paymentService.findById(idPayment);

        if (payment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        paymentService.delete(idPayment);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*Delete all payment*/
    @DeleteMapping("payment")
    public ResponseEntity<Payment> deleteAllPayment() {

        paymentService.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handlePaymentAlreadyExistsException(AlreadyExistsException exception) {

        return exception.getMessage();
    }

}
