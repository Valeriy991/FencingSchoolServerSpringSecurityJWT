package com.valeriygulin.service;

import com.valeriygulin.model.Apprentice;
import com.valeriygulin.model.Trainer;

import java.util.List;

public interface TrainerService {
    void addTrainer(Trainer trainer);

    List<Trainer> get();

    Trainer get(Long id);

    Trainer update(Trainer trainer);

    Trainer delete(Long id);
}
