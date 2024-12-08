package org.example.gestionpartes.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.example.gestionpartes.model.ColorParte;
import org.example.gestionpartes.util.AlertShow;

import java.net.URL;
import java.util.ResourceBundle;

public class CrearPartesCtrl implements Initializable {
    @FXML
    private VBox vBox;

    @FXML
    private Label titleLabel;

    @FXML
    private TextField numexpedienteTxt;

    @FXML
    private DatePicker datePick;

    @FXML
    private ComboBox<String> horaComboBox;

    @FXML
    private ComboBox<String> minutoComboBox;

    @FXML
    private TextArea descripcionTxt;

    @FXML
    private TextArea sancionTxt;

    private ColorParte color;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        onVerdeClick();
        // Llenar ComboBox con valores
        for (int i = 8; i <= 22; i++) {
            horaComboBox.getItems().add(String.format("%02d", i));
        }
        for (int i = 0; i < 60; i++) {
            minutoComboBox.getItems().add(String.format("%02d", i));
        }

        // Configuración inicial
        horaComboBox.setValue("00");
        minutoComboBox.setValue("00");
    }

    @FXML
    void onNaranjaClick(ActionEvent event) {
        vBox.setStyle("-fx-background-color: #faac46;");
        titleLabel.setText("Parte naranja de nota negativa");
        color=ColorParte.NARANJA;
    }

    @FXML
    void onRojoClick(ActionEvent event) {
        vBox.setStyle("-fx-background-color: #d06f6f;");
        titleLabel.setText("Parte rojo de nota negativa");
        color=ColorParte.ROJO;
    }

    @FXML
    void onVerdeClick() {
        vBox.setStyle("-fx-background-color: #9af69f;");
        titleLabel.setText("Parte verde de advertencia");
        color=ColorParte.VERDE;
    }

    @FXML
    void onSaveClick(ActionEvent event) {
        String hora = horaComboBox.getSelectionModel().getSelectedItem();
        String minuto = minutoComboBox.getSelectionModel().getSelectedItem();
        if (numexpedienteTxt.getText().isEmpty() || datePick==null || hora==null || minuto==null
            || descripcionTxt.getText().isEmpty() || sancionTxt.getText().isEmpty()){
            AlertShow.warning("Por favor. Rellene todos los campos.");
        }
    }

}
