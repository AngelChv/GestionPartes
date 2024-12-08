package org.example.gestionpartes.DAO;

import org.example.gestionpartes.model.Parte;

import java.util.List;

public interface ParteDAO {
    List<Parte> findAll();
}
