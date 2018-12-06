package com.example.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamoney.api.model.Persona;

public interface PersonaRepository   extends JpaRepository<Persona, Long> {

}
