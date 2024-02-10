package com.example.Services;


import org.springframework.stereotype.Service;
import com.example.Dao.AlumnoDao;
import com.example.Dao.CorreoDao;
import com.example.Entities.Correo;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CorreoServiceImpl implements CorreoService{
    
    private final AlumnoDao alumnoDao;
    private final CorreoDao correoDao;
    @Override
    public void persistirCorreo(int idAlumno, Correo correo) {
       correo.setAlumno(alumnoDao.findById(idAlumno).get());
       correoDao.save(correo);
    }




}