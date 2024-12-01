package org.example.gestionpartes.DAO;

import org.example.gestionpartes.model.Profesor;

public interface ProfesorDAO {
    Profesor getProfesor(String numAsig);
    Boolean crearProfesor(Profesor profesor);


}
