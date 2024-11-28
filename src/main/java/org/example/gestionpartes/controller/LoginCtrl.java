package org.example.gestionpartes.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.apache.commons.codec.digest.DigestUtils;
import org.example.gestionpartes.DAO.ProfesorDAOImpl;
import org.example.gestionpartes.model.Profesor;
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
        String passwordEncriptada = DigestUtils.sha256Hex(password);

        Profesor profesor = profesorDAO.getProfesor(numAsig);

        //Compruebo si el profesor existe y si la contraseña es correcta
        if (profesor != null && profesor.getPassword().equals(passwordEncriptada)) {
            SceneManager.changeScene(event, "profesor-view.fxml");
        } else {
            AlertShow.error("Usuario o contraseña incorrectos");
        }
    }


}
