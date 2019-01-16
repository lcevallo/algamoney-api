package com.example.algamoney.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Persona.class)
public abstract class Persona_ {

	public static volatile SingularAttribute<Persona, Long> codigo;
	public static volatile SingularAttribute<Persona, Direccion> direccion;
	public static volatile SingularAttribute<Persona, String> nombre;
	public static volatile SingularAttribute<Persona, Boolean> activo;

}

