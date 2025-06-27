package com.skilling.lms.content_service.service.impl;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skilling.lms.content_service.domains.RecursoDidactico;
import com.skilling.lms.content_service.repositories.RecursoDidacticoRepository;
import com.skilling.lms.content_service.service.RecursoDidacticoService;
import com.skilling.lms.shared.dtos.content.request.RecursoDidacticoRequestDTO;
import com.skilling.lms.shared.dtos.content.response.RecursoDidacticoResponseDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.r2dbc.postgresql.codec.Json;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class RecursoDidacticoServiceImpl implements RecursoDidacticoService {

    private final ObjectMapper objectMapper; 
    private final RecursoDidacticoRepository recursoDidacticoRepository;

    private static final String RECURSO_DIDACTICO_SERVICE_CB = "recursoDidacticoServiceCB";

    @Override
    @CircuitBreaker(name = RECURSO_DIDACTICO_SERVICE_CB, fallbackMethod = "fallbackCreateRecursoDidactico")
    public Mono<RecursoDidacticoResponseDTO> createRecursoDidactico(RecursoDidacticoRequestDTO requestDTO) {

        Json jsonMapping = null;
        if (requestDTO.metadata() != null) {
            try {
                jsonMapping = Json.of(objectMapper.writeValueAsString(requestDTO.metadata()));
            } catch (Exception e) {
                log.error("Error al convertir JsonNode de Request a Json de DB: {}", e.getMessage());
                return Mono.error(new RuntimeException("Error al procesar variables Disponibles.", e));
            }
        }

        RecursoDidactico recursoDidactico = RecursoDidactico.builder()
                .nombreArchivo(requestDTO.nombreArchivo())
                .tipoArchivo(requestDTO.tipoArchivo())
                .url(requestDTO.url())
                .fechaSubida(LocalDate.now()) 
                .metadata(jsonMapping)
                .version(requestDTO.version())
                .esActivo(requestDTO.esActivo())
                .usuariosId(requestDTO.usuariosId())
                .build();

        return recursoDidacticoRepository.save(recursoDidactico)
                .map(this::toRecursoDidacticoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<RecursoDidacticoResponseDTO> fallbackCreateRecursoDidactico(
            RecursoDidacticoRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para createRecursoDidactico activado. Causa: {}", t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Recurso Didáctico no está disponible o falló al crear el recurso."));
    }

    @Override
    @CircuitBreaker(name = RECURSO_DIDACTICO_SERVICE_CB, fallbackMethod = "fallbackGetRecursoDidacticoById")
    public Mono<RecursoDidacticoResponseDTO> getRecursoDidacticoById(UUID id) {
        return recursoDidacticoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Recurso Didáctico no encontrado con ID: " + id)))
                .map(this::toRecursoDidacticoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<RecursoDidacticoResponseDTO> fallbackGetRecursoDidacticoById(UUID id, Throwable t) {
        log.error("Fallback para getRecursoDidacticoById activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Recurso Didáctico no está disponible o falló al obtener el recurso."));
    }

    @Override
    @CircuitBreaker(name = RECURSO_DIDACTICO_SERVICE_CB, fallbackMethod = "fallbackGetAllRecursosDidacticos")
    public Flux<RecursoDidacticoResponseDTO> getAllRecursosDidacticos() {
        return recursoDidacticoRepository.findAll()
                .map(this::toRecursoDidacticoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Flux<RecursoDidacticoResponseDTO> fallbackGetAllRecursosDidacticos(Throwable t) {
        log.error("Fallback para getAllRecursosDidacticos activado. Causa: {}", t.getMessage());
        return Flux.empty();
    }

    @Override
    @CircuitBreaker(name = RECURSO_DIDACTICO_SERVICE_CB, fallbackMethod = "fallbackUpdateRecursoDidactico")
    public Mono<RecursoDidacticoResponseDTO> updateRecursoDidactico(UUID id,
            RecursoDidacticoRequestDTO requestDTO) {

        return recursoDidacticoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Recurso Didáctico no encontrado para actualizar con ID: " + id)))
                .flatMap(existingRecurso -> {
                    Json jsonMapping = null;
                    if (requestDTO.metadata() != null) {
                        try {
                            jsonMapping = Json.of(objectMapper.writeValueAsString(requestDTO.metadata()));
                        } catch (Exception e) {
                            log.error("Error al convertir JsonNode de Request a Json de DB: {}", e.getMessage());
                            return Mono.error(new RuntimeException("Error al procesar variables Disponibles.", e));
                        }
                    }

                    existingRecurso.setNombreArchivo(requestDTO.nombreArchivo());
                    existingRecurso.setTipoArchivo(requestDTO.tipoArchivo());
                    existingRecurso.setUrl(requestDTO.url());
                    existingRecurso.setMetadata(jsonMapping);
                    existingRecurso.setVersion(requestDTO.version());
                    existingRecurso.setEsActivo(requestDTO.esActivo());
                    existingRecurso.setUsuariosId(requestDTO.usuariosId()); 
                    return recursoDidacticoRepository.save(existingRecurso);
                })
                .map(this::toRecursoDidacticoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<RecursoDidacticoResponseDTO> fallbackUpdateRecursoDidactico(
            UUID id, RecursoDidacticoRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para updateRecursoDidactico activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Recurso Didáctico no está disponible o falló al actualizar el recurso."));
    }

    @Override
    @CircuitBreaker(name = RECURSO_DIDACTICO_SERVICE_CB, fallbackMethod = "fallbackDeleteRecursoDidactico")
    public Mono<Void> deleteRecursoDidactico(UUID id) {
        return recursoDidacticoRepository.deleteById(id)
                .then(Mono.empty());
    }

    @SuppressWarnings("unused")
    private Mono<Void> fallbackDeleteRecursoDidactico(UUID id, Throwable t) {
        log.error("Fallback para deleteRecursoDidactico activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Recurso Didáctico no está disponible o falló al eliminar el recurso."));
    }

    // --- Método Auxiliar para Mapeo de Dominio a DTO de Respuesta ---
    private RecursoDidacticoResponseDTO toRecursoDidacticoResponseDTO(RecursoDidactico recursoDidactico) {
        JsonNode jsonNodeForResponse = null;
        if (recursoDidactico.getMetadata() != null) {
            try {
                jsonNodeForResponse = objectMapper.readTree(recursoDidactico.getMetadata().asString());
            } catch (Exception e) {
                log.error("Error al convertir Json de DB ({}) a JsonNode para la respuesta: {}",
                          recursoDidactico.getMetadata().asString(), e.getMessage());
                jsonNodeForResponse = objectMapper.createObjectNode(); 
            }
        }
        return new RecursoDidacticoResponseDTO(
            recursoDidactico.getId(),
            recursoDidactico.getNombreArchivo(),
            recursoDidactico.getTipoArchivo(),
            recursoDidactico.getUrl(),
            recursoDidactico.getFechaSubida(),
            jsonNodeForResponse,
            recursoDidactico.getVersion(),
            recursoDidactico.getEsActivo(),
            recursoDidactico.getUsuariosId() 
        );
    }
}
