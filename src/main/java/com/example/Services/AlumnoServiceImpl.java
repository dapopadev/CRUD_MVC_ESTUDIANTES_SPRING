package com.example.Services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Dao.AlumnoDao;
import com.example.Entities.Alumno;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AlumnoServiceImpl implements AlumnoService{
 @Autowired
    private final AlumnoDao alumnoDao;

@Override
public List<Alumno> dameTodosLosAlumnos() {
   return alumnoDao.findAll();
}

@Override
public Alumno dameUnAlumno(int idAlumno) {
   return alumnoDao.findById(idAlumno).get();
}

@Override
public void eliminarAlumno(int idAlumno) {
  alumnoDao.deleteById(idAlumno);
}

@Override
public void persistirAlumno(Alumno alumno) {
   alumnoDao.save(alumno);
}

@Override
public void actualizarAlumno(Alumno alumno) {
   alumnoDao.save(alumno);
}



}

   
      

