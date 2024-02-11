package com.example;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.Entities.Alumno;
import com.example.Entities.Correo;
import com.example.Entities.Curso;
import com.example.Entities.Genero;
import com.example.Entities.Horario;
import com.example.Entities.Telefono;
import com.example.Services.AlumnoService;
import com.example.Services.CorreoService;
import com.example.Services.CursoService;
import com.example.Services.TelefonoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SpringBootApplication
public class CrudMvcEstudiantesSpringApplication implements CommandLineRunner{


	private final AlumnoService alumnoService;
	private final CursoService cursoService;
	private final CorreoService correoService;
	private final TelefonoService telefonoService;

	public static void main(String[] args) {
		SpringApplication.run(CrudMvcEstudiantesSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//PUNTO 11.AGREGAMOS DATOS DE MUESTRA

		//Creamos 4 cursos
		
		Curso curso1 = Curso.builder()
			.id(1)
			.descripcion("INFORMATICA")
			.horario(Horario.NOCTURNO)
			.build();

		Curso curso2 = Curso.builder()
			.id(2)
			.descripcion("IDIOMAS")
			.horario(Horario.DIURNO)
			.build();

		Curso curso3 = Curso.builder()
			.id(3)
			.descripcion("IDIOMAS")
			.horario(Horario.NOCTURNO)
			.build();

		Curso curso4 = Curso.builder()
			.id(4)
			.descripcion("CONTABILIDAD")
			.horario(Horario.DIURNO)
			.build();

		cursoService.persistirCurso(curso1);
		cursoService.persistirCurso(curso2);
		cursoService.persistirCurso(curso3);
		cursoService.persistirCurso(curso4);


		//Creamos 8 alumnos

		Alumno alumno1 = Alumno.builder()
				.nombre("Alvaro")
				.apellidos("Garcia")
				.genero(Genero.HOMBRE)
				.fechaMatriculacion(LocalDate.of(2023, Month.SEPTEMBER, 15))
				.totalAsigMatr(6)
				.curso(cursoService.dameUnCurso(4))
				.foto("imagen1.jpg")
				.build();

		
		Alumno alumno2 = Alumno.builder()
				.nombre("Cristina")
				.apellidos("Benavente")
				.genero(Genero.MUJER)
				.fechaMatriculacion(LocalDate.of(2022, Month.APRIL, 05))
				.totalAsigMatr(8)
				.curso(cursoService.dameUnCurso(2))
				.foto("imagen2.jpg")
				.build();

		Alumno alumno3 = Alumno.builder()
				.nombre("Ana")
				.apellidos("Grau")
				.genero(Genero.MUJER)
				.fechaMatriculacion(LocalDate.of(2021, Month.JANUARY, 25))
				.totalAsigMatr(4)
				.curso(cursoService.dameUnCurso(1))
				.foto("imagen3.jpg")
				.build();
					
		Alumno alumno4 = Alumno.builder()
				.nombre("Cristina")
				.apellidos("Benavente")
				.genero(Genero.MUJER)
				.fechaMatriculacion(LocalDate.of(2022, Month.APRIL, 05))
				.totalAsigMatr(8)
				.curso(cursoService.dameUnCurso(3))
				.foto("imagen4.jpg")
				.build();

		Alumno alumno5 = Alumno.builder()
				.nombre("Marta")
				.apellidos("Sanchez Moya")
				.genero(Genero.MUJER)
				.fechaMatriculacion(LocalDate.of(2018, Month.AUGUST, 10))
				.totalAsigMatr(8)
				.curso(cursoService.dameUnCurso(1))
				.foto("imagen5.jpg")
				.build();

		Alumno alumno6 = Alumno.builder()
				.nombre("Victoria")
				.apellidos("Alvarez Ruano")
				.genero(Genero.MUJER)
				.fechaMatriculacion(LocalDate.of(2024, Month.JANUARY, 05))
				.totalAsigMatr(8)
				.curso(cursoService.dameUnCurso(4))
				.foto("imagen6.jpg")
				.build();

		Alumno alumno7 = Alumno.builder()
				.nombre("Victoria")
				.apellidos("Sanchez Moya")
				.genero(Genero.MUJER)
				.fechaMatriculacion(LocalDate.of(2022, Month.NOVEMBER, 14))
				.totalAsigMatr(8)
				.curso(cursoService.dameUnCurso(2))
				.foto("imagen7.jpg")
				.build();

		Alumno alumno8 = Alumno.builder()
				.nombre("Ricardo")
				.apellidos("Casañ Pitarch")
				.genero(Genero.HOMBRE)
				.fechaMatriculacion(LocalDate.of(2021, Month.DECEMBER, 14))
				.totalAsigMatr(8)
				.curso(cursoService.dameUnCurso(3))
				.foto("imagen8.jpg")
				.build();		

	
			alumnoService.persistirAlumno(alumno1);
			alumnoService.persistirAlumno(alumno2);
			alumnoService.persistirAlumno(alumno3);
			alumnoService.persistirAlumno(alumno4);
			alumnoService.persistirAlumno(alumno5);
			alumnoService.persistirAlumno(alumno6);
			alumnoService.persistirAlumno(alumno7);
			alumnoService.persistirAlumno(alumno8);
		
		//Creamos telefonos
		
		Telefono telefono1Alumno1 = Telefono.builder()
						.numero("961012731")
						.alumno(alumnoService.dameUnAlumno(1))
						.build();
		
		Telefono telefono2Alumno1 = Telefono.builder()
						.numero("663141120")
						.alumno(alumnoService.dameUnAlumno(1))
						.build();

		telefonoService.persistirTelefono(1, telefono1Alumno1);
		telefonoService.persistirTelefono(1, telefono2Alumno1);

						
		Telefono telefono1Alumno2 = Telefono.builder()
						.numero("66234220")
						.alumno(alumnoService.dameUnAlumno(2))
						.build();

		telefonoService.persistirTelefono(2, telefono1Alumno2);

		Telefono telefono1Alumno3 = Telefono.builder()
						.numero("623104320")
						.alumno(alumnoService.dameUnAlumno(3))
						.build();

		telefonoService.persistirTelefono(3, telefono1Alumno3);


		Telefono telefono1Alumno4 = Telefono.builder()
						.numero("962724539")
						.alumno(alumnoService.dameUnAlumno(4))
						.build();

		telefonoService.persistirTelefono(4, telefono1Alumno4);

		
		Telefono telefono1Alumno5 = Telefono.builder()
						.numero("660219829")
						.alumno(alumnoService.dameUnAlumno(5))
						.build();		
	
		telefonoService.persistirTelefono(5, telefono1Alumno5);
						
		
		Telefono telefono1Alumno6 = Telefono.builder()
						.numero("963981039")
						.alumno(alumnoService.dameUnAlumno(6))
						.build();

		telefonoService.persistirTelefono(6, telefono1Alumno6);


		Telefono telefono1Alumno7 = Telefono.builder()
						.numero("60001929")
						.alumno(alumnoService.dameUnAlumno(7))
						.build();
		
		telefonoService.persistirTelefono(7, telefono1Alumno7);


		Telefono telefono1Alumno8 = Telefono.builder()
						.numero("678102910")
						.alumno(alumnoService.dameUnAlumno(8))
						.build();	
						
		Telefono telefono2Alumno8 = Telefono.builder()
						.numero("678120982")
						.alumno(alumnoService.dameUnAlumno(8))
						.build();

		telefonoService.persistirTelefono(8, telefono1Alumno8);
		telefonoService.persistirTelefono(8, telefono2Alumno8);

		//Creamos correo

		List<Correo> correosAlumno1 = new ArrayList<>();

		Correo correo1Alumno1 = Correo.builder()
					.correo("dani@gmail.com")
					.alumno(alumnoService.dameUnAlumno(1))
					.build();
		Correo correo2Alumno1 = Correo.builder()
					.correo("dani@gmail.com")
					.alumno(alumnoService.dameUnAlumno(1))
					.build();
		

		correoService.persistirCorreo(1, correo1Alumno1);
		correoService.persistirCorreo(1, correo2Alumno1);
			
		
		List<Correo>correosAlumno2 = new ArrayList<>();

		Correo correo1Alumno2 = Correo.builder()
						.correo("alz@gmail.com")
						.alumno(alumnoService.dameUnAlumno(2))
						.build();

		correoService.persistirCorreo(2, correo1Alumno2);
						
		List<Correo> correosAlumno3 = new ArrayList<>();
		
		Correo correo1Alumno3 = Correo.builder()
						.correo("alz@gmail.com")
						.alumno(alumnoService.dameUnAlumno(3))
						.build();

		correoService.persistirCorreo(3, correo1Alumno3);
					

		List<Correo> correosAlumno4 = new ArrayList<>();
		
		Correo correo1Alumno4 = Correo.builder()
						.correo("vcm@hotmail.com")
						.alumno(alumnoService.dameUnAlumno(4))
						.build();

		Correo correo2Alumno4 = Correo.builder()
										.correo("dnps@gmail.com")
										.alumno(alumnoService.dameUnAlumno(4))
										.build();
				
		correoService.persistirCorreo(4, correo1Alumno4);
		correoService.persistirCorreo(4, correo2Alumno4);

		List<Correo> correosAlumno5 = new ArrayList<>();
		
		Correo correo1Alumno5 = Correo.builder()
						.correo("esk@outlook.com")
						.alumno(alumnoService.dameUnAlumno(5))
						.build();

		correoService.persistirCorreo(5, correo1Alumno5);
		
		List<Correo> correosAlumno6 = new ArrayList<>();
		
		Correo correo1Alumno6 = Correo.builder()
						.correo("alz@gmail.com")
						.alumno(alumnoService.dameUnAlumno(6))
						.build();
		
		Correo correo2Alumno6 = Correo.builder()
						.correo("venqz@hotmail.com")
						.alumno(alumnoService.dameUnAlumno(6))
						.build();
				
		correoService.persistirCorreo(6, correo1Alumno6);
		correoService.persistirCorreo(6, correo2Alumno6);

		List<Correo> correosAlumno7 = new ArrayList<>();
		
		Correo correo1Alumno7 = Correo.builder()
						.correo("jfk@gmail.com")
						.alumno(alumnoService.dameUnAlumno(7))
						.build();

		correoService.persistirCorreo(7, correo1Alumno7);
						
		List<Correo> correosAlumno8 = new ArrayList<>();
		
		Correo correo1Alumno8 = Correo.builder()
						.correo("flsm@gmail.com")
						.alumno(alumnoService.dameUnAlumno(8))
						.build();

		correoService.persistirCorreo(8, correo1Alumno8);
						
	}

}
