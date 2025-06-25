package com.skilling.lms.content_service.service.imp;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.skilling.lms.shared.dtos.create.RecursoDidacticoCreateRequestDTO;
import com.skilling.lms.shared.dtos.recurso_didactico.request.RecursoDidacticoRequestDTO;
import com.skilling.lms.shared.dtos.recurso_didactico.response.RecursoDidacticoResponseDTO;
import com.skilling.lms.shared.models.enums.ArchivoTipo;
import com.skilling.lms.content_service.domains.RecursoDidactico;
import com.skilling.lms.content_service.repositories.RecursoDidacticoRepository;
import com.skilling.lms.content_service.service.RecursoDidacticoService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class RecursoDidacticoServiceImpl implements RecursoDidacticoService {

    private final RecursoDidacticoRepository recursoRepository;

    private static final String RECURSO_SERVICE_CB = "recursoServiceCB";

    @Override
    @CircuitBreaker(name = RECURSO_SERVICE_CB, fallbackMethod = "fallbackCreateRecurso")
    public Mono<RecursoDidacticoResponseDTO> createRecurso(RecursoDidacticoCreateRequestDTO requestDTO) {
        RecursoDidactico recurso = RecursoDidactico.builder()
                .id(UUID.randomUUID())
                .nombreArchivo(requestDTO.nombreArchivo())
                .tipoArchivo(requestDTO.tipoArchivo())
                .url(requestDTO.url())
                .fechaSubida(LocalDate.now())
                .metadata(requestDTO.metadata())
                .version(1)
                .esActivo(true)
                .usuariosId(requestDTO.usuariosId())
                .build();

        return recursoRepository.save(recurso)
                .map(this::toRecursoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<RecursoDidacticoResponseDTO> fallbackCreateRecurso(RecursoDidacticoCreateRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para createRecurso activado. Causa: {}", t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Recursos Didácticos no está disponible o falló al crear el recurso."));
    }

    @Override
    @CircuitBreaker(name = RECURSO_SERVICE_CB, fallbackMethod = "fallbackGetRecursoById")
    public Mono<RecursoDidacticoResponseDTO> getRecursoById(UUID id) {
        return recursoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Recurso no encontrado con ID: " + id)))
                .map(this::toRecursoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<RecursoDidacticoResponseDTO> fallbackGetRecursoById(UUID id, Throwable t) {
        log.error("Fallback para getRecursoById activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Recursos Didácticos no está disponible o falló al obtener el recurso."));
    }

    @Override
    @CircuitBreaker(name = RECURSO_SERVICE_CB, fallbackMethod = "fallbackGetAllRecursos")
    public Flux<RecursoDidacticoResponseDTO> getAllRecursos() {
        return recursoRepository.findAll()
                .map(this::toRecursoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Flux<RecursoDidacticoResponseDTO> fallbackGetAllRecursos(Throwable t) {
        log.error("Fallback para getAllRecursos activado. Causa: {}", t.getMessage());
        return Flux.error(new RuntimeException("El servicio de Recursos Didácticos no está disponible o falló al obtener los recursos."));
    }

    @Override
    @CircuitBreaker(name = RECURSO_SERVICE_CB, fallbackMethod = "fallbackGetRecursosByUsuario")
    public Flux<RecursoDidacticoResponseDTO> getRecursosByUsuario(UUID usuariosId) {
        return recursoRepository.findByUsuariosId(usuariosId)
                .switchIfEmpty(Mono.error(new RuntimeException("No se encontraron recursos para el usuario con ID: " + usuariosId)))
                .map(this::toRecursoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Flux<RecursoDidacticoResponseDTO> fallbackGetRecursosByUsuario(UUID usuariosId, Throwable t) {
        log.error("Fallback para getRecursosByUsuario activado para usuario ID {}. Causa: {}", usuariosId, t.getMessage());
        return Flux.error(new RuntimeException("El servicio de Recursos Didácticos no está disponible o falló al obtener los recursos por usuario."));
    }

    @Override
    @CircuitBreaker(name = RECURSO_SERVICE_CB, fallbackMethod = "fallbackGetRecursosByTipo")
    public Flux<RecursoDidacticoResponseDTO> getRecursosByTipo(String tipo) {
        ArchivoTipo tipoArchivo = ArchivoTipo.fromValue(tipo);
        return recursoRepository.findByTipoArchivo(tipoArchivo)
                .switchIfEmpty(Mono.error(new RuntimeException("No se encontraron recursos del tipo: " + tipo)))
                .map(this::toRecursoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Flux<RecursoDidacticoResponseDTO> fallbackGetRecursosByTipo(String tipo, Throwable t) {
        log.error("Fallback para getRecursosByTipo activado para tipo {}. Causa: {}", tipo, t.getMessage());
        return Flux.error(new RuntimeException("El servicio de Recursos Didácticos no está disponible o falló al obtener los recursos por tipo."));
    }

    @Override
    @CircuitBreaker(name = RECURSO_SERVICE_CB, fallbackMethod = "fallbackUpdateRecurso")
    public Mono<RecursoDidacticoResponseDTO> updateRecurso(UUID id, RecursoDidacticoRequestDTO requestDTO) {
        return recursoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Recurso no encontrado para actualizar con ID: " + id)))
                .flatMap(existingRecurso -> {
                    BeanUtils.copyProperties(requestDTO, existingRecurso, "id", "fechaSubida");
                    existingRecurso.setVersion(existingRecurso.getVersion() + 1);
                    return recursoRepository.save(existingRecurso);
                })
                .map(this::toRecursoResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<RecursoDidacticoResponseDTO> fallbackUpdateRecurso(UUID id, RecursoDidacticoRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para updateRecurso activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Recursos Didácticos no está disponible o falló al actualizar el recurso."));
    }

    @Override
    @CircuitBreaker(name = RECURSO_SERVICE_CB, fallbackMethod = "fallbackDeleteRecurso")
    public Mono<Void> deleteRecurso(UUID id) {
        return recursoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Recurso no encontrado para eliminar con ID: " + id)))
                .flatMap(recurso -> {
                    recurso.setEsActivo(false);
                    return recursoRepository.save(recurso);
                })
                .then();
    }

    @SuppressWarnings("unused")
    private Mono<Void> fallbackDeleteRecurso(UUID id, Throwable t) {
        log.error("Fallback para deleteRecurso activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Recursos Didácticos no está disponible o falló al eliminar el recurso."));
    }

    private RecursoDidacticoResponseDTO toRecursoResponseDTO(RecursoDidactico recurso) {
        return new RecursoDidacticoResponseDTO(
                recurso.getId(),
                recurso.getNombreArchivo(),
                recurso.getTipoArchivo().name(),
                recurso.getUrl(),
                recurso.getFechaSubida(),
                recurso.getMetadata(),
                recurso.getVersion(),
                recurso.getEsActivo(),
                recurso.getUsuariosId()
        );
    }
}