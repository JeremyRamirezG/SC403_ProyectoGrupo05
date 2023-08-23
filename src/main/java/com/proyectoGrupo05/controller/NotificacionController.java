package com.proyectoGrupo05.controller;

/**
 *
 * @author jrg71
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import com.proyectoGrupo05.domain.Notificacion;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NotificacionController {
    
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    
    @MessageMapping("/private")
    public void sendToSpecificUser(@Payload Notificacion notificacion) {
        simpMessagingTemplate.convertAndSendToUser(notificacion.getTo(), "/specific", notificacion);
    }
    @GetMapping("/notificaciones/notificaciones")
    public String notifiaciones() {
        return "notificaciones/notificaciones";
    }
}
