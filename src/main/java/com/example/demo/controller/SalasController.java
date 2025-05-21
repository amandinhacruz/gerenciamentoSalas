package com.example.demo.controller;

import com.example.demo.dto.SalasDTO;
import com.example.demo.model.Salas;
import com.example.demo.repository.SalasRepository;
import com.example.demo.service.SalasService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salas")
@RequiredArgsConstructor
public class SalasController {


    private final SalasService salasService;

    @GetMapping
    public ResponseEntity<List<SalasDTO>> listarSalas(){
        return salasService.listarSalas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalasDTO> consultarSalas(@PathVariable Long id){
       return salasService.buscarSala(id);
    }

    @PostMapping
    public ResponseEntity<SalasDTO> adicionarSalas(@RequestBody SalasDTO salasDTO){
        return salasService.criarSala(salasDTO);
    }

    public ResponseEntity<SalasDTO> editarSala(@PathVariable Long id, @RequestBody SalasDTO salasDTO) {
        return salasService.editarSala(id, salasDTO);
    }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarSala(@PathVariable Long id) {
        return salasService.deletarSala(id);
  }





}
