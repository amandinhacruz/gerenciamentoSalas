package com.example.demo.controller;

import com.example.demo.dto.ReservasDTO;
import com.example.demo.service.ReservasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
@RequiredArgsConstructor
public class ReservasController {

    private final ReservasService reservasService;

    @GetMapping
    public ResponseEntity<List<ReservasDTO>> reservasRealizadas () {
        return reservasService.listarReservas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ReservasDTO>> reservasPorSala (@PathVariable Long id) {
        return reservasService.procurarReservaPorSala(id);
    }

    @PostMapping
    public ResponseEntity<ReservasDTO> realizarReserva (@RequestBody ReservasDTO reservasDTO) {
        return reservasService.criarReservar(reservasDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservasDTO> editarReserva(@PathVariable Long id, @RequestBody ReservasDTO reservasDTO) {
        return reservasService.editarReserva(id,reservasDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarReserva(@PathVariable Long id) {
        return reservasService.cancelarReserva(id);
    }

}
