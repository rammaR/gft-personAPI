package com.rammar.me.jornadaAPI.repository;


import com.rammar.me.jornadaAPI.entity.JornadaTrabalho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JornadaRepository extends JpaRepository<JornadaTrabalho, Long> {
}
