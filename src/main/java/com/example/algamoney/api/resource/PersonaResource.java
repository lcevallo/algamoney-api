package com.example.algamoney.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoney.api.event.RecursoCreadoEvent;
import com.example.algamoney.api.model.Persona;
import com.example.algamoney.api.repository.PersonaRepository;
import com.example.algamoney.api.service.PersonaService;

@RestController
@RequestMapping("/personas")
public class PersonaResource {
	
	
	@Autowired
	private PersonaRepository personaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private PersonaService personaService;
	
	@GetMapping
	public List<Persona> listar(){
		return personaRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Persona> crear(@Valid @RequestBody Persona persona, HttpServletResponse response) {
	 
		Persona personaSalva=personaRepository.save(persona);
	 
	//Aqui voy a disparar un evento para crear el Location el evento que he creado
		
	  publisher.publishEvent(new RecursoCreadoEvent(this, response, personaSalva.getCodigo()));
	
	   return ResponseEntity.status(HttpStatus.CREATED).body(personaSalva);
	 
	}
	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Persona>  buscarPorCodigo(@PathVariable Long codigo) {		
		Persona persona= personaRepository.findById(codigo).orElse(null);
		return persona != null ? ResponseEntity.ok(persona) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo)
	{
		personaRepository.deleteById(codigo);
		
	}
	
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Persona> actualizar(@PathVariable Long codigo, @Valid @RequestBody Persona persona)
	{ 
		Persona personaSalva = personaService.actualizar(codigo, persona);
		return ResponseEntity.ok(personaSalva);
	}
	
	@PutMapping("/{codigo}/activo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void actualizarPropiedadActivo(@PathVariable Long codigo,@RequestBody Boolean activo) {
		personaService.actualizarPropiedadActivo(codigo,activo);
		
	}
	
}
