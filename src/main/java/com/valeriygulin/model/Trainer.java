package com.valeriygulin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Table(name = "trainers")
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NonNull
    private String surname;
    @NonNull
    private String name;
    @NonNull
    private String patronymic;
    @NonNull
    private int experience;

    @JsonIgnore
    @ToString.Exclude
    @OneToOne(mappedBy = "trainer", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private TrainerSchedule trainerSchedule;


    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @JsonIgnore
    private List<Training> trainings;


}
