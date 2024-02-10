package com.example.Services;

import org.springframework.stereotype.Service;
import com.example.Dao.AlumnoDao;
import com.example.Dao.TelefonoDao;
import com.example.Entities.Telefono;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TelefonoServiceImpl implements TelefonoService{
    
    private final AlumnoDao alumnoDao;
    private final TelefonoDao telefonoDao;
    @Override
    public void persistirTelefono(int idAlumno, Telefono telefono) {
       telefono.setAlumno(alumnoDao.findById(idAlumno).get());
       telefonoDao.save(telefono);
    }
    
    
    
    
}
