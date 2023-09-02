package com.valeriygulin.service;

import com.valeriygulin.model.Apprentice;
import com.valeriygulin.model.Trainer;
import com.valeriygulin.model.TrainerSchedule;
import com.valeriygulin.model.Training;
import com.valeriygulin.repository.TrainerScheduleRepository;
import com.valeriygulin.repository.TrainingRepository;
import com.valeriygulin.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {

    private TrainingRepository trainingRepository;
    private TrainerScheduleRepository trainerScheduleRepository;
    private TrainerService trainerService;
    private ApprenticeService apprenticeService;

    @Autowired
    public void setTrainingRepository(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Autowired
    public void setTrainerService(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @Autowired
    public void setApprenticeService(ApprenticeService apprenticeService) {
        this.apprenticeService = apprenticeService;
    }

    @Autowired
    public void setTrainerScheduleRepository(TrainerScheduleRepository trainerScheduleRepository) {
        this.trainerScheduleRepository = trainerScheduleRepository;
    }

    @Override
    public Training addTraining(long idApprentice, long idTrainer, long numGym, LocalDate date, LocalTime timeStart) {
        List<Training> trainingsByTrainer = this.trainingRepository.getTrainingsByTrainer_IdAndAndDate(idTrainer, date);
        Trainer trainer = this.trainerService.get(idTrainer);
        int quantityTraining1 = this.overLap(trainingsByTrainer, timeStart);
        if (quantityTraining1 >= 3) {
            throw new IllegalArgumentException("Trainer can't have more than 3 apprentices!");
        }
        List<Training> trainingsByNumberGymAndDate = this.trainingRepository.getTrainingsByNumberGymAndDate(numGym, date);
        int quantityTraining2 = this.overLap(trainingsByNumberGymAndDate, timeStart);
        if (quantityTraining2 >= 10) {
            throw new IllegalArgumentException("Apprentice more than 10 in the gym!");
        }
        Training trainingByApprentice = this.trainingRepository.getTrainingByApprentice_IdAndDate(idApprentice, date);
        if (trainingByApprentice != null) {
            throw new IllegalArgumentException("Apprentice can have only 1 training!");
        }
        DayOfWeek dayWeek = date.getDayOfWeek();
        String dayWeekString = dayWeek.toString();
        TrainerSchedule trainerScheduleByTrainerId = this.trainerScheduleRepository.getTrainerScheduleByTrainer_Id(idTrainer);
        LocalTime[] localTimes = trainerScheduleByTrainerId.get(dayWeekString);
        LocalTime start = localTimes[0];
        LocalTime end = localTimes[1];
        LocalTime timeEnd = timeStart.plusMinutes(90);
        if (start == null && end == null || !Util.isOverlapping(timeStart, timeEnd, start, end)) {
            throw new IllegalArgumentException("Trainer doesn't work in this DAY!");
        }
        Apprentice apprentice = this.apprenticeService.get(idApprentice);
        Training training = new Training(apprentice, trainer, numGym, date, timeStart);
        try {
            this.trainingRepository.save(training);
            return training;
        } catch (Exception e) {
            throw new IllegalArgumentException("Training is already exist!");
        }
    }

    public int overLap(List<Training> trainings, LocalTime timeStart) {
        int count = 0;
        for (Training training : trainings) {
            LocalTime stop = timeStart.plusMinutes(90);
            LocalTime start1 = training.getTimeStart();
            LocalTime stop1 = training.getTimeStart().plusMinutes(90);
            if (Util.isOverlapping(timeStart, stop, start1, stop1)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public Training get(Long id) {
        return this.trainingRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Training doesn't exist!"));
    }

    @Override
    public List<Training> getByApprentice(Long idApprentice) {
        List<Training> res = new ArrayList<>();
        List<Training> all = this.trainingRepository.findAll();
        for (Training training : all) {
            if (training.getApprentice().getId() == idApprentice) {
                res.add(training);
            }
        }
        return res;
    }

    @Override
    public List<Training> getByTrainer(Long idTrainer) {
        return this.trainingRepository.getTrainingsByTrainer_Id(idTrainer);
    }


    @Override
    public Training delete(Long id) {
        Training training = this.get(id);
        this.trainingRepository.deleteById(id);
        return training;
    }
}
