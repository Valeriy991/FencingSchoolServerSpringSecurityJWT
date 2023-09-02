package com.valeriygulin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "trainer_schedule")
public class TrainerSchedule {
    @Id
    @Column(name = "trainer_id")
    private long idTrainer;

    @ToString.Exclude
    @OneToOne
    @NonNull
    @MapsId
    @JsonIgnore
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    private LocalTime mondayStart;

    private LocalTime mondayEnd;

    private LocalTime tuesdayEnd;

    private LocalTime tuesdayStart;

    private LocalTime wednesdayStart;

    private LocalTime wednesdayEnd;

    private LocalTime thursdayStart;

    private LocalTime thursdayEnd;

    private LocalTime fridayStart;

    private LocalTime fridayEnd;

    private LocalTime saturdayStart;

    private LocalTime saturdayEnd;

    private LocalTime sundayStart;

    private LocalTime sundayEnd;


    public void add(String dayWeek, LocalTime start, LocalTime end) {
        try {
            Field declaredField = this.getClass().getDeclaredField(dayWeek + "Start");
            Field declaredField2 = this.getClass().getDeclaredField(dayWeek + "End");
            declaredField.set(this, start);
            declaredField2.set(this, end);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TrainerSchedule(@NonNull Trainer trainer) {
        this.trainer = trainer;
    }

    public LocalTime[] get(String dayWeek) {
        try {
            String dayWeekLowerCase = dayWeek.toLowerCase();
            Field declaredField = this.getClass().getDeclaredField(dayWeekLowerCase + "Start");
            Field declaredField2 = this.getClass().getDeclaredField(dayWeekLowerCase + "End");
            return new LocalTime[]{(LocalTime) declaredField.get(this), (LocalTime) declaredField2.get(this)};
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
