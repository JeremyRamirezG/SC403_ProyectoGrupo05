package com.proyectoGrupo05.service;

/**
 *
 * @author jrg71
 */
import jakarta.mail.MessagingException;

public interface CorreoService {
    public void enviarCorreoHtml(String para,String asunto,String contenidoHtml)throws MessagingException;
}

