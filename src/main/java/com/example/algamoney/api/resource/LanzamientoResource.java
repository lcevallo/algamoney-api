package com.example.algamoney.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoney.api.event.RecursoCreadoEvent;
import com.example.algamoney.api.model.Lanzamiento;
import com.example.algamoney.api.repository.LanzamientoRepository;

@RestController
@RequestMapping("/lanzamiento")
public class LanzamientoResource {
	
	@Autowired
	private LanzamientoRepository lanzamientoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	@GetMapping
	public List<Lanzamiento> listar(){
		return lanzamientoRepository.findAll();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Lanzamiento>  buscarPorCodigo(@PathVariable Long codigo) {		
		Lanzamiento lanzamiento= lanzamientoRepository.findById(codigo).orElse(null);
		return lanzamiento != null ? ResponseEntity.ok(lanzamiento) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Lanzamiento> crear(@Valid @RequestBody Lanzamiento lanzamiento, HttpServletResponse response)
	{
		Lanzamiento lanzamientoSalva = lanzamientoRepository.save(lanzamiento);
		publisher.publishEvent(new RecursoCreadoEvent(this, response, lanzamientoSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(lanzamientoSalva);
	}
}
