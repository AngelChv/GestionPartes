package org.example.gestionpartes.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.example.gestionpartes.GestionPartesApp;
import org.example.gestionpartes.model.Profesor;
import org.example.gestionpartes.model.TipoProfesor;
import org.example.gestionpartes.service.GestionPartesService;
import org.example.gestionpartes.util.SceneManager;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfesorCtrl {

    @FXML
    private Button buttonCrearParte;

    @FXML
    private Button buttonCrearProfesor;

    @FXML
    private Button buttonListaAlumnos;

    @FXML
    private Button buttonListaPartes;


    @FXML
    void clickCrearParte(ActionEvent event) {
        SceneManager.changeScene(event, "crearparte-view.fxml");

    }

    @FXML
    void clickCrearProfesor(ActionEvent event) {
        SceneManager.changeScene(event, "crearprofesor-view.fxml");

    }

    @FXML
    void clickListaAlumnos(ActionEvent event) {
        SceneManager.changeScene(event, "listaalumnos-view.fxml");

    }

    @FXML
    void clickListaPartes(ActionEvent event) {
        SceneManager.changeScene(event, "listapartes-view.fxml");

    }
}
