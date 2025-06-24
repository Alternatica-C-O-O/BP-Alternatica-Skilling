package com.skilling.lms.shared.models;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.relational.core.mapping.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CursoPrerequisitoId implements Serializable {

    @Column("curso_id")
    private UUID cursoId;
    
    @Column("prerequisito_id")
    private UUID prerequisitoId;
}
