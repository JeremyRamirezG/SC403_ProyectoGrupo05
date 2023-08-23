package com.proyectoGrupo05.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author jrg71
 */
@Data
@Entity
@Table(name="documentacion")
public class Documentacion implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_documento")
    private Long idDocumento;
    
    @NotEmpty
    private String titulo;
    private String descripcion;
    @NotEmpty
    private String contenido;
    private String fechaCreacion;
}
