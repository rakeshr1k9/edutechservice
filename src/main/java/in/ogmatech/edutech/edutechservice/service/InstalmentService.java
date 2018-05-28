package in.ogmatech.edutech.edutechservice.service;

import in.ogmatech.edutech.edutechservice.model.Instalment;

import java.util.List;

public interface InstalmentService {

    boolean isExist(Instalment instalment);

    Instalment save(Instalment instalment);

    Instalment findById(Long idInstalment);

    List<Instalment> findAll();

    Instalment update(Instalment instalment);

    void delete(Long idInstalment);

    void deleteAll();
}
