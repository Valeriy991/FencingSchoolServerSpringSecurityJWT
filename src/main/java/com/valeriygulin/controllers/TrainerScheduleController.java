package com.valeriygulin.controllers;

import com.valeriygulin.dto.ResponseResult;
import com.valeriygulin.model.TrainerSchedule;
import com.valeriygulin.service.TrainerScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/trainer_schedule")
public class TrainerScheduleController {

    private TrainerScheduleService trainerScheduleService;

    @Autowired
    public void setTrainerScheduleService(TrainerScheduleService trainerScheduleService) {
        this.trainerScheduleService = trainerScheduleService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<ResponseResult<TrainerSchedule>> add(@PathVariable long id, @RequestParam String dayWeek,
                                                               @RequestParam LocalTime start, @RequestParam LocalTime end) {
        try {
            TrainerSchedule trainerSchedule = this.trainerScheduleService.addTrainerSchedule(id, dayWeek, start, end);
            return new ResponseEntity<>(new ResponseResult<>(null, trainerSchedule), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseResult<List<TrainerSchedule>>> get() {
        try {
            List<TrainerSchedule> trainerSchedules = this.trainerScheduleService.get();
            return new ResponseEntity<>(new ResponseResult<>(null, trainerSchedules), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<TrainerSchedule>> get(@PathVariable long id) {
        try {
            TrainerSchedule trainerSchedule = this.trainerScheduleService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(null, trainerSchedule), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "localTime/{id}")
    public ResponseEntity<ResponseResult<LocalTime[]>> get(@PathVariable long id, @RequestParam LocalDate date) {
        try {
            LocalTime[] localTimes = this.trainerScheduleService.get(id, date);
            return new ResponseEntity<>(new ResponseResult<>(null, localTimes), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseResult<TrainerSchedule>> delete(@PathVariable long id, @RequestParam String dayWeek) {
        try {
            TrainerSchedule trainerSchedule = this.trainerScheduleService.delete(id, dayWeek);
            return new ResponseEntity<>(new ResponseResult<>(null, trainerSchedule), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

}
