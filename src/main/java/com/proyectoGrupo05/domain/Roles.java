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
@Table(name="roles")
public class Roles implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRol;
        
    @NotEmpty
    private String nombre;
        
    @Column(name = "id_usuario")
    private Long idUsuario;
}
