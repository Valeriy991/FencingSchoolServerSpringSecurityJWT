package com.valeriygulin.service;

import com.valeriygulin.model.Apprentice;
import com.valeriygulin.repository.ApprenticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ApprenticeServiceImpl implements ApprenticeService {

    private ApprenticeRepository apprenticeRepository;

    @Autowired
    public void setApprenticeRepository(ApprenticeRepository apprenticeRepository) {
        this.apprenticeRepository = apprenticeRepository;
    }

    @Override
    public void addApprentice(Apprentice apprentice) {
        try {
            this.apprenticeRepository.save(apprentice);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Apprentice is already exist!");
        }
    }

    @Override
    public List<Apprentice> get() {
        return this.apprenticeRepository.findAll();
    }

    @Override
    public Apprentice get(Long id) {
        return this.apprenticeRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Apprentice doesn't exist!"));
    }

    @Override
    public Apprentice update(Apprentice apprentice) {
        Apprentice fromBase = this.get(apprentice.getId());
        fromBase.setUserName(apprentice.getUserName());
        fromBase.setFirstName(apprentice.getFirstName());
        fromBase.setLastName(apprentice.getLastName());
        fromBase.setPhoneNumber(apprentice.getPhoneNumber());
        try {
            this.apprenticeRepository.save(fromBase);
            return fromBase;
        } catch (Exception e) {
            throw new IllegalArgumentException("Apprentice is already exists!");
        }
    }

    @Override
    public Apprentice delete(Long id) {
        Apprentice apprentice = this.get(id);
        this.apprenticeRepository.deleteById(id);
        return apprentice;
    }
}
