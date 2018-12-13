package com.example.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamoney.api.model.Lanzamiento;

public interface LanzamientoRepository  extends JpaRepository<Lanzamiento,Long> {

}
