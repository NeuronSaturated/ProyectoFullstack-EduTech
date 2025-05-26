package com.edutech.notificacionservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.notificacionservice.model.Notificacion;
import com.edutech.notificacionservice.model.NotificacionDTO;
import com.edutech.notificacionservice.repository.NotificacionRepository;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    public List<Notificacion> listar() {
        return notificacionRepository.findAll();
    }

    public Notificacion crear(Notificacion notificacion) {
        return notificacionRepository.save(notificacion); // Sin setFechaEnvio()
    }

    public NotificacionDTO obtenerUltima() {
        Notificacion ultima = notificacionRepository.findTopByOrderByIdDesc();

        if (ultima == null) return null;

        NotificacionDTO dto = new NotificacionDTO();
        dto.setMensaje(ultima.getMensaje());
        dto.setDestinatario(ultima.getDestinatario());
        dto.setFechaEnvio(ultima.getFechaEnvio().toString());

        return dto;
    }


    public void eliminar(Long id) {
        notificacionRepository.deleteById(id);
    }

    public void eliminarTodos() {
        notificacionRepository.deleteAll();
    }

}
