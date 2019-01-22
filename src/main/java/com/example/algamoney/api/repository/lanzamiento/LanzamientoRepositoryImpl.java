package com.example.algamoney.api.repository.lanzamiento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.example.algamoney.api.model.Categoria_;
import com.example.algamoney.api.model.Lanzamiento;
import com.example.algamoney.api.model.Lanzamiento_;
import com.example.algamoney.api.model.Persona_;
import com.example.algamoney.api.repository.filter.LanzamientoFilter;
import com.example.algamoney.api.repository.proyeccion.ResumenLanzamiento;

public class LanzamientoRepositoryImpl implements LanzamientoRepositoryQuery {
	
	
	@PersistenceContext
	private EntityManager manager;
	
	
	@Override
	public Page<Lanzamiento> filtrar(LanzamientoFilter lanzamientoFilter, Pageable pageable){
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Lanzamiento> criteria = builder.createQuery(Lanzamiento.class);
		
		Root<Lanzamiento> root = criteria.from(Lanzamiento.class);
		
		Predicate[] predicates= crearRestricciones(lanzamientoFilter, builder, root);
		criteria.where(predicates);
		
		
		TypedQuery<Lanzamiento> query = manager.createQuery(criteria);
		
		adicionarRestricionDePaginacion(query, pageable);
		
		
		return new PageImpl<>(query.getResultList(), pageable, total(lanzamientoFilter)) ;
		
	}
	
	@Override
	public Page<ResumenLanzamiento> resumir(LanzamientoFilter lanzamientoFilter, Pageable pageable) {
		 
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumenLanzamiento> criteria =builder.createQuery(ResumenLanzamiento.class);
		Root<Lanzamiento> root = criteria.from(Lanzamiento.class);
		
		
		criteria.select(builder.construct(ResumenLanzamiento.class
						, root.get(Lanzamiento_.codigo), root.get(Lanzamiento_.descripcion)
						, root.get(Lanzamiento_.dataVencimiento), root.get(Lanzamiento_.dataPagamento)
						, root.get(Lanzamiento_.valor), root.get(Lanzamiento_.tipo)
						, root.get(Lanzamiento_.categoria).get(Categoria_.nombre)
						, root.get(Lanzamiento_.persona).get(Persona_.nombre)
						));
		
		Predicate[] predicates= crearRestricciones(lanzamientoFilter, builder, root);
		criteria.where(predicates);
		
		
		TypedQuery<ResumenLanzamiento> query = manager.createQuery(criteria);
		
		adicionarRestricionDePaginacion(query, pageable);
		
		
		return new PageImpl<>(query.getResultList(), pageable, total(lanzamientoFilter)) ;
	}

	private Predicate[] crearRestricciones(LanzamientoFilter lanzamientoFilter, CriteriaBuilder builder,
			Root<Lanzamiento> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(lanzamientoFilter.getDescripcion())) {
			predicates.add(builder.like(
										builder.lower(root.get(Lanzamiento_.descripcion)),
										"%"+lanzamientoFilter.getDescripcion().toLowerCase()+ "%" 
										));		
		}
		
		if(lanzamientoFilter.getFechaVencimientoDesde()!= null) {
			predicates.add(builder.greaterThanOrEqualTo(
														root.get(Lanzamiento_.dataVencimiento)	,
														lanzamientoFilter.getFechaVencimientoDesde()));
		
		}
		
		if(lanzamientoFilter.getFechaVencimientoHasta()!= null) {
			predicates.add( builder.lessThanOrEqualTo(
														root.get(Lanzamiento_.dataVencimiento),
														lanzamientoFilter.getFechaVencimientoHasta()) );
			
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	

	private void adicionarRestricionDePaginacion(TypedQuery<?> query, Pageable pageable) {
	  
	   int paginaActual = pageable.getPageNumber();
	   int totalRegistroPorPagina = pageable.getPageSize();
	   
	   int primerRegistroDePagina = paginaActual* totalRegistroPorPagina;
	   
	   
	   query.setFirstResult(primerRegistroDePagina);
	   
	   query.setMaxResults(totalRegistroPorPagina);
	   
	   
		
	}
	
	
	private Long total(LanzamientoFilter lanzamientoFilter) {
		 
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		
		Root<Lanzamiento> root = criteria.from(Lanzamiento.class);
		
		Predicate[] predicates = crearRestricciones(lanzamientoFilter, builder, root);
		
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
	 
	}





}
