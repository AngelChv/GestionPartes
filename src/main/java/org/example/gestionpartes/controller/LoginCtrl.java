package org.example.gestionpartes.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.apache.commons.codec.digest.DigestUtils;
import org.example.gestionpartes.DAO.ProfesorDAOImpl;
import org.example.gestionpartes.model.Profesor;
import org.example.gestionpartes.model.TipoProfesor;
import org.example.gestionpartes.service.GestionPartesService;
import org.example.gestionpartes.util.AlertShow;
import org.example.gestionpartes.util.SceneManager;

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

        if (!numAsig.isEmpty() && !password.isEmpty()) {
            String passwordEncriptada = DigestUtils.sha256Hex(password);
            Profesor profesor = profesorDAO.getProfesor(numAsig);
            if (profesor.getPassword().equals(passwordEncriptada)) {
                GestionPartesService.setProfesor(profesor);
                SceneManager.changeScene(event,"menu-view.fxml");
            } else {
                AlertShow.error("Usuario o contraseña incorrectos");
            }
        }else{
            AlertShow.error("Rellene los campos");
        }
    }
}