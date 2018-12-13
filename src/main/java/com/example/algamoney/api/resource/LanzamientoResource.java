package com.example.algamoney.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoney.api.model.Lanzamiento;
import com.example.algamoney.api.repository.LanzamientoRepository;

@RestController
@RequestMapping("/lanzamiento")
public class LanzamientoResource {
	
	@Autowired
	private LanzamientoRepository lanzamientoRepository;
	
	@GetMapping
	public List<Lanzamiento> listar(){
		return lanzamientoRepository.findAll();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Lanzamiento>  buscarPorCodigo(@PathVariable Long codigo) {		
		Lanzamiento lanzamiento= lanzamientoRepository.findById(codigo).orElse(null);
		return lanzamiento != null ? ResponseEntity.ok(lanzamiento) : ResponseEntity.notFound().build();
	}
}
