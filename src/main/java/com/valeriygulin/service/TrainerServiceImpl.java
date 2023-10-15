package com.valeriygulin.service;

import com.valeriygulin.model.Trainer;
import com.valeriygulin.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TrainerServiceImpl implements TrainerService {
    private TrainerRepository trainerRepository;

    @Autowired
    public void setTrainerRepository(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }


    @Override
    public void addTrainer(Trainer trainer) {
        try {
            this.trainerRepository.save(trainer);
        } catch (Exception e) {
            throw new IllegalArgumentException("Trainer is already exist!");
        }
    }

    @Override
    public List<Trainer> get() {
        return this.trainerRepository.findAll();
    }

    @Override
    public Trainer get(Long id) {
        return this.trainerRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Trainer doesn't exist!"));
    }

    @Override
    public Trainer update(Trainer trainer) {
        Trainer fromBase = this.get(trainer.getId());
        fromBase.setFirstName(trainer.getFirstName());
        fromBase.setUserName(trainer.getUserName());
        fromBase.setLastName(trainer.getLastName());
        fromBase.setExperience(trainer.getExperience());
        try {
            this.trainerRepository.save(fromBase);
            return fromBase;
        } catch (Exception e) {
            throw new IllegalArgumentException("Trainer is already exists!");
        }
    }

    @Override
    public Trainer delete(Long id) {
        Trainer trainer = this.get(id);
        this.trainerRepository.deleteById(id);
        return trainer;
    }
}
