package com.skilling.lms.assessments_service.dtos;

import java.util.UUID;
import java.time.LocalDate;

public record CertificadoDto(
	UUID id, 
	LocalDate fechaEmision, 
	String urlCertificado, 
	String codigoVerificacion, 
	UUID inscripcionId
) {
}
