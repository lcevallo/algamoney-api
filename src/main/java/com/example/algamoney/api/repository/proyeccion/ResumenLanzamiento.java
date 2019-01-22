package com.example.algamoney.api.repository.proyeccion;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.algamoney.api.model.TipoLanzamiento;

public class ResumenLanzamiento {
		
	private Long codigo;
	private String descripcion;
	private LocalDate dataVencimiento;
	
	
	public ResumenLanzamiento(Long codigo, String descripcion, LocalDate dataVencimiento, LocalDate dataPagamento,
			BigDecimal valor, TipoLanzamiento tipo, String categoria, String persona) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.dataVencimiento = dataVencimiento;
		this.dataPagamento = dataPagamento;
		this.valor = valor;
		this.tipo = tipo;
		this.categoria = categoria;
		this.persona = persona;
	}
	private LocalDate dataPagamento;
	private BigDecimal valor;
	private TipoLanzamiento tipo;
	private String categoria;
	private String persona;
	
	
	
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public LocalDate getDataVencimiento() {
		return dataVencimiento;
	}
	public void setDataVencimiento(LocalDate dataVencimiento) {
		this.dataVencimiento = dataVencimiento;
	}
	public LocalDate getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public TipoLanzamiento getTipo() {
		return tipo;
	}
	public void setTipo(TipoLanzamiento tipo) {
		this.tipo = tipo;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getPersona() {
		return persona;
	}
	public void setPersona(String persona) {
		this.persona = persona;
	}
	
	
	
}
