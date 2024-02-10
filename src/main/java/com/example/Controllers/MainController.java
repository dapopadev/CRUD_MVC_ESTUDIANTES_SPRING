package com.example.Controllers;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.Services.AlumnoService;
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
    
    @GetMapping("/frmAltaModificacion")
    public String formularioAltaModificacionAlumno(Model model) {


        return "views/frmAltaModificacionAlumno";
    }
    
}