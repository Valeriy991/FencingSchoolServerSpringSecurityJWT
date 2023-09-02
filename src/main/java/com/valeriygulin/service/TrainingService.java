package com.valeriygulin.service;

import com.valeriygulin.model.Apprentice;
import com.valeriygulin.model.Training;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TrainingService {
    Training addTraining(long idApprentice, long idTrainer, long numGym, LocalDate date, LocalTime timeStart);

    Training get(Long id);

    List<Training> getByApprentice(Long idApprentice);

    List<Training> getByTrainer(Long idTrainer);

    Training delete(Long id);

}
