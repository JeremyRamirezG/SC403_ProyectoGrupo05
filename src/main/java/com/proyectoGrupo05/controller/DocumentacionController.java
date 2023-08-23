package com.proyectoGrupo05.controller;

import com.proyectoGrupo05.domain.Documentacion;
import com.proyectoGrupo05.service.DocumentacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author jrg71
 */
@Controller
@RequestMapping("/documentacion")
public class DocumentacionController {
    
    @Autowired
    private DocumentacionService documentacionService;
    
    @GetMapping("/listado")
    public String listado(Model model) {
        var documentacion = documentacionService.getDocumentacion();
        model.addAttribute("documentacion", documentacion);
        return "documentacion/listado";
    }
    @GetMapping("/verDocumento/{idDocumento}")
    public String vistaPrincipalDocumentacion(Model model, Documentacion documento) {
        var documentacion = documentacionService.getDocumento(documento);
        model.addAttribute("documentacion", documentacion);
        return "documentacion/vistaDocumentacion";
    }
    
    @PostMapping("/agregarDocumento")
    public String guardarDocumento(Documentacion documentacion){
        documentacionService.save(documentacion);
        return "redirect:/documentacion/listado";
    }
    
    @GetMapping("/eliminarDocumento/{idDocumento}")
    public String documentacionEliminar(Documentacion documentacion){
        documentacionService.delete(documentacion);
        return "redirect:/documentacion/listado";
    }
}
