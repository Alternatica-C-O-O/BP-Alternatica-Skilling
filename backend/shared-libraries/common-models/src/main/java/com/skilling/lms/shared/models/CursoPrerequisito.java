package com.skilling.lms.shared.models;

import java.util.UUID;

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
@Table("curso_prerequisito")
public class CursoPrerequisito {

    @Column("curso_id")
    private UUID cursoId;
    
    @Column("prerequisito_id")
    private UUID prerequisitoId;
}
