package com.valeriygulin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "trainer")
public class Trainer extends Account {

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
