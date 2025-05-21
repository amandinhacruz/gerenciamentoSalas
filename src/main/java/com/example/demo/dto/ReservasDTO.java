package com.example.demo.dto;

import com.example.demo.model.Reservas;
import com.example.demo.model.Salas;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
public class ReservasDTO {
    private Long id;
    private Salas sala;
    private String nomeResponsavel;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;

    public ReservasDTO(Reservas reservas) {
        this.id = reservas.getId();
        this.sala = reservas.getSala();
        this.nomeResponsavel = reservas.getNomeResponsavel();
        this.dataHoraInicio = reservas.getDataHoraInicio();
        this.dataHoraFim = reservas.getDataHoraFim();
    }

    public static Reservas toEntity(ReservasDTO reservasDTO) {
        Reservas reservas = new Reservas();
        Salas sala = new Salas();
        sala.setId(reservasDTO.getSala().getId());

        reservas.setSala(sala);
        reservas.setNomeResponsavel(reservasDTO.getNomeResponsavel());
        reservas.setDataHoraInicio(reservasDTO.getDataHoraInicio());
        reservas.setDataHoraFim(reservasDTO.getDataHoraFim());
        return reservas;
    }
}
