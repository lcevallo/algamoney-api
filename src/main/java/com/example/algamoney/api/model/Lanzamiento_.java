package com.example.algamoney.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Lanzamiento.class)
public abstract class Lanzamiento_ {

	public static volatile SingularAttribute<Lanzamiento, String> descripcion;
	public static volatile SingularAttribute<Lanzamiento, Long> codigo;
	public static volatile SingularAttribute<Lanzamiento, TipoLanzamiento> tipo;
	public static volatile SingularAttribute<Lanzamiento, LocalDate> dataPagamento;
	public static volatile SingularAttribute<Lanzamiento, Persona> persona;
	public static volatile SingularAttribute<Lanzamiento, LocalDate> dataVencimiento;
	public static volatile SingularAttribute<Lanzamiento, Categoria> categoria;
	public static volatile SingularAttribute<Lanzamiento, BigDecimal> valor;
	public static volatile SingularAttribute<Lanzamiento, String> observacion;

}

