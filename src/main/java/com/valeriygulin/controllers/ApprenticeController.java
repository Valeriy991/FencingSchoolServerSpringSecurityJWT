package com.valeriygulin.controllers;

import com.valeriygulin.dto.ResponseResult;
import com.valeriygulin.model.Apprentice;
import com.valeriygulin.service.ApprenticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apprentice")
public class ApprenticeController {
    private ApprenticeService apprenticeService;

    @Autowired
    public void setApprenticeService(ApprenticeService apprenticeService) {
        this.apprenticeService = apprenticeService;
    }

    @PostMapping
    public ResponseEntity<ResponseResult<Apprentice>> add(@RequestBody Apprentice apprentice) {
        try {
            this.apprenticeService.addApprentice(apprentice);
            return new ResponseEntity<>(new ResponseResult<>(null, apprentice), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseResult<List<Apprentice>>> get() {
        List<Apprentice> list = this.apprenticeService.get();
        return new ResponseEntity<>(new ResponseResult<>(null, list), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Apprentice>> get(@PathVariable long id) {
        try {
            Apprentice apprentice = this.apprenticeService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(null, apprentice), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseResult<Apprentice>> update(@RequestBody Apprentice apprentice) {
        try {
            if (apprentice.getId() <= 0) {
                return new ResponseEntity<>(new ResponseResult<>("Incorrect format id", null),
                        HttpStatus.BAD_REQUEST);
            }
            Apprentice update = this.apprenticeService.update(apprentice);
            return new ResponseEntity<>(new ResponseResult<>(null, update), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Apprentice>> delete(@PathVariable long id) {
        try {
            Apprentice apprentice = this.apprenticeService.delete(id);
            return new ResponseEntity<>(new ResponseResult<>(null, apprentice), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}
