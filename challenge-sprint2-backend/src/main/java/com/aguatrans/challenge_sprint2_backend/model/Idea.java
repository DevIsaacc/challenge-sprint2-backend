package com.aguatrans.challenge_sprint2_backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ideias")
public class Idea {
    @Id
    private String id;
    private String titulo;
    private String descricao;
    private String autorId; // ID do Operador
    private String status;  // PENDENTE, APROVADA, REJEITADA
    private String estrategiaId;
    private Double pontuacaoIa;

    public Idea() {}

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getAutorId() { return autorId; }
    public void setAutorId(String autorId) { this.autorId = autorId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getEstrategiaId() { return estrategiaId; }
    public void setEstrategiaId(String estrategiaId) { this.estrategiaId = estrategiaId; }
    public Double getPontuacaoIa() { return pontuacaoIa; }
    public void setPontuacaoIa(Double pontuacaoIa) { this.pontuacaoIa = pontuacaoIa; }
}