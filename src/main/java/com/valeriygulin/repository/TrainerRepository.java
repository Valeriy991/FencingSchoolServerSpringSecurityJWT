package com.valeriygulin.repository;

import com.valeriygulin.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    Trainer findByLoginAndPassword(String login, String password);
}
