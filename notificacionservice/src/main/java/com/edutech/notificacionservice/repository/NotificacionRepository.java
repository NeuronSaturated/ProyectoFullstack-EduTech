package com.edutech.notificacionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.notificacionservice.model.Notificacion;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
    Notificacion findTopByOrderByIdDesc();
} 