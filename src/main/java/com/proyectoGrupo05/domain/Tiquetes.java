package com.proyectoGrupo05.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author jrg71
 */
@Data
@Entity
@Table(name="tiquetes")
public class Tiquetes implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_tiquete")
    private Long idTiquete;
    
    private String titulo;
    private String descripcion;
    private String estado;
    
    @ManyToOne
    @JoinColumn(name="id_usuario")
    public Usuarios usuario;
    
    @ManyToOne
    @JoinColumn(name="id_cliente")
    public Usuarios cliente;
    
    public Tiquetes() {
    }
}
