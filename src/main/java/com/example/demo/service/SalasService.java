package com.example.demo.service;

import com.example.demo.dto.SalasDTO;
import com.example.demo.model.Salas;
import com.example.demo.repository.SalasRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SalasService {

    private final SalasRepository salasRepository;

    public ResponseEntity<List<SalasDTO>> listarSalas() {
        List<Salas> salas = salasRepository.findAll();
        List<SalasDTO> salasDTOS = salas.stream().map(SalasDTO::new).toList();
        return ResponseEntity.ok(salasDTOS);
    }

    public ResponseEntity<SalasDTO> buscarSala(@PathVariable Long id) {
        Optional<Salas> salaExistente = salasRepository.findById(id);
        if (salaExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Salas salaId = salaExistente.get();
        return ResponseEntity.ok(new SalasDTO(salaId));
    }

    public ResponseEntity<SalasDTO> criarSala (@RequestBody SalasDTO salasDTO) {
        Optional<Salas> salaExistente = salasRepository.findByNome(salasDTO.getNome());
        if (salaExistente.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Salas criarSala = SalasDTO.toEntity(salasDTO);
        Salas salvarSala = salasRepository.save(criarSala);
        return ResponseEntity.ok(new SalasDTO(salvarSala));
    }

    public ResponseEntity<SalasDTO> editarSala (@PathVariable Long id, @RequestBody SalasDTO salasDTO) {
        Optional<Salas> salaExistente = salasRepository.findById(id);
        if (salaExistente.isEmpty()){
            return ResponseEntity.notFound().build();
        }
         Salas editarSala = SalasDTO.toEntity(salasDTO);
         Salas salvarSala = salasRepository.save(editarSala);
         return ResponseEntity.ok(new SalasDTO(salvarSala));
    }

    public ResponseEntity<Void> deletarSala (@PathVariable Long id) {
        Optional<Salas> salaExistente = salasRepository.findById(id);
        if (salaExistente.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        salasRepository.deleteById(id);
        return ResponseEntity.notFound().build();
    }

}
