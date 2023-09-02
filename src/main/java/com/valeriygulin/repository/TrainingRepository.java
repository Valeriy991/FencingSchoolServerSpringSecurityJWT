package com.valeriygulin.repository;

import com.valeriygulin.model.Training;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
    Training getTrainingByApprentice_IdAndDate(long idApprentice, LocalDate date);

    List<Training> getTrainingsByTrainer_IdAndAndDate(long idTrainer, LocalDate date);

    List<Training> getTrainingsByNumberGymAndDate(long numGym, LocalDate date);

    List<Training> getTrainingsByTrainer_Id(Long idTrainer);
}
