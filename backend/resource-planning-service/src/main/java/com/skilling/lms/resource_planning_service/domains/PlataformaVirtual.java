package com.skilling.lms.resource_planning_service.domains;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.skilling.lms.shared.models.enums.PlataformaVirtualTipo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("plataforma_virtual")
public class PlataformaVirtual {

    @Id
    @Column("id")
    private UUID id;

    @Column("nombre_plataforma")
    private String nombrePlataforma;

    private String url;
    private PlataformaVirtualTipo tipo;

    @Column("credenciales_api")
    private String credencialesApi; // Podría ser JSON o un String cifrado
}
