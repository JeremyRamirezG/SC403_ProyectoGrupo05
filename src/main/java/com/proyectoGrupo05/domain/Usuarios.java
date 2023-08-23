package com.proyectoGrupo05.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 *
 * @author jrg71
 */
@Entity
@Data
@Table(name="usuarios")
public class Usuarios implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private Long idUsuario;
    
    @NotEmpty
    private String nombre;
    private String apellido;
    
    @NotEmpty
    private String username;
    
    @NotEmpty
    private String password;
    
    @OneToMany
    @JoinColumn(name="id_usuario")
    private List<Roles> roles;
    
    @OneToMany
    @JoinColumn(name="id_usuario")
    private List<Tiquetes> tiquetesUsuario;
    
    @OneToMany
    @JoinColumn(name="id_cliente")
    private List<Tiquetes> tiquetesCliente;
    
    public Usuarios() {
    }
    
}
