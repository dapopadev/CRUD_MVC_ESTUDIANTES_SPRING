package com.example.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Entities.Curso;

@Repository
public interface CursoDao extends JpaRepository<Curso,Integer> {

}