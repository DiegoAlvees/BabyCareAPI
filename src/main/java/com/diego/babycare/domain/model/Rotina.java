package com.diego.babycare.domain.model;

import com.diego.babycare.utils.MapToJsonConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Entity
public class Rotina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;

    private LocalDateTime timeStamp;

    @ManyToOne
    @JoinColumn(name = "baby_id")
    @JsonIgnore
    private Baby baby;

    @Convert(converter = MapToJsonConverter.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, Object> detalhes;

}
