package com.example.Services;

import java.util.List;
import com.example.Entities.Curso;

public interface CursoService {
    public List<Curso> dameCursos();
    public Curso dameUnCurso(int idCurso);
    public void persistirCurso(Curso curso);
    
}
