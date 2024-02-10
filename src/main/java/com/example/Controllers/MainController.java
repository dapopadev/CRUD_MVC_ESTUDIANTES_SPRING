package com.example.Controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.Entities.Alumno;
import com.example.Entities.Correo;
import com.example.Entities.Curso;
import com.example.Entities.Telefono;
import com.example.Services.AlumnoService;
import com.example.Services.CursoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

    private final AlumnoService alumnoService;
    private final Logger LOG = Logger.getLogger("MainController");

    @GetMapping("/all")
    public String dameAlumnos(Model model) {
      
        model.addAttribute("alumnos",alumnoService.dameTodosLosAlumnos());
        
        return "views/listadoAlumnos";
    
    
        }

    @GetMapping("/detalles{id}")
    public String detallesAlumno(@PathVariable(name ="id")int idAlumno,Model model) {

        LOG.info("ID Alumno Recibido: " + idAlumno);

        model.addAttribute("alumno", alumnoService.dameUnAlumno(idAlumno));
        return "views/alumnoDetalles";
    }
    
    @GetMapping("/frmAltaModificacion")
    public String formularioAltaModificacionAlumno(Model model) {

        
        return "views/frmAltaModificacionAlumno";
    }
    
}