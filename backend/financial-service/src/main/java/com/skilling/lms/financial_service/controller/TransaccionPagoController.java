package com.skilling.lms.financial_service.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.skilling.lms.financial_service.service.TransaccionPagoService;
import com.skilling.lms.shared.dtos.financial.request.TransaccionPagoRequestDTO;
import com.skilling.lms.shared.dtos.financial.response.TransaccionPagoResponseDTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transacciones-pago")
public class TransaccionPagoController {

    private final TransaccionPagoService transaccionPagoService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<TransaccionPagoResponseDTO> createTransaccionPago(@Valid @RequestBody TransaccionPagoRequestDTO requestDTO) {
        return transaccionPagoService.createTransaccionPago(requestDTO);
    }
    
    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public Flux<TransaccionPagoResponseDTO> getAllTransaccionesPago() {
        return transaccionPagoService.getAllTransaccionesPago();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public Mono<TransaccionPagoResponseDTO> getTransaccionPagoById(@PathVariable UUID id) {
        return transaccionPagoService.getTransaccionPagoById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaccion pago no encontrado")));
    }
    
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Mono<TransaccionPagoResponseDTO> updateTransaccionPago(@PathVariable UUID id, @Valid @RequestBody TransaccionPagoRequestDTO requestDTO) {
        return transaccionPagoService.updateTransaccionPago(id, requestDTO)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaccion pago no encontrado para actualizar")));
    }
    
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteTransaccionPago(@PathVariable UUID id) {
        return transaccionPagoService.deleteTransaccionPago(id);
    }
}
