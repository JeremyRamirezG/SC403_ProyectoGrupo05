package com.proyectoGrupo05.dao;

import com.proyectoGrupo05.domain.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesDao extends JpaRepository<Roles, Long> {
    
}
