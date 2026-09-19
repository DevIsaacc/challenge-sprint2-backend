package com.aguatrans.challenge_sprint2_backend.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orientacoes_estrategicas")
public class StrategicOrientation {
    @Id
    private String id;
    private LocalDate data;
    private String categoria;
    private String campanha;
    private String descricao;

    public StrategicOrientation() {}

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getCampanha() { return campanha; }
    public void setCampanha(String campanha) { this.campanha = campanha; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}