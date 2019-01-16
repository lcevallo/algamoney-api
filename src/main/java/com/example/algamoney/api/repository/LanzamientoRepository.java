package com.example.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamoney.api.model.Lanzamiento;
import com.example.algamoney.api.repository.lanzamiento.LanzamientoRepositoryQuery;

public interface LanzamientoRepository  extends JpaRepository<Lanzamiento,Long>, LanzamientoRepositoryQuery {

}
