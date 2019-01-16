package com.example.algamoney.api.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class LanzamientoFilter {
	//Voy a buscar por description y por la fecha de vencimiento
	
	private String descripcion;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaVencimientoDesde;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaVencimientoHasta;
	
	
	public String getDescripcion() { 
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public LocalDate getFechaVencimientoDesde() {
		return fechaVencimientoDesde;
	}
	public void setFechaVencimientoDesde(LocalDate fechaVencimientoDesde) {
		this.fechaVencimientoDesde = fechaVencimientoDesde;
	}
	public LocalDate getFechaVencimientoHasta() {
		return fechaVencimientoHasta;
	}
	public void setFechaVencimientoHasta(LocalDate fechaVencimientoHasta) {
		this.fechaVencimientoHasta = fechaVencimientoHasta;
	}
	
	
	
	
}
