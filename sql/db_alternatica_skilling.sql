-- Creado con Kata Kuntur - Modelador de Datos
-- Versión: 2.5.4
-- Sitio Web: http://katakuntur.jeanmazuelos.com/
-- Si usted encuentra algún error le agradeceriamos lo reporte en:
-- http://pm.jeanmazuelos.com/katakuntur/issues/new

-- Administrador de Base de Datos: PostgreSQL
-- Diagrama: db_alternatica_skilling
-- Autor: josephgallegos
-- Fecha y hora: 20/06/2025 11:09:06


--Tabla de usuarios
CREATE TABLE "usuarios" (
	"id" UUID NOT NULL DEFAULT gen_random_uuid() ,
	"nombre" VARCHAR(100) NOT NULL ,
	"apellido" VARCHAR(255) NOT NULL ,
	"email" VARCHAR(255) NOT NULL UNIQUE ,
	"clave_hash" VARCHAR(255) NOT NULL ,
	"fecha_registro" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
	"estado_activo" BOOLEAN NOT NULL DEFAULT TRUE ,
	"tipo_usuario" VARCHAR(50) NOT NULL ,
	PRIMARY KEY("id")
);

--Tabla de roles
CREATE TABLE "roles" (
	"id" UUID NOT NULL DEFAULT gen_random_uuid() ,
	"nombre_rol" VARCHAR(100) NOT NULL UNIQUE ,
	"descripcion" TEXT NOT NULL ,
	PRIMARY KEY("id")
);

--Tabla de permisos
CREATE TABLE "permisos" (
	"id" UUID NOT NULL DEFAULT gen_random_uuid() ,
	"nombre_permiso" VARCHAR(100) NOT NULL UNIQUE ,
	"descripcion" TEXT NOT NULL ,
	PRIMARY KEY("id")
);

--Tabla de rol_permiso
CREATE TABLE "rol_permiso" (
	"roles_id" UUID NOT NULL ,
	"permisos_id" UUID NOT NULL ,
	PRIMARY KEY("roles_id","permisos_id")
);

--Tabla de plantilla_notificaciones
CREATE TABLE "plantilla_notificaciones" (
	"id" UUID NOT NULL DEFAULT gen_random_uuid() ,
	"nombre_interno" VARCHAR(255) NOT NULL UNIQUE ,
	"asunto_plantilla" VARCHAR(255) NOT NULL ,
	"cuerpo_plantilla" TEXT NOT NULL ,
	"variables_disponibles" JSON NOT NULL ,
	"canal_preferido" VARCHAR(100) NOT NULL ,
	PRIMARY KEY("id")
);

--Tabla de notificaciones
CREATE TABLE "notificaciones" (
	"id" UUID NOT NULL DEFAULT gen_random_uuid() ,
	"tipo_notificacion" VARCHAR(100) NOT NULL ,
	"titulo" VARCHAR(255) NOT NULL ,
	"contenido" TEXT NOT NULL ,
	"fecha_envio" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
	"fecha_lectura" TIMESTAMP NULL ,
	"esta_leida" BOOLEAN NOT NULL DEFAULT FALSE ,
	"url_accion" VARCHAR(255) NULL ,
	"evento_origen" VARCHAR(255) NOT NULL ,
	"referencia_id" VARCHAR(100) NOT NULL ,
	"usuarios_id" UUID NOT NULL ,
	"plantilla_notificaciones_id" UUID NOT NULL ,
	PRIMARY KEY("id")
);

--Tabla de inscripcion
CREATE TABLE "inscripcion" (
	"id" UUID NOT NULL DEFAULT gen_random_uuid() ,
	"fecha_inscripcion" DATE NOT NULL DEFAULT CURRENT_DATE ,
	"estado" VARCHAR(100) NOT NULL DEFAULT 'Activo' ,
	"fecha_finalizacion" DATE NULL ,
	"progreso_porcentaje" DOUBLE PRECISION NOT NULL DEFAULT 0.0 ,
	"usuarios_id" UUID NOT NULL ,
	"curso_ofertado_id" UUID NOT NULL ,
	PRIMARY KEY("id")
);

--Tabla de foro
CREATE TABLE "foro" (
	"id" UUID NOT NULL DEFAULT gen_random_uuid() ,
	"nombre_foro" VARCHAR(255) NOT NULL UNIQUE ,
	"descripcion" TEXT NOT NULL ,
	"fecha_creacion" DATE NOT NULL DEFAULT CURRENT_DATE ,
	"curso_ofertado_id" UUID NOT NULL ,
	PRIMARY KEY("id")
);

--Tabla de mensaje
CREATE TABLE "mensaje" (
	"id" UUID NOT NULL DEFAULT gen_random_uuid() ,
	"asunto" VARCHAR(255) NULL ,
	"contenido" TEXT NOT NULL ,
	"fecha_envio" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
	"leido" BOOLEAN NOT NULL DEFAULT FALSE ,
	"usuarios_id" UUID NOT NULL ,
	"foro_id" UUID NOT NULL ,
	PRIMARY KEY("id")
);

--Tabla de seguimiento_progreso
CREATE TABLE "seguimiento_progreso" (
	"id" UUID NOT NULL DEFAULT gen_random_uuid() ,
	"fecha_ultimo_acceso" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
	"completado" BOOLEAN NOT NULL DEFAULT FALSE ,
	"puntaje_obtenido_modulo" DOUBLE PRECISION NOT NULL DEFAULT 0.0 ,
	"inscripcion_id" UUID NOT NULL ,
	"modulo_id" UUID NOT NULL ,
	PRIMARY KEY("id")
);

--Tabla de asistencia
CREATE TABLE "asistencia" (
	"id" UUID NOT NULL DEFAULT gen_random_uuid() ,
	"fecha_clase" DATE NOT NULL DEFAULT CURRENT_DATE ,
	"estado_asistencia" VARCHAR(100) NOT NULL ,
	"hora_registro" TIME NOT NULL DEFAULT CURRENT_TIME ,
	"inscripcion_id" UUID NOT NULL ,
	PRIMARY KEY("id")
);

--Tabla de publicacion_foro
CREATE TABLE "publicacion_foro" (
	"id" UUID NOT NULL DEFAULT gen_random_uuid() ,
	"contenido" TEXT NOT NULL ,
	"fecha_publicacion" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
	"usuarios_id" UUID NOT NULL ,
	"foro_id" UUID NOT NULL ,
	"publicacion_foro_id" UUID NULL ,
	PRIMARY KEY("id")
);

--Tabla de log_actividad
CREATE TABLE "log_actividad" (
	"id" UUID NOT NULL DEFAULT gen_random_uuid() ,
	"fecha_hora" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
	"tipo_evento" VARCHAR(150) NOT NULL ,
	"detalle_evento" TEXT NOT NULL ,
	"ip_origen" VARCHAR(100) NOT NULL ,
	"usuarios_id" UUID NOT NULL ,
	PRIMARY KEY("id")
);

--Tabla de reporte_generado
CREATE TABLE "reporte_generado" (
	"id" UUID NOT NULL DEFAULT gen_random_uuid() ,
	"nombre_reporte" VARCHAR(255) NOT NULL UNIQUE ,
	"fecha_generacion" DATE NOT NULL DEFAULT CURRENT_DATE ,
	"parametros_reporte" TEXT NOT NULL ,
	"url_reporte" VARCHAR(255) NOT NULL UNIQUE ,
	"usuarios_id" UUID NOT NULL ,
	PRIMARY KEY("id")
);

--Tabla de recurso_didactico
CREATE TABLE "recurso_didactico" (
	"id" UUID NOT NULL DEFAULT gen_random_uuid() ,
	"nombre_archivo" VARCHAR(255) NOT NULL ,
	"tipo_archivo" VARCHAR(150) NOT NULL ,
	"url" VARCHAR(255) NOT NULL UNIQUE ,
	"fecha_subida" DATE NOT NULL DEFAULT CURRENT_DATE ,
	"metadata" JSON NOT NULL ,
	"version" INTEGER NOT NULL DEFAULT 1 ,
	"es_activo" BOOLEAN NOT NULL DEFAULT TRUE ,
	"usuarios_id" UUID NOT NULL ,
	PRIMARY KEY("id")
);

--Tabla de contenido_modulo
CREATE TABLE "contenido_modulo" (
	"id" UUID NOT NULL DEFAULT gen_random_uuid() ,
	"orden_modulo" INTEGER NOT NULL ,
	"recurso_didactico_id" UUID NOT NULL ,
	"modulo_id" UUID NOT NULL ,
	PRIMARY KEY("id"),
	UNIQUE("recurso_didactico_id", "modulo_id")
);

--Tabla de asignacion_horario
CREATE TABLE "asignacion_horario" (
	"id" UUID NOT NULL DEFAULT gen_random_uuid() ,
	"dia_semana" VARCHAR(150) NOT NULL ,
	"hora_inicio" TIME NOT NULL ,
	"hora_fin" TIME NOT NULL ,
	"tipo_asignacion" VARCHAR(150) NOT NULL ,
	"usuarios_id" UUID NOT NULL ,
	"espacio_fisico_id" UUID NOT NULL ,
	"plataforma_virtual_id" UUID NOT NULL ,
	"curso_ofertado_id" UUID NOT NULL ,
	PRIMARY KEY("id")
);

--Tabla de periodo_academico
CREATE TABLE "periodo_academico" (
	"id" UUID NOT NULL DEFAULT gen_random_uuid() ,
	"nombre" VARCHAR(255) NOT NULL UNIQUE ,
	"fecha_inicio" DATE NOT NULL ,
	"fecha_fin" DATE NOT NULL ,
	"estado" VARCHAR(150) NOT NULL ,
	"tipo_periodo" VARCHAR(150) NOT NULL ,
	PRIMARY KEY("id")
);

--Tabla de espacio_fisico
CREATE TABLE "espacio_fisico" (
	"id" UUID NOT NULL DEFAULT gen_random_uuid() ,
	"nombre" VARCHAR(255) NOT NULL UNIQUE ,
	"capacidad" INTEGER NOT NULL ,
	"tipo_espacio" VARCHAR(150) NOT NULL ,
	"ubicacion" VARCHAR(255) NOT NULL ,
	PRIMARY KEY("id")
);

--Tabla de plataforma_virtual
CREATE TABLE "plataforma_virtual" (
	"id" UUID NOT NULL DEFAULT gen_random_uuid() ,
	"nombre_plataforma" VARCHAR(255) NOT NULL UNIQUE ,
	"url" VARCHAR(255) NOT NULL UNIQUE ,
	"tipo" VARCHAR(255) NOT NULL ,
	"credenciales_api" TEXT NOT NULL ,
	PRIMARY KEY("id")
);

--Tabla de plan_precio
CREATE TABLE "plan_precio" (
	"id" UUID NOT NULL DEFAULT gen_random_uuid() ,
	"nombre_plan" VARCHAR(255) NOT NULL ,
	"version" VARCHAR(150) NOT NULL ,
	"fecha_aprobacion" DATE NOT NULL ,
	"estado" VARCHAR(150) NOT NULL ,
	"fecha_ultima_actualizacion" DATE NOT NULL DEFAULT CURRENT_DATE ,
	"curso_ofertado_id" UUID NOT NULL ,
	PRIMARY KEY("id"),
	UNIQUE("nombre_plan", "version", "curso_ofertado_id")
);

--Tabla de transaccion_pago
CREATE TABLE "transaccion_pago" (
	"id" UUID NOT NULL DEFAULT gen_random_uuid() ,
	"monto_pagado" NUMERIC(10,2) NOT NULL ,
	"fecha_transaccion" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
	"metodo_pago" VARCHAR(150) NOT NULL ,
	"estado_pago" VARCHAR(150) NOT NULL ,
	"referencia_externa" VARCHAR(255) NOT NULL UNIQUE ,
	"usuarios_id" UUID NOT NULL ,
	"plan_precio_id" UUID NOT NULL ,
	"curso_ofertado_id" UUID NULL ,
	PRIMARY KEY("id")
);

--Tabla de curso_ofertado
CREATE TABLE "curso_ofertado" (
	"id" UUID NOT NULL DEFAULT gen_random_uuid() ,
	"nombre_curso" VARCHAR(255) NOT NULL ,
	"codigo_curso" VARCHAR(150) NOT NULL UNIQUE ,
	"creditos" INTEGER NOT NULL ,
	"descripcion" VARCHAR(255) NOT NULL ,
	"duracion_semanas" INTEGER NOT NULL ,
	"modalidad" VARCHAR(150) NOT NULL ,
	"periodo_academico_id" UUID NOT NULL ,
	"plan_estudio_id" UUID NOT NULL ,
	PRIMARY KEY("id")
);

--Tabla de modelo_educativo
CREATE TABLE "modelo_educativo" (
	"id" UUID NOT NULL DEFAULT gen_random_uuid() ,
	"nombre_modelo" VARCHAR(255) NOT NULL UNIQUE ,
	"descripcion" VARCHAR(255) NOT NULL ,
	"version" VARCHAR(100) NOT NULL ,
	"fecha_creacion" DATE NOT NULL DEFAULT CURRENT_DATE ,
	"estado" VARCHAR(150) NOT NULL ,
	"usuarios_id" UUID NOT NULL ,
	PRIMARY KEY("id")
);

--Tabla de plan_estudio
CREATE TABLE "plan_estudio" (
	"id" UUID NOT NULL DEFAULT gen_random_uuid() ,
	"nombre_plan" VARCHAR(255) NOT NULL ,
	"version" VARCHAR(150) NOT NULL ,
	"fecha_aprobacion" DATE NOT NULL ,
	"estado" VARCHAR(150) NOT NULL ,
	"fecha_ultima_actualizacion" DATE NOT NULL DEFAULT CURRENT_DATE ,
	"modelo_educativo_id" UUID NOT NULL ,
	PRIMARY KEY("id"),
	UNIQUE("nombre_plan", "version")
);

--Tabla de perfil_curricular
CREATE TABLE "perfil_curricular" (
	"id" UUID NOT NULL DEFAULT gen_random_uuid() ,
	"nombre_perfil" VARCHAR(255) NOT NULL UNIQUE ,
	"descripcion" VARCHAR(255) NOT NULL ,
	"modelo_educativo_id" UUID NOT NULL ,
	PRIMARY KEY("id")
);

--Tabla de perfil_competencia
CREATE TABLE "perfil_competencia" (
	"perfil_curricular_id" UUID NOT NULL ,
	"competencia_id" UUID NOT NULL ,
	PRIMARY KEY("perfil_curricular_id","competencia_id")
);

--Tabla de competencia
CREATE TABLE "competencia" (
	"id" UUID NOT NULL DEFAULT gen_random_uuid() ,
	"nombre_competencia" VARCHAR(255) NOT NULL UNIQUE ,
	"descripcion" VARCHAR(255) NOT NULL ,
	"nivel_esperado" VARCHAR(255) NOT NULL ,
	PRIMARY KEY("id")
);

--Tabla de modulo
CREATE TABLE "modulo" (
	"id" UUID NOT NULL DEFAULT gen_random_uuid() ,
	"nombre_modulo" VARCHAR(255) NOT NULL ,
	"orden" INTEGER NOT NULL ,
	"descripcion" VARCHAR(255) NOT NULL ,
	"objetivos_aprendizaje" TEXT NOT NULL ,
	"curso_ofertado_id" UUID NOT NULL ,
	PRIMARY KEY("id"),
	UNIQUE("nombre_modulo", "curso_ofertado_id")
);

--Tabla de evaluacion
CREATE TABLE "evaluacion" (
	"id" UUID NOT NULL DEFAULT gen_random_uuid() ,
	"nombre_evaluacion" VARCHAR(255) NOT NULL ,
	"tipo_evaluacion" VARCHAR(150) NOT NULL ,
	"porcentaje_calificacion" DOUBLE PRECISION NOT NULL ,
	"fecha_vencimiento" DATE NOT NULL ,
	"descripcion" TEXT NOT NULL ,
	"peso_relativo" DOUBLE PRECISION NOT NULL ,
	"puntaje_maximo" DOUBLE PRECISION NOT NULL ,
	"curso_ofertado_id" UUID NOT NULL ,
	"modulo_id" UUID NOT NULL ,
	PRIMARY KEY("id")
);

--Tabla de certificado
CREATE TABLE "certificado" (
	"id" UUID NOT NULL DEFAULT gen_random_uuid() ,
	"fecha_emision" DATE NOT NULL DEFAULT CURRENT_DATE ,
	"url_certificado" VARCHAR(255) NOT NULL UNIQUE ,
	"codigo_verificacion" VARCHAR(255) NOT NULL UNIQUE ,
	"inscripcion_id" UUID NOT NULL UNIQUE ,
	PRIMARY KEY("id")
);

--Tabla de calificacion
CREATE TABLE "calificacion" (
	"id" UUID NOT NULL DEFAULT gen_random_uuid(),
	"puntaje_obtenido" DOUBLE PRECISION NOT NULL ,
	"fecha_calificacion" DATE NOT NULL DEFAULT CURRENT_DATE ,
	"comentarios_docente" TEXT NOT NULL ,
	"inscripcion_id" UUID NOT NULL ,
	"evaluacion_id" UUID NOT NULL ,
	PRIMARY KEY("id"),
	UNIQUE("inscripcion_id", "evaluacion_id")
);

--Tabla de usuario_role
CREATE TABLE "usuario_role" (
	"usuarios_id" UUID NOT NULL ,
	"roles_id" UUID NOT NULL ,
	PRIMARY KEY("usuarios_id","roles_id")
);

--Tabla de curso_prerequisito
CREATE TABLE "curso_prerequisito" (
	"curso_id" UUID NOT NULL ,
	"prerequisito_id" UUID NOT NULL,
	PRIMARY KEY("curso_id","prerequisito_id")
);

--GENERANDO RELACIONES
ALTER TABLE "rol_permiso" ADD FOREIGN KEY ("roles_id") REFERENCES "roles" ("id") ON DELETE CASCADE;
ALTER TABLE "rol_permiso" ADD FOREIGN KEY ("permisos_id") REFERENCES "permisos" ("id") ON DELETE CASCADE;
ALTER TABLE "notificaciones" ADD FOREIGN KEY ("usuarios_id") REFERENCES "usuarios" ("id") ON DELETE CASCADE;
ALTER TABLE "notificaciones" ADD FOREIGN KEY ("plantilla_notificaciones_id") REFERENCES "plantilla_notificaciones" ("id") ON DELETE RESTRICT;
ALTER TABLE "inscripcion" ADD FOREIGN KEY ("usuarios_id") REFERENCES "usuarios" ("id") ON DELETE RESTRICT;
ALTER TABLE "inscripcion" ADD FOREIGN KEY ("curso_ofertado_id") REFERENCES "curso_ofertado" ("id") ON DELETE CASCADE;
ALTER TABLE "foro" ADD FOREIGN KEY ("curso_ofertado_id") REFERENCES "curso_ofertado" ("id") ON DELETE CASCADE;
ALTER TABLE "mensaje" ADD FOREIGN KEY ("usuarios_id") REFERENCES "usuarios" ("id") ON DELETE CASCADE;
ALTER TABLE "mensaje" ADD FOREIGN KEY ("foro_id") REFERENCES "foro" ("id") ON DELETE CASCADE;
ALTER TABLE "seguimiento_progreso" ADD FOREIGN KEY ("inscripcion_id") REFERENCES "inscripcion" ("id") ON DELETE CASCADE;
ALTER TABLE "seguimiento_progreso" ADD FOREIGN KEY ("modulo_id") REFERENCES "modulo" ("id") ON DELETE CASCADE;
ALTER TABLE "asistencia" ADD FOREIGN KEY ("inscripcion_id") REFERENCES "inscripcion" ("id") ON DELETE CASCADE;
ALTER TABLE "publicacion_foro" ADD FOREIGN KEY ("usuarios_id") REFERENCES "usuarios" ("id") ON DELETE CASCADE;
ALTER TABLE "publicacion_foro" ADD FOREIGN KEY ("foro_id") REFERENCES "foro" ("id") ON DELETE CASCADE;
ALTER TABLE "publicacion_foro" ADD FOREIGN KEY ("publicacion_foro_id") REFERENCES "publicacion_foro" ("id") ON DELETE CASCADE;
ALTER TABLE "log_actividad" ADD FOREIGN KEY ("usuarios_id") REFERENCES "usuarios" ("id") ON DELETE CASCADE;
ALTER TABLE "reporte_generado" ADD FOREIGN KEY ("usuarios_id") REFERENCES "usuarios" ("id") ON DELETE CASCADE;
ALTER TABLE "recurso_didactico" ADD FOREIGN KEY ("usuarios_id") REFERENCES "usuarios" ("id") ON DELETE RESTRICT;
ALTER TABLE "contenido_modulo" ADD FOREIGN KEY ("recurso_didactico_id") REFERENCES "recurso_didactico" ("id") ON DELETE CASCADE;
ALTER TABLE "contenido_modulo" ADD FOREIGN KEY ("modulo_id") REFERENCES "modulo" ("id") ON DELETE CASCADE;
ALTER TABLE "asignacion_horario" ADD FOREIGN KEY ("usuarios_id") REFERENCES "usuarios" ("id") ON DELETE RESTRICT;
ALTER TABLE "asignacion_horario" ADD FOREIGN KEY ("espacio_fisico_id") REFERENCES "espacio_fisico" ("id") ON DELETE RESTRICT;
ALTER TABLE "asignacion_horario" ADD FOREIGN KEY ("plataforma_virtual_id") REFERENCES "plataforma_virtual" ("id") ON DELETE RESTRICT;
ALTER TABLE "asignacion_horario" ADD FOREIGN KEY ("curso_ofertado_id") REFERENCES "curso_ofertado" ("id") ON DELETE CASCADE;
ALTER TABLE "plan_precio" ADD FOREIGN KEY ("curso_ofertado_id") REFERENCES "curso_ofertado" ("id") ON DELETE CASCADE;
ALTER TABLE "transaccion_pago" ADD FOREIGN KEY ("usuarios_id") REFERENCES "usuarios" ("id") ON DELETE RESTRICT;
ALTER TABLE "transaccion_pago" ADD FOREIGN KEY ("plan_precio_id") REFERENCES "plan_precio" ("id") ON DELETE RESTRICT;
ALTER TABLE "transaccion_pago" ADD FOREIGN KEY ("curso_ofertado_id") REFERENCES "curso_ofertado" ("id") ON DELETE SET NULL;
ALTER TABLE "curso_ofertado" ADD FOREIGN KEY ("periodo_academico_id") REFERENCES "periodo_academico" ("id") ON DELETE RESTRICT;
ALTER TABLE "curso_ofertado" ADD FOREIGN KEY ("plan_estudio_id") REFERENCES "plan_estudio" ("id") ON DELETE RESTRICT;
ALTER TABLE "modelo_educativo" ADD FOREIGN KEY ("usuarios_id") REFERENCES "usuarios" ("id") ON DELETE RESTRICT;
ALTER TABLE "plan_estudio" ADD FOREIGN KEY ("modelo_educativo_id") REFERENCES "modelo_educativo" ("id") ON DELETE RESTRICT;
ALTER TABLE "perfil_curricular" ADD FOREIGN KEY ("modelo_educativo_id") REFERENCES "modelo_educativo" ("id") ON DELETE RESTRICT;
ALTER TABLE "perfil_competencia" ADD FOREIGN KEY ("perfil_curricular_id") REFERENCES "perfil_curricular" ("id") ON DELETE CASCADE;
ALTER TABLE "perfil_competencia" ADD FOREIGN KEY ("competencia_id") REFERENCES "competencia" ("id") ON DELETE CASCADE;
ALTER TABLE "modulo" ADD FOREIGN KEY ("curso_ofertado_id") REFERENCES "curso_ofertado" ("id") ON DELETE CASCADE;
ALTER TABLE "evaluacion" ADD FOREIGN KEY ("curso_ofertado_id") REFERENCES "curso_ofertado" ("id") ON DELETE CASCADE;
ALTER TABLE "evaluacion" ADD FOREIGN KEY ("modulo_id") REFERENCES "modulo" ("id") ON DELETE CASCADE;
ALTER TABLE "certificado" ADD FOREIGN KEY ("inscripcion_id") REFERENCES "inscripcion" ("id") ON DELETE CASCADE;
ALTER TABLE "calificacion" ADD FOREIGN KEY ("inscripcion_id") REFERENCES "inscripcion" ("id") ON DELETE CASCADE;
ALTER TABLE "calificacion" ADD FOREIGN KEY ("evaluacion_id") REFERENCES "evaluacion" ("id") ON DELETE CASCADE;
ALTER TABLE "usuario_role" ADD FOREIGN KEY ("usuarios_id") REFERENCES "usuarios" ("id") ON DELETE CASCADE;
ALTER TABLE "usuario_role" ADD FOREIGN KEY ("roles_id") REFERENCES "roles" ("id") ON DELETE RESTRICT;
ALTER TABLE "curso_prerequisito" ADD FOREIGN KEY ("curso_id") REFERENCES "curso_ofertado" ("id") ON DELETE CASCADE;
ALTER TABLE "curso_prerequisito" ADD FOREIGN KEY ("prerequisito_id") REFERENCES "curso_ofertado" ("id") ON DELETE CASCADE;

select * from competencia c 