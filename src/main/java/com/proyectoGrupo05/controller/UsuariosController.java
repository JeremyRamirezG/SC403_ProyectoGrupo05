package com.proyectoGrupo05.controller;

import com.proyectoGrupo05.service.UsuariosService;
import com.proyectoGrupo05.domain.Usuarios;
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
@RequestMapping("/usuarios")
public class UsuariosController {
    
    @Autowired
    private UsuariosService usuariosService;
    
    @GetMapping("/listado")
    public String listado(Model model) {
        var usuarios = usuariosService.getUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuarios/listado";
    }
    @GetMapping("/eliminarUsuario/{idUsuario}")
    public String usuariosEliminar(Usuarios usuarios){
        usuariosService.delete(usuarios);
        return "redirect:/usuarios/listado";
    }
    @PostMapping("/guardarUsuario")
    public String guardarUsuario(Usuarios usuarios){
        usuariosService.save(usuarios, true);
        return "redirect:/usuarios/listado";
    }
}
