package com.example.algamoney.api.repository.lanzamiento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.algamoney.api.model.Lanzamiento;
import com.example.algamoney.api.repository.filter.LanzamientoFilter;

public interface LanzamientoRepositoryQuery {
	
	public Page<Lanzamiento> filtrar(LanzamientoFilter lanzamientoFilter, Pageable pageable);
}
