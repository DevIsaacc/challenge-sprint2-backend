package com.aguatrans.challenge_sprint2_backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "projetos")
public class Project {
    @Id
    private String id;
    private String nome;
    private String etapa;
    private String status;
    private Double investimento;
    private String prazo;
    private Double retornoFinanceiro;
    private Double lucroObtido;
    private Double aumentoProdutividade;
    private String estrategiaId;

    public Project() {}

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEtapa() { return etapa; }
    public void setEtapa(String etapa) { this.etapa = etapa; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Double getInvestimento() { return investimento; }
    public void setInvestimento(Double investimento) { this.investimento = investimento; }
    public String getPrazo() { return prazo; }
    public void setPrazo(String prazo) { this.prazo = prazo; }
    public Double getRetornoFinanceiro() { return retornoFinanceiro; }
    public void setRetornoFinanceiro(Double retornoFinanceiro) { this.retornoFinanceiro = retornoFinanceiro; }
    public Double getLucroObtido() { return lucroObtido; }
    public void setLucroObtido(Double lucroObtido) { this.lucroObtido = lucroObtido; }
    public Double getAumentoProdutividade() { return aumentoProdutividade; }
    public void setAumentoProdutividade(Double aumentoProdutividade) { this.aumentoProdutividade = aumentoProdutividade; }
    public String getEstrategiaId() { return estrategiaId; }
    public void setEstrategiaId(String estrategiaId) { this.estrategiaId = estrategiaId; }
}