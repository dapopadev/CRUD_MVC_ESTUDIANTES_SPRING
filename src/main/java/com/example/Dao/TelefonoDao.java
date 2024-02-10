package com.example.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Entities.Alumno;
import com.example.Entities.Telefono;

@Repository
public interface TelefonoDao extends JpaRepository<Telefono, Integer> {
    List<Telefono>findByAlumno(Alumno alumno);
    public void deleteByAlumno(Alumno alumno);
}