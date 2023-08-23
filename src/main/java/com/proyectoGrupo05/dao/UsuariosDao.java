package com.proyectoGrupo05.dao;

import com.proyectoGrupo05.domain.Usuarios;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author jrg71
 */
public interface UsuariosDao extends JpaRepository <Usuarios,Long> {
    Usuarios findByUsername(String username);
    Usuarios findByUsernameAndPassword(String username, String Password);
    boolean existsByUsername(String username);
    
    @Query(nativeQuery=true,value="SELECT usuarios.id_usuario, usuarios.nombre, usuarios.apellido, usuarios.username, usuarios.password FROM usuarios INNER JOIN roles ON usuarios.id_usuario = roles.id_usuario WHERE roles.nombre = :rol")
    public List<Usuarios> metodoNativoUsuarioPorRoles(@Param("rol") String rol); 
}