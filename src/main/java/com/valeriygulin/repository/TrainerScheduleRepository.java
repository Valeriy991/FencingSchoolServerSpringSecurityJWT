package com.valeriygulin.repository;

import com.valeriygulin.model.TrainerSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerScheduleRepository extends JpaRepository<TrainerSchedule, Long> {

    TrainerSchedule getTrainerScheduleByTrainer_Id(long id);
}
