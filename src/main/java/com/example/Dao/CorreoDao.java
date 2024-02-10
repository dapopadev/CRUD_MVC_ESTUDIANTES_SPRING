package com.example.Dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Entities.Alumno;
import com.example.Entities.Correo;


@Repository
public interface CorreoDao extends JpaRepository<Correo, Integer> {
    List<Correo>findByAlumno(Alumno alumno);
    public void deleteByAlumno(Alumno alumno);
}
