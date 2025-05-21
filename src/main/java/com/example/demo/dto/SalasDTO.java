package com.example.demo.dto;

import com.example.demo.model.Salas;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class SalasDTO {
    private Long id;
    private String nome;
    private int capacidadeMax;
    private String localizacao;

    public SalasDTO(Salas salas) {
        this.id = salas.getId();
        this.nome = salas.getNome();
        this.capacidadeMax = salas.getCapacidadeMax();
        this.localizacao = salas.getLocalizacao();
    }

    public static Salas toEntity(SalasDTO salasDTO) {
        Salas salas = new Salas();
        salas.setNome(salasDTO.getNome());
        salas.setCapacidadeMax(salasDTO.getCapacidadeMax());
        salas.setLocalizacao(salasDTO.getLocalizacao());
        return salas;
    }

}
