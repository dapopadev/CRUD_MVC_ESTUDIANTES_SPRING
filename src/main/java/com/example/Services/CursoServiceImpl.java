package com.example.Services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.Dao.CursoDao;
import com.example.Entities.Curso;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {

    
      private final CursoDao cursoDao; 
    
      @Override
    public List<Curso> dameCursos() {
       return cursoDao.findAll();
    }

    @Override
    public Curso dameUnCurso(int idCurso) {
       return cursoDao.findById(idCurso).get();
    }

    
   @Override
   public void persistirCurso(Curso curso) {
      cursoDao.save(curso);
   }

}
