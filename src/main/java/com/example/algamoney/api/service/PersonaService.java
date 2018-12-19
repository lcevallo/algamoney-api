package com.example.algamoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamoney.api.model.Persona;
import com.example.algamoney.api.repository.PersonaRepository;

@Service
public class PersonaService {
	
	@Autowired
	private PersonaRepository personaRepository;
	
	public Persona actualizar(Long codigo, Persona persona) {
		
		Persona personaSalva = buscarPersonaPorCodigo(codigo);
		
		

		BeanUtils.copyProperties(persona, personaSalva, "codigo");
		return personaRepository.save(personaSalva);
	}


	public void actualizarPropiedadActivo(Long codigo, Boolean activo) {
		Persona personaSalva = buscarPersonaPorCodigo(codigo);
		personaSalva.setActivo(activo);
		personaRepository.save(personaSalva);
		
	}
	
	
	public Persona buscarPersonaPorCodigo(Long codigo) {
		Persona personaSalva = personaRepository.findById(codigo).orElse(null);
		if(personaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return personaSalva;
	}

}
