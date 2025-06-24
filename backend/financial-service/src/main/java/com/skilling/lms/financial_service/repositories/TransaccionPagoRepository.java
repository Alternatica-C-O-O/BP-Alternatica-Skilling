package com.skilling.lms.financial_service.repositories;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.skilling.lms.financial_service.domains.TransaccionPago;
import com.skilling.lms.shared.models.enums.MetodoPagoTipo;
import com.skilling.lms.shared.models.enums.PagoEstado;

import reactor.core.publisher.Flux;

public interface TransaccionPagoRepository extends ReactiveCrudRepository<TransaccionPago, UUID> {

    Flux<TransaccionPago> findByUsuariosId(UUID usuariosId);
    Flux<TransaccionPago> findByPlanPrecioId(UUID planPrecioId);
    Flux<TransaccionPago> findByCursoOfertadoId(UUID cursoOfertadoId);
    Flux<TransaccionPago> findByMetodoPago(MetodoPagoTipo metodoPago);
    Flux<TransaccionPago> findByEstadoPago(PagoEstado estadoPago); 
    Flux<TransaccionPago> findByFechaTransaccionBetween(LocalDateTime start, LocalDateTime end);
}
