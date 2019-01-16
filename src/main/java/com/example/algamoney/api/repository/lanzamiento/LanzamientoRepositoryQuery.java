package com.example.algamoney.api.repository.lanzamiento;

import java.util.List;

import com.example.algamoney.api.model.Lanzamiento;
import com.example.algamoney.api.repository.filter.LanzamientoFilter;

public interface LanzamientoRepositoryQuery {
	
	public List<Lanzamiento> filtrar(LanzamientoFilter lanzamientoFilter);
}
