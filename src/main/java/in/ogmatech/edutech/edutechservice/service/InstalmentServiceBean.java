package in.ogmatech.edutech.edutechservice.service;

import in.ogmatech.edutech.edutechservice.exception.AlreadyExistsException;
import in.ogmatech.edutech.edutechservice.model.Instalment;
import in.ogmatech.edutech.edutechservice.repository.InstalmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
@Transactional
public class InstalmentServiceBean implements InstalmentService {
    @Autowired
    InstalmentRepository instalmentRepository;

    @Override
    public boolean isExist(Instalment instalment) {
        return findById(instalment.getIdInstalment())!=null;
    }

    @Override
    public Instalment save(Instalment instalment) {
        Instalment existing = instalmentRepository.findOne(instalment.getIdInstalment());

        if (existing != null) {
            throw new AlreadyExistsException(
                    String.format("There already exists a instalment with id = %s", instalment.getIdInstalment()));
        }

        return instalmentRepository.save(instalment);
    }

    @Override
    public Instalment findById(Long idInstalment) {
        return instalmentRepository.findOne(idInstalment);
    }

    @Override
    public List<Instalment> findAll() {
        return instalmentRepository.findAll();
    }

    @Override
    public Instalment update(Instalment instalment) {
        return instalmentRepository.save(instalment);
    }

    @Override
    public void delete(Long idInstalment) {
        instalmentRepository.delete(idInstalment);
    }

    @Override
    public void deleteAll() {
        instalmentRepository.deleteAll();
    }
}
