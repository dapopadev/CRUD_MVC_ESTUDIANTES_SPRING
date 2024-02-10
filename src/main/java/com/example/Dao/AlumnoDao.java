package com.example.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Entities.Alumno;

@Repository
public interface AlumnoDao extends JpaRepository<Alumno, Integer> {
    List<Alumno> findByNombre(String nombre);
}