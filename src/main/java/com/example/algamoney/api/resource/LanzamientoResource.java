package com.example.algamoney.api.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoney.api.event.RecursoCreadoEvent;
import com.example.algamoney.api.exceptionhandler.AlgamoneyExceptionHandler.Error;
import com.example.algamoney.api.model.Lanzamiento;
import com.example.algamoney.api.repository.LanzamientoRepository;
import com.example.algamoney.api.repository.filter.LanzamientoFilter;
import com.example.algamoney.api.repository.proyeccion.ResumenLanzamiento;
import com.example.algamoney.api.service.LanzamientoService;
import com.example.algamoney.api.service.exception.PersonaInactivaOInexistenteException;

@RestController
@RequestMapping("/lanzamiento")
public class LanzamientoResource {
	
	@Autowired
	private LanzamientoRepository lanzamientoRepository;
	
	@Autowired
	private LanzamientoService lanzamientoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageSource messageSource;
	
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
	public Page<Lanzamiento> buscar(LanzamientoFilter lanzamientoFilter, Pageable pageable){
		return lanzamientoRepository.filtrar(lanzamientoFilter, pageable);
	}
	
	@GetMapping(params="resumen")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
	public Page<ResumenLanzamiento> resumir(LanzamientoFilter lanzamientoFilter, Pageable pageable){
		return lanzamientoRepository.resumir(lanzamientoFilter, pageable);
	}
	
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
	public ResponseEntity<Lanzamiento>  buscarPorCodigo(@PathVariable Long codigo) {		
		Lanzamiento lanzamiento= lanzamientoRepository.findOne(codigo);
		return lanzamiento != null ? ResponseEntity.ok(lanzamiento) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and #oauth2.hasScope('write')")
	public ResponseEntity<Lanzamiento> crear(@Valid @RequestBody Lanzamiento lanzamiento, HttpServletResponse response)
	{
		Lanzamiento lanzamientoSalva = lanzamientoService.salvar(lanzamiento);
		
		publisher.publishEvent(new RecursoCreadoEvent(this, response, lanzamientoSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(lanzamientoSalva);
	}
	
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo)
	{
		lanzamientoRepository.delete(codigo);
		
	}
	
	
	@ExceptionHandler({PersonaInactivaOInexistenteException.class})
	public ResponseEntity<Object> handlePersonaInactivaOInexistenteException(PersonaInactivaOInexistenteException ex)
	{
		String mensajeUsuario=messageSource.getMessage("persona.inexisten-o-inactiva",null, LocaleContextHolder.getLocale());
		String mensajeDesarrollador=ex.toString();
		List<Error> errores= Arrays.asList(new Error(mensajeUsuario,mensajeDesarrollador));
		return ResponseEntity.badRequest().body(errores);
	}
}
