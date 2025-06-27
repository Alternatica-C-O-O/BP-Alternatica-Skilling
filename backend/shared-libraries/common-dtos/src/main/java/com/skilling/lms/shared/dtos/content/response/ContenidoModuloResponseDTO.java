package com.skilling.lms.shared.dtos.content.response;

import java.util.UUID;

public record ContenidoModuloResponseDTO(
    UUID id, 
    Integer ordenModulo,
    UUID recursoDidactico,
    UUID modulo  
) {}