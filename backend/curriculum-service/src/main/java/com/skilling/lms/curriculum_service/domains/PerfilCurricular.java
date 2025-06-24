package com.skilling.lms.curriculum_service.domains;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("perfil_curricular")
public class PerfilCurricular {

    @Id
    @Column("id")
    private UUID id;
    
    @Column("nombre_perfil")
    private String nombrePerfil;
    
    private String descripcion;

    @Column("modelo_educativo_id")
    private UUID modeloEducativoId; // Foreign key
}
