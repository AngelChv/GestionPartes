package org.example.gestionpartes.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.apache.commons.codec.digest.DigestUtils;
import org.example.gestionpartes.DAO.ProfesorDAOImpl;
import org.example.gestionpartes.model.Profesor;
import org.example.gestionpartes.service.GestionPartesService;
import org.example.gestionpartes.util.AlertShow;
import org.example.gestionpartes.util.SceneManager;
import org.example.gestionpartes.util.Validator;

public class LoginCtrl {

    @FXML
    private Button loginBttn;

    @FXML
    private TextField numTxt;

    @FXML
    private PasswordField passwordTxt;

    ProfesorDAOImpl profesorDAO = new ProfesorDAOImpl();

    @FXML
    void clickLogin(ActionEvent event) {
        String numAsig = numTxt.getText();
        String password = passwordTxt.getText();

        if (numAsig.isEmpty() || password.isEmpty()) {
            AlertShow.warning("Rellene los campos");
        } else { // Campos rellenados
            String passwordEncriptada = DigestUtils.sha256Hex(password);
            Profesor profesor = profesorDAO.getProfesor(numAsig);

            if (!Validator.validarNumAsig(numAsig)) {
                AlertShow.warning("El número asignado debe de ser un entero de cúatro dígitos.");
            } else if (profesor == null || !profesor.getPassword().equals(passwordEncriptada)) {
                AlertShow.warning("Usuario o contraseña incorrectos");
            } else { // Login correcto.
                GestionPartesService.setProfesor(profesor);
                SceneManager.changeScene(event, "crear_parte-view.fxml");
            }
        }
    }
}