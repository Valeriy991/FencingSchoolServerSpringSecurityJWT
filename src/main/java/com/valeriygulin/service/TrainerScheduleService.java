package com.valeriygulin.service;

import com.valeriygulin.model.Apprentice;
import com.valeriygulin.model.Trainer;
import com.valeriygulin.model.TrainerSchedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TrainerScheduleService {
    TrainerSchedule addTrainerSchedule(Long id, String dayWeek, LocalTime start, LocalTime end);

    List<TrainerSchedule> get();

    TrainerSchedule get(Long id);

    LocalTime[] get(long id, LocalDate date);

    TrainerSchedule delete(Long idTrainer, String dayWeek);

    TrainerSchedule delete(Long idTrainer);
}
