package com.example.Controllers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Entities.Alumno;
import com.example.Entities.Correo;
import com.example.Entities.Telefono;
import com.example.Services.AlumnoService;
import com.example.Services.CursoService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

    private final AlumnoService alumnoService;
    private final CursoService cursoService;
    private final Logger LOG = Logger.getLogger("MainController");

    // Punto 4.Metodo para ver el listado de empleados
    @GetMapping("/all")
    public String dameAlumnos(Model model) {
      
        model.addAttribute("alumnos",alumnoService.dameTodosLosAlumnos());
        
        return "views/listadoAlumnos";
    
    
        }

    //Punto 5. Metodo para formulario de alta/Matriculacion
    @GetMapping("/frmAltaModificacion")
    
    public String formularioAltaModificacionAlumno(Model model) {

       
        Alumno alumno = new Alumno();

        model.addAttribute("alumno", alumno);


        model.addAttribute("cursos", cursoService.dameCursos());

        return "views/frmAltaModificacionAlumno";
    }

    @PostMapping("/persistir")
    @Transactional
    public String persistirAlumno(@ModelAttribute(name = "empleado")Alumno empleado,
        @RequestParam(name = "numerosTel", required = false) String telefonosRecibidos,
        @RequestParam(name = "direccionesCorreo", required = false) String correosRecibidos) {

    if(telefonosRecibidos != null) {
        String[] arrayTelefonos = telefonosRecibidos.split(";");
        List<String> numerosTelefonos = Arrays.asList(arrayTelefonos);

        List<Telefono> telefonos = new ArrayList<>();

        numerosTelefonos.stream()
            .forEach(numeroTelefono -> {
                telefonos.add(Telefono.builder()
                .numero(numeroTelefono)
                .alumno(alumno)
                .build());
            });

        alumno.setTelefonos(telefonos);


    }
    
    if(correosRecibidos != null) {
            String[] arrayCorreos = correosRecibidos.split(";");
            List<String> direccionesDeCorreo = Arrays.asList(arrayCorreos);

            List<Correo> correos = new ArrayList<>();

            direccionesDeCorreo.stream()
                    .forEach(direccionDeCorreo -> {
                        correos.add(Correo.builder()
                                .correo(direccionDeCorreo)
                                .alumno(alumno)
                                .build());
                    });

            alumno.setCorreos(correos);

    }
        alumnoService.persistirAlumno(alumno);
        return "redirect:/all";
    }

    //Punto 6. Metodo para eliminar alumno
    @GetMapping("/eliminar/{id}")
    @Transactional
    public String eliminarAlumno(@PathVariable(name = "id", required = true) int idAlumno) {
                                    alumnoService.eliminarAlumno(idAlumno);

            return "redirect:/all";
    }
 }

