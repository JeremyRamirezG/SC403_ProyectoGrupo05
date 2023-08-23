package com.proyectoGrupo05.controller;

import com.proyectoGrupo05.domain.Documentacion;
import com.proyectoGrupo05.domain.Usuarios;
import com.proyectoGrupo05.domain.Tiquetes;
import com.proyectoGrupo05.service.DocumentacionService;
import com.proyectoGrupo05.service.UsuariosService;
import com.proyectoGrupo05.service.TiquetesService;
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
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author jrg71
 */
@Controller
@RequestMapping("/pruebas")
public class PruebasController {
    
    @Autowired
    private TiquetesService tiquetesService;
    @Autowired
    private UsuariosService usuariosService;
    @Autowired
    private DocumentacionService documentacionService;
    @Autowired
    private CorreoService correoService;
    
    //Listado general
    @GetMapping("/listado")
    public String listado(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuarioLog = authentication.getName();
        var tiquetesUsuario = tiquetesService.getTiquetesUsuarioMetodoNativo(usuariosService.getUsuarioPorUsername(usuarioLog), "Open");
        
        var tiquetes = tiquetesService.getTiquetes();
        var usuarios = usuariosService.getUsuarios();
        var documentacion = documentacionService.getDocumentacion();
        
        model.addAttribute("tiquetes", tiquetes);
        model.addAttribute("tiquetesUsuario", tiquetesUsuario);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("documentacion", documentacion);
        return "pruebas/listado";
    }
    
    //Listado especifico
    @GetMapping("/listado/tiquete/{idTiquete}")
    public String vistaPrincipalTiquete(Model model, Tiquetes tiquete) {
        var tiquetes = tiquetesService.getTiquete(tiquete);
        var usuario = usuariosService.getUsuario(tiquetes.getUsuario().getIdUsuario());
        var cliente = usuariosService.getUsuario(tiquetes.getCliente().getIdUsuario());
        model.addAttribute("tiquetes", tiquetes);
        model.addAttribute("usuario", usuario);
        model.addAttribute("cliente", cliente);
        return "pruebas/vistaPrincipalTiquete";
    }
    @GetMapping("/listado/documento/{idDocumento}")
    public String vistaPrincipalDocumentacion(Model model, Documentacion documento) {
        var documentacion = documentacionService.getDocumento(documento);
        model.addAttribute("documentacion", documentacion);
        return "pruebas/vistaPrincipalDocumentacion";
    }
    
    //Agregar data
    @GetMapping("/agregarUsuario")
    public String agregarUsuario(){
        return "pruebas/agregarUsuario";
    }
    @PostMapping("/guardarUsuario")
    public String guardarUsuario(Usuarios usuarios, String rol){
        usuariosService.save(usuarios, true);
        return "redirect:/pruebas/listado";
    }
    
    @GetMapping("/agregarDocumentacion")
    public String agregarDocumento(){
        return "pruebas/agregarDocumentacion";
    }
    @PostMapping("/guardarDocumentacion")
    public String guardarDocumento(Documentacion documentacion){
        documentacionService.save(documentacion);
        return "redirect:/pruebas/listado";
    }
    
    @GetMapping("/agregarTiquete")
    public String agregarTiquete(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usuarioLog = authentication.getName();
        var usuarios = usuariosService.metodoNativoUsuarioPorRoles("ROLE_ING");
        var cliente = usuariosService.getUsuarioPorUsername(usuarioLog);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("cliente", cliente);
        return "pruebas/agregarTiquete";
    }
    @PostMapping("/guardarTiquete")
    public String guardarTiquete(Tiquetes tiquetes){
        tiquetesService.save(tiquetes);
        return "redirect:/pruebas/listado";
    }
    
    //Borrar data
    @GetMapping("/eliminarTiquete/{idTiquete}")
    public String tiqueteEliminar(Tiquetes tiquetes){
        tiquetesService.delete(tiquetes);
        return "redirect:/pruebas/listado";
    }
    @GetMapping("/eliminarUsuario/{idUsuario}")
    public String usuariosEliminar(Usuarios usuarios){
        System.out.println("ID: "+usuarios.getIdUsuario());
        usuariosService.delete(usuarios);
        return "redirect:/pruebas/listado";
    }
    @GetMapping("/eliminarDocumento/{idDocumento}")
    public String documentacionEliminar(Documentacion documentacion){
        documentacionService.delete(documentacion);
        return "redirect:/pruebas/listado";
    }
    
    //Modificar data
    @GetMapping("/modificarTiquete/{idTiquete}")
    public String tiquetesModificar(Tiquetes tiquetes, Model model){
        tiquetes = tiquetesService.getTiquete(tiquetes);
        var usuarios = usuariosService.metodoNativoUsuarioPorRoles("ROLE_ING");
        var clientes = usuariosService.metodoNativoUsuarioPorRoles("ROLE_CUS");
        var usrnotificacion = usuariosService.getUsuario(tiquetes.getCliente().getIdUsuario());
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("clientes", clientes);
        model.addAttribute("tiquetes", tiquetes);
        model.addAttribute("usrnotificacion", usrnotificacion);
        return "pruebas/modificarTiquete";
    }
    @GetMapping("/modificarDocumento/{idDocumento}")
    public String documentacionModificar(Documentacion documentacion, Model model){
        documentacion = documentacionService.getDocumento(documentacion);
        model.addAttribute("documentacion", documentacion);
        return "pruebas/modificarDocumento";
    }
    @GetMapping("/modificarUsuario/{idUsuario}")
    public String usuariosModificar(Usuarios usuarios, Model model){
        usuarios = usuariosService.getUsuario(usuarios.getIdUsuario());
        model.addAttribute("usuarios", usuarios);
        return "pruebas/modificarUsuario";
    }
    
    //Emails
    @GetMapping("/enviarCorreo/{idTiquete}")
    public String enviarCorreo(Tiquetes tiquetes, Model model){
        var tiquete = tiquetesService.getTiquete(tiquetes);
        var subject = tiquete.getTitulo()+" - ID: "+tiquete.getIdTiquete();
        var destinatario = usuariosService.getUsuario(tiquete.getCliente().getIdUsuario());
        model.addAttribute("tiquete", tiquete);
        model.addAttribute("destinatario", destinatario);
        model.addAttribute("subject", subject);
        return "pruebas/enviarCorreo";
    }
    @PostMapping("/enviarCorreo/{idTiquete}/enviar")
    public String enviarAccion(HttpServletRequest request, Tiquetes tiquetes, Model model) throws ServletException, MessagingException{
        var tiquete = tiquetesService.getTiquete(tiquetes);
        var subject = tiquete.getTitulo()+" - ID: "+tiquete.getIdTiquete();
        var destinatario = usuariosService.getUsuario(tiquete.getCliente().getIdUsuario()).getUsername();
        var contenido = request.getParameter("contenido");
        System.out.println("destino: "+destinatario+subject+contenido);
        correoService.enviarCorreoHtml(destinatario, subject, contenido);
        return "redirect:/pruebas/listado/tiquete/{idTiquete}";
    }
}
