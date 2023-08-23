package com.proyectoGrupo05.service;

import com.proyectoGrupo05.domain.Tiquetes;
import com.proyectoGrupo05.domain.Usuarios;
import java.util.List;

/**
 *
 * @author jrg71
 */
public interface TiquetesService {

    public List<Tiquetes> getTiquetes();
    public Tiquetes getTiquete(Tiquetes tiquete);
    public void save(Tiquetes tiquetes);
    public void delete(Tiquetes tiquetes);
    public List<Tiquetes> getTiquetesUsuarioMetodoNativo(Usuarios usuario, String status);
    public List<Tiquetes> getTiquetesStatusMetodoNativo(String status);
    
}
