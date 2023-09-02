package com.valeriygulin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "trainings")
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long numberGym;

    private LocalDate date;

    private LocalTime timeStart;

    @ManyToOne
    @JoinColumn(name = "apprentice_id", nullable = false)
    private Apprentice apprentice;

    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false)
    private Trainer trainer;

    public Training(Apprentice apprentice, Trainer trainer,long numberGym, LocalDate date, LocalTime timeStart) {
        this.apprentice = apprentice;
        this.trainer = trainer;
        this.numberGym = numberGym;
        this.date = date;
        this.timeStart = timeStart;
    }
}
