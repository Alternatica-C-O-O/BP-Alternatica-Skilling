# PETICIONES DE PRUEBA - RESOURCE PLANNING SERVICE

## BASE URL
http://localhost:8082/api/v1

## ========================================
## 1. CONTROLADOR: AsignacionHorarioController
## ========================================

### Crear Asignación de Horario
POST /asignaciones-horario
Content-Type: application/json

{
  "espacioFisicoId": "123e4567-e89b-12d3-a456-426614174001",
  "plataformaVirtualId": "123e4567-e89b-12d3-a456-426614174002",
  "usuariosId": "123e4567-e89b-12d3-a456-426614174003",
  "cursoOfertadoId": "123e4567-e89b-12d3-a456-426614174004",
  "tipoAsignacion": "PRESENCIAL",
  "horaInicio": "08:00:00",
  "horaFin": "10:00:00",
  "diaSemana": "LUNES"
}

### Obtener todas las Asignaciones de Horario
GET /asignaciones-horario

### Obtener Asignación de Horario por ID
GET /asignaciones-horario/{id}

### Actualizar Asignación de Horario
PUT /asignaciones-horario/{id}
Content-Type: application/json

{
  "espacioFisicoId": "123e4567-e89b-12d3-a456-426614174001",
  "plataformaVirtualId": "123e4567-e89b-12d3-a456-426614174002",
  "usuariosId": "123e4567-e89b-12d3-a456-426614174003",
  "cursoOfertadoId": "123e4567-e89b-12d3-a456-426614174004",
  "tipoAsignacion": "VIRTUAL",
  "horaInicio": "14:00:00",
  "horaFin": "16:00:00",
  "diaSemana": "MIERCOLES"
}

### Eliminar Asignación de Horario
DELETE /asignaciones-horario/{id}

## ========================================
## 2. CONTROLADOR: EspacioFisicoController
## ========================================

### Crear Espacio Físico
POST /espacios-fisicos
Content-Type: application/json

{
  "nombre": "Aula Magna 101",
  "capacidad": 50,
  "tipoEspacio": "AULA",
  "ubicacion": "Edificio A - Primer Piso"
}

### Obtener todos los Espacios Físicos
GET /espacios-fisicos

### Obtener Espacio Físico por ID
GET /espacios-fisicos/{id}

### Actualizar Espacio Físico
PUT /espacios-fisicos/{id}
Content-Type: application/json

{
  "nombre": "Laboratorio de Sistemas",
  "capacidad": 30,
  "tipoEspacio": "LABORATORIO",
  "ubicacion": "Edificio B - Segundo Piso"
}

### Eliminar Espacio Físico
DELETE /espacios-fisicos/{id}

## ========================================
## 3. CONTROLADOR: PlataformaVirtualController
## ========================================

### Crear Plataforma Virtual
POST /plataformas-virtuales
Content-Type: application/json

{
  "nombrePlataforma": "Zoom Educativo",
  "url": "https://zoom.us/education",
  "tipo": "VIDEOCONFERENCIA",
  "credencialesApi": "api_key_12345_secret_67890"
}

### Obtener todas las Plataformas Virtuales
GET /plataformas-virtuales

### Obtener Plataforma Virtual por ID
GET /plataformas-virtuales/{id}

### Actualizar Plataforma Virtual
PUT /plataformas-virtuales/{id}
Content-Type: application/json

{
  "nombrePlataforma": "Microsoft Teams",
  "url": "https://teams.microsoft.com",
  "tipo": "PLATAFORMA_LMS",
  "credencialesApi": "teams_api_key_abcdef"
}

### Eliminar Plataforma Virtual
DELETE /plataformas-virtuales/{id}

## ========================================
## 4. CONTROLADOR: PeriodoAcademicoController
## ========================================

### Crear Período Académico
POST /periodos-academicos
Content-Type: application/json

{
  "nombre": "Semestre 2024-1",
  "fechaInicio": "2024-02-01",
  "fechaFin": "2024-06-30",
  "estado": "ACTIVO",
  "tipoPeriodo": "SEMESTRAL"
}

### Obtener todos los Períodos Académicos
GET /periodos-academicos

### Obtener Período Académico por ID
GET /periodos-academicos/{id}

### Actualizar Período Académico
PUT /periodos-academicos/{id}
Content-Type: application/json

{
  "nombre": "Trimestre 2024-2",
  "fechaInicio": "2024-07-01",
  "fechaFin": "2024-09-30",
  "estado": "PLANIFICADO",
  "tipoPeriodo": "TRIMESTRAL"
}

### Eliminar Período Académico
DELETE /periodos-academicos/{id}

## ========================================
## EJEMPLOS DE ENUMERACIONES
## ========================================

### AsignacionTipo (para AsignacionHorario):
- PRESENCIAL
- VIRTUAL
- HIBRIDO

### EspacioTipo (para EspacioFisico):
- AULA
- LABORATORIO
- AUDITORIO
- SALA_CONFERENCIAS

### PlataformaVirtualTipo (para PlataformaVirtual):
- VIDEOCONFERENCIA
- PLATAFORMA_LMS
- HERRAMIENTA_COLABORATIVA

### PeriodoAcademicoEstado (para PeriodoAcademico):
- ACTIVO
- INACTIVO
- PLANIFICADO
- FINALIZADO

### PeriodoAcademicoTipo (para PeriodoAcademico):
- SEMESTRAL
- TRIMESTRAL
- ANUAL
- CUATRIMESTRAL

## ========================================
## NOTAS IMPORTANTES
## ========================================

1. Reemplazar {id} con UUIDs válidos en las peticiones específicas
2. Todos los endpoints requieren Content-Type: application/json para POST/PUT
3. Los IDs deben ser UUIDs válidos formato: xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx
4. Las fechas deben estar en formato ISO: YYYY-MM-DD
5. Las horas deben estar en formato HH:mm:ss
6. El servicio corre típicamente en puerto 8082
