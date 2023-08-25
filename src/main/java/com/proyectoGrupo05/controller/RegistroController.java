package com.proyectoGrupo05.controller;

/**
 *
 * @author jrg71
 */
import com.proyectoGrupo05.service.UsuariosService;
import com.proyectoGrupo05.domain.Usuarios;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/registro")
public class RegistroController {
    @Autowired
    private UsuariosService usuariosService;
    
    @GetMapping("/nuevo")
    public String nuevo(Model model, Usuarios usuario) {
        return "registro/nuevo";
    }
    @PostMapping("/crearUsuario")
    public String crearUsuario(Usuarios usuarios, String rol)throws MessagingException {
        usuariosService.save(usuarios, true);
        return "redirect:/login";
    }
}
