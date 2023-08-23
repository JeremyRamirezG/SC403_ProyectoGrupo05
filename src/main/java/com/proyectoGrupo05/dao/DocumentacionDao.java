package com.proyectoGrupo05.dao;

import com.proyectoGrupo05.domain.Documentacion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jrg71
 */
public interface DocumentacionDao extends JpaRepository <Documentacion,Long> {
    
}
