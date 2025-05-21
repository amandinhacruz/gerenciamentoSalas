package com.example.demo.repository;

import com.example.demo.model.Salas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SalasRepository extends JpaRepository<Salas, Long> {
    Optional<Salas> findByNome(String nome);
}
