package com.proyectoGrupo05.service.impl;

import com.proyectoGrupo05.domain.Usuarios;
import com.proyectoGrupo05.dao.TiquetesDao;
import com.proyectoGrupo05.domain.Tiquetes;
import com.proyectoGrupo05.service.TiquetesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jrg71
 */

@Service
public class TiquetesServiceImpl implements TiquetesService{
    @Autowired
    private TiquetesDao tiquetesDao;

    @Override
    @Transactional(readOnly=true)
    public List<Tiquetes> getTiquetes() {
        return tiquetesDao.findAll();
    }
    @Override
    @Transactional(readOnly=true)
    public Tiquetes getTiquete(Tiquetes tiquete){
        return tiquetesDao.findById(tiquete.getIdTiquete()).orElse(null);
    }
        
    @Override
    public void save(Tiquetes tiquetes) {
        tiquetesDao.save(tiquetes);
    }

    @Override
    public void delete(Tiquetes tiquetes) {
        tiquetesDao.delete(tiquetes);    
    }

    @Override
    @Transactional(readOnly=true)
    public List<Tiquetes> getTiquetesUsuarioMetodoNativo(Usuarios usuario, String status) {
        return tiquetesDao.metodoNativoUsuarios(usuario.getIdUsuario(), status);
    }
    
    @Override
    @Transactional(readOnly=true)
    public List<Tiquetes> getTiquetesStatusMetodoNativo(String status) {
        return tiquetesDao.metodoNativoStatus(status);
    }
    
    
}
