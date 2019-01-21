package com.example.algamoney.api.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.algamoney.api.model.Lanzamiento;
import com.example.algamoney.api.model.Persona;
import com.example.algamoney.api.repository.LanzamientoRepository;
import com.example.algamoney.api.repository.PersonaRepository;
import com.example.algamoney.api.service.exception.PersonaInactivaOInexistenteException;

@Service
public class LanzamientoService {
	
	
	@Autowired
	private PersonaRepository personaRepository;
	
	@Autowired
	private LanzamientoRepository lanzamientoRepository;

	public Lanzamiento salvar(@Valid Lanzamiento lanzamiento) {
		
		Persona persona= personaRepository.findOne(lanzamiento.getPersona().getCodigo());
		
		if(persona == null || persona.isInactivo())
		{
			throw new PersonaInactivaOInexistenteException();
		}
		
		return lanzamientoRepository.save(lanzamiento);
	}

	
	
	
}
