package com.aguatrans.challenge_sprint2_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aguatrans.challenge_sprint2_backend.model.Idea;
import com.aguatrans.challenge_sprint2_backend.repository.IdeaRepository;

@RestController
@RequestMapping("/api/ideas")
public class IdeaController {

    @Autowired
    private IdeaRepository repository;

    @GetMapping
    public List<Idea> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Idea criar(@RequestBody Idea ideia) {
        ideia.setStatus("PENDENTE"); // Toda ideia nasce pendente para aprovação do gestor
        return repository.save(ideia);
    }
}