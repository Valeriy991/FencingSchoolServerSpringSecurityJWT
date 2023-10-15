package com.valeriygulin.service;

import com.valeriygulin.model.Trainer;
import com.valeriygulin.model.TrainerSchedule;
import com.valeriygulin.model.Training;
import com.valeriygulin.repository.TrainerScheduleRepository;
import com.valeriygulin.repository.TrainingRepository;
import com.valeriygulin.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TrainerScheduleServiceImpl implements TrainerScheduleService {

    private TrainerScheduleRepository trainerScheduleRepository;
    private TrainerService trainerService;

    private TrainingRepository trainingRepository;

    private TrainingServiceImpl trainingService;

    @Autowired
    public void setTrainingService(TrainingServiceImpl trainingService) {
        this.trainingService = trainingService;
    }

    @Autowired
    public void setTrainerScheduleRepository(TrainerScheduleRepository trainerScheduleRepository) {
        this.trainerScheduleRepository = trainerScheduleRepository;
    }

    @Autowired
    public void setTrainerService(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @Autowired
    public void setTrainingRepository(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Override
    public TrainerSchedule addTrainerSchedule(Long id, String dayWeek, LocalTime start, LocalTime end) {
        try {
            Trainer trainer = this.trainerService.get(id);
            TrainerSchedule fromBase = this.trainerScheduleRepository.findById(id).orElse(null);
            if (fromBase == null) {
                fromBase = new TrainerSchedule(trainer);
            }
            fromBase.add(dayWeek, start, end);
            this.trainerScheduleRepository.save(fromBase);
            return fromBase;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("TrainerSchedule is already exists!");

        }
    }

    @Override
    public List<TrainerSchedule> get() {
        return this.trainerScheduleRepository.findAll();
    }

    @Override
    public TrainerSchedule get(Long id) {
        return this.trainerScheduleRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Trainer schedule doesn't exist!"));
    }

    @Override
    public LocalTime[] get(long id, LocalDate date) {
        String dayWeek = date.getDayOfWeek().toString();
        TrainerSchedule trainerScheduleByTrainerId = this.trainerScheduleRepository.getTrainerScheduleByTrainer_Id(id);
        LocalTime[] localTimes = trainerScheduleByTrainerId.get(dayWeek);
        ArrayList<LocalTime> arrayList = new ArrayList<>();
        List<Training> trainingsByTrainer = this.trainingRepository.getTrainingsByTrainer_IdAndAndDate(id, date);
        for (LocalTime i = localTimes[0]; i.isBefore(localTimes[1]); i = i.plusMinutes(90)) {
            int quantity = this.trainingService.overLap(trainingsByTrainer, i);
            if (quantity < 3) {
                arrayList.add(i);
            }
        }
        int size = arrayList.size();
        LocalTime[] res = new LocalTime[size];
        for (int i = 0; i < res.length; i++) {
            res[i] = arrayList.get(i);
        }
        return res;
    }


    @Override
    public TrainerSchedule delete(Long idTrainer, String dayWeek) {
        TrainerSchedule fromBase = this.get(idTrainer);
        fromBase.add(dayWeek, null, null);
        try {
            this.trainerScheduleRepository.save(fromBase);
            return fromBase;
        } catch (Exception e) {
            throw new IllegalArgumentException("Error delete!");
        }
    }


    @Override
    public TrainerSchedule delete(Long idTrainer) {
        TrainerSchedule trainerSchedule = this.get(idTrainer);
        this.trainerScheduleRepository.deleteById(idTrainer);
        return trainerSchedule;
    }
}
