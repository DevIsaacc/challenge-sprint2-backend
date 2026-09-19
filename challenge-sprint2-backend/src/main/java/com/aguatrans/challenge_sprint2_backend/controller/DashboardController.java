package com.aguatrans.challenge_sprint2_backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aguatrans.challenge_sprint2_backend.model.Project;
import com.aguatrans.challenge_sprint2_backend.repository.ProjectRepository;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/summary")
    public Map<String, Object> obterResumoDashboard() {
        List<Project> projetos = projectRepository.findAll();

        double totalInvestido = projetos.stream()
                .mapToDouble(p -> p.getInvestimento() != null ? p.getInvestimento() : 0.0)
                .sum();
        
        double totalRetorno = projetos.stream()
                .mapToDouble(p -> p.getRetornoFinanceiro() != null ? p.getRetornoFinanceiro() : 0.0)
                .sum();
                
        double totalLucro = projetos.stream()
                .mapToDouble(p -> p.getLucroObtido() != null ? p.getLucroObtido() : 0.0)
                .sum();
        
        double roiGeral = totalInvestido > 0 ? ((totalRetorno - totalInvestido) / totalInvestido) * 100 : 0.0;

        Map<String, Object> resumo = new HashMap<>();
        resumo.put("totalProjetos", projetos.size());
        resumo.put("investimentoTotal", totalInvestido);
        resumo.put("retornoTotal", totalRetorno);
        resumo.put("lucroObtidoTotal", totalLucro);
        resumo.put("roiGeralPercentual", roiGeral);
        resumo.put("projetos", projetos);

        return resumo;
    }
}