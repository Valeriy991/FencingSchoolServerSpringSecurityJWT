package com.valeriygulin.service;

import com.valeriygulin.model.Apprentice;

import java.util.List;

public interface ApprenticeService {
    void addApprentice(Apprentice apprentice);

    List<Apprentice> get();

    Apprentice get(Long id);

    Apprentice update(Apprentice apprentice);

    Apprentice delete(Long id);
}
