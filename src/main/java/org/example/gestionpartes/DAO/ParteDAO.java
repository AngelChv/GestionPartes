package org.example.gestionpartes.DAO;

import org.example.gestionpartes.model.Parte;
import org.example.gestionpartes.model.Profesor;

import java.util.List;

public interface ParteDAO {
    List<Parte> findAll();
    Boolean crear(Parte parte);
}
