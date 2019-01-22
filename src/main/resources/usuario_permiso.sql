-- =============================================================================
-- Diagram Name: DiagramaUsuarioPermiso
-- Created on: 21/1/2019 14:59:04
-- Diagram Version: 
-- =============================================================================


CREATE TABLE "usuario" (
	"codigo" BIGSERIAL NOT NULL,
	"nombre" varchar(50) NOT NULL,
	"email" varchar(50) NOT NULL,
	"senha" varchar(150) NOT NULL,
	"usuario_permiso_codigo_permiso" int8 NOT NULL,
	PRIMARY KEY("codigo")
);

CREATE TABLE "permiso" (
	"codigo" BIGSERIAL NOT NULL,
	"descripcion" varchar(50) NOT NULL,
	PRIMARY KEY("codigo")
);

CREATE TABLE "usuario_permiso" (
	"codigo_usuario" int8 NOT NULL,
	"codigo_permiso" int8 NOT NULL,
	PRIMARY KEY("codigo_usuario","codigo_permiso")
);


ALTER TABLE "usuario_permiso" ADD CONSTRAINT "Ref_usuario_permiso_to_usuario" FOREIGN KEY ("codigo_usuario")
	REFERENCES "usuario"("codigo")
	MATCH SIMPLE
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
	NOT DEFERRABLE;

ALTER TABLE "usuario_permiso" ADD CONSTRAINT "Ref_usuario_permiso_to_permiso" FOREIGN KEY ("codigo_permiso")
	REFERENCES "permiso"("codigo")
	MATCH SIMPLE
	ON DELETE NO ACTION
	ON UPDATE NO ACTION
	NOT DEFERRABLE;


