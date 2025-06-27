package com.skilling.lms.reporting_service.service.impl;

import com.skilling.lms.reporting_service.domains.ReporteGenerado;
import com.skilling.lms.reporting_service.dto.request.ReporteGeneradoRequest;
import com.skilling.lms.reporting_service.dto.response.ReporteGeneradoResponse;
import com.skilling.lms.reporting_service.mapper.ReporteGeneradoMapper;
import com.skilling.lms.reporting_service.repositories.ReporteGeneradoRepository;
import com.skilling.lms.reporting_service.service.ReporteGeneradoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ReporteGeneradoServiceImpl implements ReporteGeneradoService {
    private final ReporteGeneradoRepository reporteGeneradoRepository;

    @Override
    public Mono<ReporteGeneradoResponse> createReporteGenerado(ReporteGeneradoRequest request) {
        ReporteGenerado reporteGeneradoSave = ReporteGeneradoMapper.toEntity(request);
        return reporteGeneradoRepository.save(reporteGeneradoSave)
                .map(ReporteGeneradoMapper::toResponse);
    }
}
