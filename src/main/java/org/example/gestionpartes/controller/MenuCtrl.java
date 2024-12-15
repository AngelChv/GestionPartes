package org.example.gestionpartes.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import org.example.gestionpartes.model.Profesor;
import org.example.gestionpartes.model.TipoProfesor;
import org.example.gestionpartes.service.GestionPartesService;
import org.example.gestionpartes.util.AlertShow;
import org.example.gestionpartes.util.SceneManager;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuCtrl implements Initializable {

    @FXML
    private Button buttonCrearParte;

    @FXML
    private Button buttonCrearProfesor;

    @FXML
    private Button buttonListaAlumnos;

    @FXML
    private Button buttonListaPartes;

    @FXML
    private Label userLabel;

    @FXML
    private MenuButton userMenuBttn;

    private final Profesor profesor = GestionPartesService.getProfesor();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userLabel.setText(GestionPartesService.getProfesor().getNombre());
        if (profesor.getTipoProfesor() == TipoProfesor.PROFESOR){
            buttonCrearProfesor.setDisable(true);
            buttonListaAlumnos.setDisable(true);
            buttonListaPartes.setDisable(true);
        }
    }//initialize

    @FXML
    void clickCrearParte(ActionEvent event) {
        SceneManager.changeScene(event, "crear_parte-view.fxml");
    }

    @FXML
    void clickCrearProfesor(ActionEvent event) {
        SceneManager.changeScene(event, "crear_profesor-view.fxml");
    }

    @FXML
    void clickListaAlumnos(ActionEvent event) {
        SceneManager.changeScene(event, "lista_alumnos-view.fxml");
    }

    @FXML
    void clickListaPartes(ActionEvent event) {
        SceneManager.changeScene(event, "lista_partes-view.fxml");
    }

    @FXML
    void onCloseSessionClick(ActionEvent event) {
        AlertShow.confirmation("¿Desea cerrar la sesión?");
        SceneManager.changeScene(event, "login-view.fxml");
    }
}
