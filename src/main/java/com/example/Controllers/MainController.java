package com.example.Controllers;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.Entities.Alumno;
import com.example.Entities.Correo;
import com.example.Entities.Curso;
import com.example.Entities.Horario;
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

    //Punto 5. Metodo para formulario de alta/Modificación
    @GetMapping("/frmAltaModificacionAlumno")
    
    public String formularioAltaModificacionAlumno(Model model) {

       
        Alumno alumno = new Alumno();

        model.addAttribute("alumno", alumno);


        model.addAttribute("cursos", cursoService.dameCursos());

        return "views/frmAltaModificacionAlumno";
    }
    
    @PostMapping("/persistir")
    @Transactional
    public String persistirAlumno(@ModelAttribute(name = "alumno")Alumno alumno,
        @RequestParam(name = "numerosTel", required = false) String telefonosRecibidos,
        @RequestParam(name = "direccionesCorreo", required = false) String correosRecibidos,
        @RequestParam(name = "file", required = false) MultipartFile imagen) {  

            if(!imagen.isEmpty()) {

                
                Path imageFolder = Path.of("src/main/resources/static/images"); 

              
                Path rutaAbsoluta = imageFolder.toAbsolutePath();

               
                Path rutaCompleta = Path.of(rutaAbsoluta + "/" + imagen.getOriginalFilename());


                try {

                    byte[] baytesImage = imagen.getBytes();
                    Files.write(rutaCompleta, baytesImage);

                    
                    alumno.setFoto(imagen.getOriginalFilename());


                } catch (IOException e) {
                    // TODO: handle exception
                }
            }

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

    //Punto 7.Actualizar un alumno
    @GetMapping("/actualizar/{id}")
    @Transactional
    public String actualizarAlumno(@PathVariable(name = "id", required = true)int idAlumno, 
                                        Model model) {
        
        Alumno alumno = alumnoService.dameUnAlumno(idAlumno);
        model.addAttribute("alumno", alumno);
        
        List<Curso> cursos = cursoService.dameCursos();
        model.addAttribute("cursos", cursos);
        
        if(alumno.getTelefonos() != null) {
            String numerosTelefono = alumno.getTelefonos().stream()
                        .map(Telefono::getNumero)
                        .collect(Collectors.joining(";"));
            model.addAttribute("numerosTelefono", numerosTelefono);
        }

        if(alumno.getCorreos() != null) {
        String direccionesDeCorreo = alumno.getCorreos().stream()
                    .map(Correo::getCorreo)
                    .collect(Collectors.joining(";"));
        model.addAttribute("direccionesDeCorreo", direccionesDeCorreo);
    }

        return "views/frmAltaModificacionAlumno";
    }

    //Punto 8.Mostrar con link en vista alumnos matriculados en horario diurno

    @GetMapping("/listadoAlumnosDiurno")
    public String alumnosDiurno(Model model) {
        List<Alumno> todosLosAlumnos = alumnoService.dameTodosLosAlumnos();
        List<Alumno> alumnosDiurno = todosLosAlumnos.stream()
                                .filter(alumno -> alumno.getCurso().getHorario()==Horario.DIURNO)
                                .collect(Collectors.toList());

        model.addAttribute("alumnosDiurno", alumnosDiurno);
        return "views/listadoAlumnosDiurno";
    }

    //Punto 9.Mostrar con link en vista alumnos agrupados por curso

    @GetMapping("/listadoAlumnosPorCurso")
        public String alumnosPorCurso (Model model) {
            List<Alumno>todosLosAlumnos = alumnoService.dameTodosLosAlumnos();
            Map<Curso, List<Alumno>> listadoAlumnosCurso = todosLosAlumnos.stream()
                                    .collect(Collectors.groupingBy(Alumno::getCurso));

            model.addAttribute("listadoAlumnosCurso", listadoAlumnosCurso);


            return "views/listadoAlumnosPorCurso";
        }
   
  }

