package com.skilling.lms.reporting_service.mapper;

import com.skilling.lms.reporting_service.domains.ReporteGenerado;
import com.skilling.lms.reporting_service.dto.request.ReporteGeneradoRequest;
import com.skilling.lms.reporting_service.dto.response.ReporteGeneradoResponse;

import java.time.LocalDate;

public class ReporteGeneradoMapper {
    public static ReporteGenerado toEntity(ReporteGeneradoRequest request) {
        return ReporteGenerado.builder()
                .nombreReporte(request.name())
                .fechaGeneracion(LocalDate.now())
                .parametrosReporte(request.parameters())
                .urlReporte(request.url())
                .usuariosId(request.usersId())
                .build();
    }

    public static ReporteGeneradoResponse toResponse(ReporteGenerado entity) {
        return new ReporteGeneradoResponse(
                entity.getId(),
                entity.getNombreReporte(),
                entity.getFechaGeneracion(),
                entity.getParametrosReporte(),
                entity.getUrlReporte(),
                entity.getUsuariosId()
        );
    }
}
