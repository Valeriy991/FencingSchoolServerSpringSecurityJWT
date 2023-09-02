package com.valeriygulin.controllers;

import com.valeriygulin.dto.ResponseResult;
import com.valeriygulin.model.Training;
import com.valeriygulin.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/training")
public class TrainingController {

    private TrainingService trainingService;

    @Autowired
    public void setTrainingService(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @PostMapping("/{idApprentice}/{idTrainer}")
    public ResponseEntity<ResponseResult<Training>> add
            (@PathVariable long idApprentice, @PathVariable long idTrainer, @RequestParam long numGym,
             @RequestParam LocalDate date, @RequestParam LocalTime timeStart) {
        try {
            Training training = this.trainingService.addTraining(idApprentice, idTrainer, numGym, date, timeStart);
            return new ResponseEntity<>(new ResponseResult<>(null, training), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Training>> get(@PathVariable long id) {
        try {
            Training training = this.trainingService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(null, training), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getByApprentice")
    public ResponseEntity<ResponseResult<List<Training>>> getByApprentice(@RequestParam String id) {
        try {
            List<Training> training = this.trainingService.getByApprentice(Long.valueOf(id));
            return new ResponseEntity<>(new ResponseResult<>(null, training), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getByTrainer")
    public ResponseEntity<ResponseResult<List<Training>>> getByTrainer(@RequestParam String id) {
        try {
            List<Training> training = this.trainingService.getByTrainer(Long.valueOf(id));
            return new ResponseEntity<>(new ResponseResult<>(null, training), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Training>> delete(@PathVariable long id) {
        try {
            Training training = this.trainingService.delete(id);
            return new ResponseEntity<>(new ResponseResult<>(null, training), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

}
