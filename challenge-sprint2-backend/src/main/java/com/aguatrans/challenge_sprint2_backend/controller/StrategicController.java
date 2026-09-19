package com.aguatrans.challenge_sprint2_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aguatrans.challenge_sprint2_backend.model.StrategicOrientation;
import com.aguatrans.challenge_sprint2_backend.repository.StrategicOrientationRepository;

@RestController
@RequestMapping("/api/strategies")
public class StrategicController {

    @Autowired
    private StrategicOrientationRepository repository;

    @GetMapping
    public List<StrategicOrientation> listar() {
        return repository.findAll();
    }

    @PostMapping
    public StrategicOrientation criar(@RequestBody StrategicOrientation orientacao) {
        return repository.save(orientacao);
    }
}