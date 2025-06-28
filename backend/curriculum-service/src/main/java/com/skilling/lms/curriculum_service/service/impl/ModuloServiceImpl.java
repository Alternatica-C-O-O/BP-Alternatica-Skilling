package com.skilling.lms.curriculum_service.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.skilling.lms.curriculum_service.domains.Modulo;
import com.skilling.lms.curriculum_service.repositories.ModuloRepository;
import com.skilling.lms.curriculum_service.service.ModuloService;
import com.skilling.lms.shared.dtos.curriculum.request.ModuloRequestDTO;
import com.skilling.lms.shared.dtos.curriculum.response.ModuloResponseDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class ModuloServiceImpl implements ModuloService {

    private final ModuloRepository moduloRepository;

    private static final String MODULO_SERVICE_CB = "moduloServiceCB";

    @Override
    @CircuitBreaker(name = MODULO_SERVICE_CB, fallbackMethod = "fallbackCreateModulo")
    public Mono<ModuloResponseDTO> createModulo(ModuloRequestDTO requestDTO) {
        return moduloRepository.findByNombreModuloAndCursoOfertadoId(requestDTO.nombreModulo(), requestDTO.cursoOfertadoId())
                .flatMap(existingModulo -> {
                    log.warn("Intento de crear módulo duplicado por nombre: Nombre '{}' en Curso ID '{}'",
                            requestDTO.nombreModulo(), requestDTO.cursoOfertadoId());
                    return Mono.error(new RuntimeException("Ya existe un módulo con este nombre para el curso ofertado."));
                })
                .then(Mono.defer(() ->
                    moduloRepository.findByOrdenAndCursoOfertadoId(requestDTO.orden(), requestDTO.cursoOfertadoId())
                        .flatMap(existingModulo -> {
                            log.warn("Intento de crear módulo duplicado por orden: Orden '{}' en Curso ID '{}'",
                                    requestDTO.orden(), requestDTO.cursoOfertadoId());
                            return Mono.error(new RuntimeException("Ya existe un módulo con este orden para el curso ofertado."));
                        })
                        .then(Mono.defer(() -> {
                            Modulo modulo = Modulo.builder()
                                    .nombreModulo(requestDTO.nombreModulo())
                                    .orden(requestDTO.orden())
                                    .descripcion(requestDTO.descripcion())
                                    .objetivosAprendizaje(requestDTO.objetivosAprendizaje())
                                    .cursoOfertadoId(requestDTO.cursoOfertadoId())
                                    .build();

                            return moduloRepository.save(modulo)
                                    .map(this::toModuloResponseDTO);
                        }))
                ));
    }

    @SuppressWarnings("unused")
    private Mono<ModuloResponseDTO> fallbackCreateModulo(
            ModuloRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para createModulo activado. Causa: {}", t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Módulos no está disponible o falló al crear el módulo."));
    }

    @Override
    @CircuitBreaker(name = MODULO_SERVICE_CB, fallbackMethod = "fallbackGetModuloById")
    public Mono<ModuloResponseDTO> getModuloById(UUID id) {
        return moduloRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Módulo no encontrado con ID: " + id)))
                .map(this::toModuloResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<ModuloResponseDTO> fallbackGetModuloById(UUID id, Throwable t) {
        log.error("Fallback para getModuloById activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Módulos no está disponible o falló al obtener el módulo."));
    }

    @Override
    @CircuitBreaker(name = MODULO_SERVICE_CB, fallbackMethod = "fallbackGetAllModulos")
    public Flux<ModuloResponseDTO> getAllModulos() {
        return moduloRepository.findAll()
                .map(this::toModuloResponseDTO);
    }

    @SuppressWarnings("unused")
    private Flux<ModuloResponseDTO> fallbackGetAllModulos(Throwable t) {
        log.error("Fallback para getAllModulos activado. Causa: {}", t.getMessage());
        return Flux.empty();
    }

    @Override
    @CircuitBreaker(name = MODULO_SERVICE_CB, fallbackMethod = "fallbackUpdateModulo")
    public Mono<ModuloResponseDTO> updateModulo(UUID id, ModuloRequestDTO requestDTO) {
        return moduloRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Módulo no encontrado para actualizar con ID: " + id)))
                .flatMap(existingModulo -> {
                    Mono<Void> checkDuplicate = Mono.empty();

                    if (!existingModulo.getNombreModulo().equals(requestDTO.nombreModulo())) {
                        checkDuplicate = checkDuplicate.then(moduloRepository.findByNombreModuloAndCursoOfertadoId(requestDTO.nombreModulo(), requestDTO.cursoOfertadoId())
                            .flatMap(m -> Mono.error(new RuntimeException("Ya existe otro módulo con el nombre '" + requestDTO.nombreModulo() + "' en este curso.")))
                            .then());
                    }

                    if (!existingModulo.getOrden().equals(requestDTO.orden())) {
                        checkDuplicate = checkDuplicate.then(moduloRepository.findByOrdenAndCursoOfertadoId(requestDTO.orden(), requestDTO.cursoOfertadoId())
                            .flatMap(m -> Mono.error(new RuntimeException("Ya existe otro módulo con el orden '" + requestDTO.orden() + "' en este curso.")))
                            .then());
                    }

                    return checkDuplicate.then(Mono.defer(() -> {
                        existingModulo.setNombreModulo(requestDTO.nombreModulo());
                        existingModulo.setOrden(requestDTO.orden());
                        existingModulo.setDescripcion(requestDTO.descripcion());
                        existingModulo.setObjetivosAprendizaje(requestDTO.objetivosAprendizaje());
                        existingModulo.setCursoOfertadoId(requestDTO.cursoOfertadoId()); 

                        return moduloRepository.save(existingModulo);
                    }));
                })
                .map(this::toModuloResponseDTO);
    }

    @SuppressWarnings("unused")
    private Mono<ModuloResponseDTO> fallbackUpdateModulo(
            UUID id, ModuloRequestDTO requestDTO, Throwable t) {
        log.error("Fallback para updateModulo activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Módulos no está disponible o falló al actualizar el módulo."));
    }

    @Override
    @CircuitBreaker(name = MODULO_SERVICE_CB, fallbackMethod = "fallbackDeleteModulo")
    public Mono<Void> deleteModulo(UUID id) {
        return moduloRepository.deleteById(id)
                .then(Mono.empty());
    }

    @SuppressWarnings("unused")
    private Mono<Void> fallbackDeleteModulo(UUID id, Throwable t) {
        log.error("Fallback para deleteModulo activado para ID {}. Causa: {}", id, t.getMessage());
        return Mono.error(new RuntimeException("El servicio de Módulos no está disponible o falló al eliminar el módulo."));
    }

    private ModuloResponseDTO toModuloResponseDTO(Modulo modulo) {
        return new ModuloResponseDTO(
            modulo.getId(),
            modulo.getNombreModulo(),
            modulo.getOrden(),
            modulo.getDescripcion(),
            modulo.getObjetivosAprendizaje(),
            modulo.getCursoOfertadoId()
        );
    }
}
