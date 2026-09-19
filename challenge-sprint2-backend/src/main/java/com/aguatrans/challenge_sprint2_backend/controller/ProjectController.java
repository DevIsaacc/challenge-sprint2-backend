package com.aguatrans.challenge_sprint2_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aguatrans.challenge_sprint2_backend.model.Project;
import com.aguatrans.challenge_sprint2_backend.repository.ProjectRepository;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectRepository repository;

    @GetMapping
    public List<Project> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Project criar(@RequestBody Project projeto) {
        return repository.save(projeto);
    }

    @PutMapping("/{id}")
    public Project atualizar(@PathVariable String id, @RequestBody Project projetoAtualizado) {
        return repository.findById(id).map(projeto -> {
            projeto.setNome(projetoAtualizado.getNome());
            projeto.setEtapa(projetoAtualizado.getEtapa());
            projeto.setStatus(projetoAtualizado.getStatus());
            projeto.setInvestimento(projetoAtualizado.getInvestimento());
            projeto.setPrazo(projetoAtualizado.getPrazo());
            projeto.setRetornoFinanceiro(projetoAtualizado.getRetornoFinanceiro());
            projeto.setLucroObtido(projetoAtualizado.getLucroObtido());
            projeto.setAumentoProdutividade(projetoAtualizado.getAumentoProdutividade());
            projeto.setEstrategiaId(projetoAtualizado.getEstrategiaId());
            return repository.save(projeto);
        }).orElseThrow(() -> new RuntimeException("Projeto não encontrado com o ID: " + id));
    }
}