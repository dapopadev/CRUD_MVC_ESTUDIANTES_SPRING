package com.example.Services;

import java.util.List;

import com.example.Entities.Alumno;

public interface AlumnoService {
    
    public List<Alumno> dameTodosLosAlumnos();
    public Alumno dameUnAlumno(int idAlumno);
    public void eliminarAlumno(int idAlumno);
    public void persistirAlumno(Alumno alumno);
    public void actualizarAlumno(Alumno alumno);



}
