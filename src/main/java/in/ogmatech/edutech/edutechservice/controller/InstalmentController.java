package in.ogmatech.edutech.edutechservice.controller;

import in.ogmatech.edutech.edutechservice.exception.AlreadyExistsException;
import in.ogmatech.edutech.edutechservice.model.Instalment;
import in.ogmatech.edutech.edutechservice.service.InstalmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class InstalmentController {

    @Autowired
    private InstalmentService instalmentService;

    /* Create a instalment */
    @PostMapping("instalment")
    public ResponseEntity<Instalment> createInstalment(@RequestBody Instalment instalment, UriComponentsBuilder ucBuilder) {

        if (instalmentService.isExist(instalment)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        instalmentService.save(instalment);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("instalment/{id}").buildAndExpand(instalment.getIdInstalment()).toUri());

        return new ResponseEntity<>(instalment, headers, HttpStatus.CREATED);
    }

    /* Reading single instalment */
    @GetMapping(value = "instalment/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Instalment> getInstalment(@PathVariable("id") long idInstalment) {

        Instalment instalment = instalmentService.findById(idInstalment);

        if (instalment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(instalment, HttpStatus.OK);
    }

    /*Reads all instalment*/
    @GetMapping(value = "instalment",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Instalment>> listAllInstalment() {

        List<Instalment> instalment = instalmentService.findAll();

        if (instalment.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(instalment, HttpStatus.OK);
    }

    /*Update a instalment*/
    @PutMapping("instalment/{id}")
    public ResponseEntity<Instalment> updateInstalment(@PathVariable("id") long idInstalment, @RequestBody Instalment instalment) {

        Instalment currentInstalment = instalmentService.findById(idInstalment);

        if (currentInstalment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentInstalment.setInstalmentNumber(instalment.getInstalmentNumber());

        instalmentService.update(currentInstalment);

        return new ResponseEntity<>(currentInstalment, HttpStatus.OK);
    }

    /*Delete a instalment */
    @DeleteMapping("instalment/{id}")
    public ResponseEntity<Instalment> deleteInstalment(@PathVariable("id") long idInstalment) {

        Instalment instalment = instalmentService.findById(idInstalment);

        if (instalment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        instalmentService.delete(idInstalment);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*Delete all instalment*/
    @DeleteMapping("instalment")
    public ResponseEntity<Instalment> deleteAllInstalment() {

        instalmentService.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleInstalmentAlreadyExistsException(AlreadyExistsException exception) {

        return exception.getMessage();
    }
}
