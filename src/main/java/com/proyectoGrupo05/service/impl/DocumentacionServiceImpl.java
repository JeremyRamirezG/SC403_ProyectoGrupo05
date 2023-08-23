package com.proyectoGrupo05.service.impl;

import com.proyectoGrupo05.dao.DocumentacionDao;
import com.proyectoGrupo05.domain.Documentacion;
import com.proyectoGrupo05.service.DocumentacionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jrg71
 */
@Service
public class DocumentacionServiceImpl implements DocumentacionService {
    @Autowired
    private DocumentacionDao documentacionDao;
    
    @Override
    @Transactional(readOnly=true)
    public List<Documentacion> getDocumentacion() {
        return documentacionDao.findAll();
    }
    @Override
    @Transactional(readOnly=true)
    public Documentacion getDocumento(Documentacion documento){
        System.out.println(documento.getIdDocumento());
        return documentacionDao.findById(documento.getIdDocumento()).orElse(null);
    }
        
    @Override
    public void save(Documentacion documentacion) {
        documentacionDao.save(documentacion);
    }

    @Override
    public void delete(Documentacion documentacion) {
        documentacionDao.delete(documentacion);    
    }
}
