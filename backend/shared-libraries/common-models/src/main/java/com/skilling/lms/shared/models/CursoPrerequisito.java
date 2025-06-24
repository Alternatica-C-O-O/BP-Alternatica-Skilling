package com.skilling.lms.shared.models;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CursoPrerequisito implements Serializable {

    @Id
    private CursoPrerequisitoId id;

    // @Transient private CursoOfertado curso; // El curso principal
    // @Transient private CursoOfertado prerequisito; // El curso que es prerequisito
}
