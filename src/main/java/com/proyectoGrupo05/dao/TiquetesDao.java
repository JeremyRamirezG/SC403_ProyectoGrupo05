package com.proyectoGrupo05.dao;

import com.proyectoGrupo05.domain.Tiquetes;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author jrg71
 */
public interface TiquetesDao extends JpaRepository <Tiquetes,Long> {
    @Query(nativeQuery=true,value="SELECT * FROM tiquetes WHERE (id_cliente = :idUsuario OR id_usuario = :idUsuario) AND estado = :status ORDER BY id_tiquete ASC")
    public List<Tiquetes> metodoNativoUsuarios(@Param("idUsuario") long idUsuario, @Param("status") String status);
    
    @Query(nativeQuery=true,value="SELECT * FROM tiquetes WHERE estado = :status ORDER BY id_tiquete ASC")
    public List<Tiquetes> metodoNativoStatus(@Param("status") String status);
}