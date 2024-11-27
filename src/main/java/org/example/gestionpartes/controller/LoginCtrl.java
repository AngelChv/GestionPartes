package org.example.gestionpartes.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginCtrl {

    @FXML
    private Button loginBttn;

    @FXML
    private TextField numTxt;

    @FXML
    private TextField passwordTxt;

    @FXML
    void clickLogin(ActionEvent event) {
        String num = numTxt.getText();
        String password = passwordTxt.getText();


    }

}
