package com.example.demo.service;

import com.example.demo.dto.ReservasDTO;
import com.example.demo.dto.SalasDTO;
import com.example.demo.model.Reservas;
import com.example.demo.model.Salas;
import com.example.demo.repository.ReservasRepository;
import com.example.demo.repository.SalasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservasService {

    private final ReservasRepository reservasRepository;
    private final SalasRepository salasRepository;

    public ResponseEntity<List<ReservasDTO>> listarReservas (){
        List<Reservas> lista = reservasRepository.findAll();
        List<ReservasDTO> listaDTO = lista.stream().map(ReservasDTO::new).toList();
        return ResponseEntity.ok(listaDTO);
    }

    public ResponseEntity<List<ReservasDTO>> procurarReservaPorSala(@PathVariable Long id) {
        List<Reservas> reservas = reservasRepository.findBySalaId(id);
        List<ReservasDTO> reservasDTO = reservas.stream().map(ReservasDTO::new).toList();
        return ResponseEntity.ok(reservasDTO);
    }

    public ResponseEntity<ReservasDTO> criarReservar (@RequestBody ReservasDTO reservasDTO) {
        Reservas reserva = ReservasDTO.toEntity(reservasDTO);

        // Regra 1: início < fim
        if (reservasDTO.getDataHoraInicio().isAfter(reservasDTO.getDataHoraFim()) || reservasDTO.getDataHoraInicio().isEqual(reservasDTO.getDataHoraFim())) {
            return ResponseEntity.notFound().build();
        }

        // Regra 2: não reservar no passado
        if (reservasDTO.getDataHoraInicio().isBefore(LocalDateTime.now())) {
            return ResponseEntity.notFound().build();
        }

        // Regra 3: verificar sobreposição
        boolean existeConflito = reservasRepository.existsBySalaIdAndDataHoraInicioLessThanAndDataHoraFimGreaterThan(
                reservasDTO.getSala().getId(),
                reservasDTO.getDataHoraFim(),
                reservasDTO.getDataHoraInicio()
        );
        if (existeConflito) {
            return ResponseEntity.notFound().build();
        }

        Salas salaExistente = salasRepository.findById(reserva.getSala().getId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        reserva.setSala(salaExistente);
        Reservas salvarReserva = reservasRepository.save(reserva);
        return ResponseEntity.ok(new ReservasDTO(salvarReserva));
    }

    public ResponseEntity<ReservasDTO> editarReserva(@PathVariable Long id, @RequestBody ReservasDTO reservasDTO){
        Optional<Reservas> reservaExistente = reservasRepository.findById(id);
        if (reservaExistente.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Reservas editarReserva = ReservasDTO.toEntity(reservasDTO);

        Salas salaExistente = salasRepository.findById(editarReserva.getSala().getId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        editarReserva.setSala(salaExistente);
        Reservas salvarReserva = reservasRepository.save(editarReserva);
        return ResponseEntity.ok(new ReservasDTO(salvarReserva));
    }

    public ResponseEntity<Void> cancelarReserva (@PathVariable Long id) {
        Optional<Reservas> reservaExistente = reservasRepository.findById(id);
        if (reservaExistente.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        reservasRepository.deleteById(id);
        return ResponseEntity.notFound().build();
    }



}
