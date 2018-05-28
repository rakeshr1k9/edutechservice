package in.ogmatech.edutech.edutechservice.controller;

import in.ogmatech.edutech.edutechservice.exception.AlreadyExistsException;
import in.ogmatech.edutech.edutechservice.model.Class;
import in.ogmatech.edutech.edutechservice.service.ClassService;
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
public class ClassController {

    @Autowired
    private ClassService classService;

    /* Create a class */
    @PostMapping("class")
    public ResponseEntity<Class> createClass(@RequestBody Class classx, UriComponentsBuilder ucBuilder) {

        if (classService.isExist(classx)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        classService.save(classx);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("class/{id}").buildAndExpand(classx.getIdClass()).toUri());

        return new ResponseEntity<>(classx, headers, HttpStatus.CREATED);
    }

    /* Reading single class */
    @GetMapping(value = "class/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Class> getClass(@PathVariable("id") long idClass) {

        Class classx = classService.findById(idClass);

        if (classx == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(classx, HttpStatus.OK);
    }

    /*Reads all classes*/
    @GetMapping(value = "class",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Class>> listAllClass() {

        List<Class> classx = classService.findAll();

        if (classx.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(classx, HttpStatus.OK);
    }

    /*Update a class*/
    @PutMapping("class/{id}")
    public ResponseEntity<Class> updateClass(@PathVariable("id") long idClass, @RequestBody Class classx) {

        Class currentClass = classService.findById(idClass);

        if (currentClass == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentClass.setClassName(classx.getClassName());
        currentClass.setIsDeleted(classx.getIsDeleted());

        classService.update(currentClass);

        return new ResponseEntity<>(currentClass, HttpStatus.OK);
    }

    /*Delete a class */
    @DeleteMapping("class/{id}")
    public ResponseEntity<Class> deleteClass(@PathVariable("id") long idClass) {

        Class classx = classService.findById(idClass);

        if (classx == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        classService.delete(idClass);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /*Delete all classes*/
    @DeleteMapping("class")
    public ResponseEntity<Class> deleteAllClasss() {

        classService.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleClassAlreadyExistsException(AlreadyExistsException exception) {

        return exception.getMessage();
    }
}
