package in.ogmatech.edutech.edutechservice.service;

import in.ogmatech.edutech.edutechservice.exception.AlreadyExistsException;
import in.ogmatech.edutech.edutechservice.model.Class;
import in.ogmatech.edutech.edutechservice.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
@Transactional
public class ClassServiceBean implements ClassService {

    @Autowired
    ClassRepository classRepository;

    @Override
    public boolean isExist(Class classx) {
        return findById(classx.getIdClass())!=null;
    }

    @Override
    public Class save(Class classx) {
        Class existing = classRepository.findOne(classx.getIdClass());

        if (existing != null) {
            throw new AlreadyExistsException(
                    String.format("There already exists a class with id = %s", classx.getIdClass()));
        }

        return classRepository.save(classx);
    }

    @Override
    public Class findById(Long idClass) {
        return classRepository.findOne(idClass);
    }

    @Override
    public List<Class> findAll() {
        return classRepository.findAll();
    }

    @Override
    public Class update(Class classx) {
        return classRepository.save(classx);
    }

    @Override
    public void delete(Long idClass) {
        classRepository.delete(idClass);
    }

    @Override
    public void deleteAll() {
        classRepository.deleteAll();
    }
}
