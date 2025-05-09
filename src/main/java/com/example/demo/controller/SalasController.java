package com.example.demo.controller;

import com.example.demo.model.Salas;
import com.example.demo.repository.SalasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalasController {

    @Autowired
    private SalasRepository salasRepository;

    @GetMapping
    public List<Salas> listarSalas(){
        return salasRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Salas> consultarSalas(@PathVariable Long id){
       return salasRepository.findById(id)
               .map(salas -> ResponseEntity.ok(salas))
               .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Salas adicionarSalas(@RequestBody Salas salas){
        return salasRepository.save(salas);
    }

    @DeleteMapping("/{id}")
    public String removerSalas(@PathVariable Long id){
        salasRepository.deleteById(id);
        return "Deletado com sucesso!";
    }





}
