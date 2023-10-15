package com.valeriygulin.repository;

import com.valeriygulin.model.Apprentice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprenticeRepository extends JpaRepository<Apprentice, Long> {
    Apprentice findByLoginAndPassword(String login, String password);
}
