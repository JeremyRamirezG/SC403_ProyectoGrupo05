package com.proyectoGrupo05.service;

import com.proyectoGrupo05.domain.Documentacion;
import java.util.List;

/**
 *
 * @author jrg71
 */
public interface DocumentacionService {
    
    public List<Documentacion> getDocumentacion();
    public Documentacion getDocumento(Documentacion documento);
    public void save(Documentacion documentacion);
    public void delete(Documentacion documentacion);
}
