package com.proyectoGrupo05.controller;

import com.proyectoGrupo05.domain.Tiquetes;
import com.proyectoGrupo05.service.TiquetesService;
import com.proyectoGrupo05.service.UsuariosService;
import com.proyectoGrupo05.service.CorreoService;
import jakarta.mail.MessagingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author jrg71
 */

@Controller
public class TiquetesController {
    
    @Autowired
    private TiquetesService tiquetesService;
    @Autowired
    private UsuariosService usuariosService;
    @Autowired
    private CorreoService correoService;
    
    @GetMapping("/index")
    public String listado(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuarioLog = authentication.getName();
        var tiquetesUsuarioAbiertos = tiquetesService.getTiquetesUsuarioMetodoNativo(usuariosService.getUsuarioPorUsername(usuarioLog),"Open");
        var tiquetesUsuarioCerrados = tiquetesService.getTiquetesUsuarioMetodoNativo(usuariosService.getUsuarioPorUsername(usuarioLog),"Close");
        var tiquetesAdminAbiertos = tiquetesService.getTiquetesStatusMetodoNativo("Open");
        var tiquetesAdminCerrados = tiquetesService.getTiquetesStatusMetodoNativo("Close");
        var usuarios = usuariosService.metodoNativoUsuarioPorRoles("ROLE_ING");
        var cliente = usuariosService.getUsuarioPorUsername(usuarioLog);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("cliente", cliente);
        model.addAttribute("tiquetesUsuarioAbiertos", tiquetesUsuarioAbiertos);
        model.addAttribute("tiquetesUsuarioCerrados", tiquetesUsuarioCerrados);
        model.addAttribute("tiquetesAdminAbiertos", tiquetesAdminAbiertos);
        model.addAttribute("tiquetesAdminCerrados", tiquetesAdminCerrados);
        return "index";
    }
    @GetMapping("/verTiquete/{idTiquete}")
    public String vistaPrincipalTiquete(Model model, Tiquetes tiquete) {
        var tiquetes = tiquetesService.getTiquete(tiquete);
        var usuario = usuariosService.getUsuario(tiquetes.getUsuario().getIdUsuario());
        var cliente = usuariosService.getUsuario(tiquetes.getCliente().getIdUsuario());
        var subject = tiquetes.getTitulo()+" - ID: "+tiquetes.getIdTiquete();
        var destinatario = usuariosService.getUsuario(tiquetes.getCliente().getIdUsuario()).getUsername();
        var usrnotificacion = usuariosService.getUsuario(tiquetes.getCliente().getIdUsuario());
        model.addAttribute("tiquetes", tiquetes);
        model.addAttribute("usuario", usuario);
        model.addAttribute("cliente", cliente);
        model.addAttribute("subject", subject);
        model.addAttribute("destinatario", destinatario);
        model.addAttribute("usrnotificacion", usrnotificacion);
        return "vistaTiquete";
    }
    
    @PostMapping("/tiquetes/guardarTiquete")
    public String guardarTiquete(Tiquetes tiquetes){
        tiquetesService.save(tiquetes);
        return "redirect:/";
    }
    @PostMapping("/tiquetes/rearbrirTiquete")
    public String rearbrirTiquete(Tiquetes tiquetes){
        tiquetes.setCliente(tiquetesService.getTiquete(tiquetes).getCliente());
        tiquetes.setUsuario(tiquetesService.getTiquete(tiquetes).getUsuario());
        tiquetes.setTitulo(tiquetesService.getTiquete(tiquetes).getTitulo());
        tiquetes.setDescripcion(tiquetesService.getTiquete(tiquetes).getDescripcion() + "\n\n!Re-open reason: " + tiquetes.getDescripcion());
        tiquetesService.save(tiquetes);
        return "redirect:/";
    }
    @PostMapping("/tiquetes/enviarCorreo/{idTiquete}/enviar")
    public String enviarAccion(HttpServletRequest request, Tiquetes tiquetes, Model model) throws ServletException, MessagingException{
        var tiquete = tiquetesService.getTiquete(tiquetes);
        var subject = tiquete.getTitulo()+" - ID: "+tiquete.getIdTiquete();
        var destinatario = usuariosService.getUsuario(tiquete.getCliente().getIdUsuario()).getUsername();
        var contenido = request.getParameter("contenido");
        correoService.enviarCorreoHtml(destinatario, subject, contenido);
        return "redirect:/verTiquete/{idTiquete}";
    }
    
    @GetMapping("/eliminarTiquete/{idTiquete}")
    public String tiqueteEliminar(Tiquetes tiquetes){
        tiquetesService.delete(tiquetes);
        return "redirect:/";
    }
}
