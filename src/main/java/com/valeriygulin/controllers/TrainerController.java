package com.valeriygulin.controllers;

import com.valeriygulin.dto.ResponseResult;
import com.valeriygulin.model.Trainer;
import com.valeriygulin.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/trainer")
public class TrainerController {
    private TrainerService trainerService;

    @Autowired
    public void setTrainerService(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping
    public ResponseEntity<ResponseResult<Trainer>> add(@RequestBody Trainer trainer) {
        try {
            this.trainerService.addTrainer(trainer);
            return new ResponseEntity<>(new ResponseResult<>(null, trainer), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseResult<List<Trainer>>> get() {
        List<Trainer>list = this.trainerService.get();
        return new ResponseEntity<>(new ResponseResult<>(null, list), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Trainer>> get(@PathVariable long id) {
        try {
            Trainer trainer = this.trainerService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(null, trainer), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseResult<Trainer>> update(@RequestBody Trainer trainer) {
        try {
            if (trainer.getId() <= 0) {
                return new ResponseEntity<>(new ResponseResult<>("Incorrect format id", null),
                        HttpStatus.BAD_REQUEST);
            }
            Trainer update = this.trainerService.update(trainer);
            return new ResponseEntity<>(new ResponseResult<>(null, update), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Trainer>> delete(@PathVariable long id) {
        try {
            Trainer trainer = this.trainerService.delete(id);
            return new ResponseEntity<>(new ResponseResult<>(null, trainer), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}
