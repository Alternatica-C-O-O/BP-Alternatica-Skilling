package com.skilling.lms.resource_planning_service.dtos;

import java.util.UUID;

public record EspacioFisicoDto(
	UUID id, 
	String nombre, 
	String ubicacion
) {}
