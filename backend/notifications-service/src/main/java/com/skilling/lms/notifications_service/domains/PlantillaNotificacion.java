package com.skilling.lms.notifications_service.domains;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.skilling.lms.shared.models.enums.CanalTipo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("plantilla_notificaciones")
public class PlantillaNotificacion {
    
    @Id
    @Column("id")
    private UUID id;
    
    @Column("nombre_interno")
    private String nombreInterno;
    
    @Column("asunto_plantilla")
    private String asuntoPlantilla;
    
    @Column("cuerpo_plantilla")
    private String cuerpoPlantilla;
    
    @Column("variables_disponibles")
    private String variablesDisponibles;

    @Column("canal_preferido")
    private CanalTipo canalPreferido;
}
