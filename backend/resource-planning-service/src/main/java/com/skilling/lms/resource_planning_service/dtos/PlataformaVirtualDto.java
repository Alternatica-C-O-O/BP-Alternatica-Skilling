package com.skilling.lms.resource_planning_service.dtos;

import java.util.UUID;

public record PlataformaVirtualDto(
	UUID id, String nombre, String url
) {
}
