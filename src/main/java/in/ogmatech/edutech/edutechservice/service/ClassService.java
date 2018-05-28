package in.ogmatech.edutech.edutechservice.service;

import in.ogmatech.edutech.edutechservice.model.Class;

import java.util.List;

public interface ClassService {

    boolean isExist(Class classx);

    Class save(Class classx);

    Class findById(Long idClass);

    List<Class> findAll();

    Class update(Class classx);

    void delete(Long idClass);

    void deleteAll();
}
