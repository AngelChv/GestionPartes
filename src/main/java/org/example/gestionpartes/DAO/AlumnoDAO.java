package org.example.gestionpartes.DAO;

import org.example.gestionpartes.model.Alumno;

import java.util.List;

public interface AlumnoDAO {
    List<Alumno> findAll();
    Alumno findByNumExp(int numExp);
}
