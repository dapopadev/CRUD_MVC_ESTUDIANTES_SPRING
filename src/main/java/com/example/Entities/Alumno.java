package com.example.Entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alumno")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Alumno implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   
    private int id;
    private String nombre;
    private String apellidos;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaMatriculacion;
   
     @Enumerated(EnumType.STRING)
    private Genero genero;

    private int totalAsigMatr;
    
    //Nombre de la foto, porque el contenido se almacenara en el sist de archivos(file system),
    //en prinicipio en la carpeta static directamente, o dentro de una carpeta que este en static 
    private String foto;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Curso curso;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "alumno")
    private List<Telefono> telefonos;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "alumno")
    private List<Correo> correos;

    
}

