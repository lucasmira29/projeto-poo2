package com.Mangoroll.mangoroll.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Data;

@Entity
@Table(name = "animes")
@Data
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(name = "descricao", columnDefinition = "LONGTEXT")
    private String descricao;

    private String genero;
    private int episodios;
    private double nota;
    private String imagemUrl;
}
