package com.ipiecoles.java.eval2x0.service;

import com.ipiecoles.java.eval2x0.model.Cadre;
import com.ipiecoles.java.eval2x0.repository.CadreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadreService {

    private CadreRepository cadreRepository;

    public List<Cadre> findByCoefficientBetween (Double coef, Double coef2) {
        return cadreRepository.findByCoefficientBetween(coef,coef2);
    }
}
