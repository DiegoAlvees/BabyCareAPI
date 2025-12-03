package com.diego.babycare.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Vacina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private LocalDate data;
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "baby_id")
    @JsonIgnore
    private Baby baby;
}
