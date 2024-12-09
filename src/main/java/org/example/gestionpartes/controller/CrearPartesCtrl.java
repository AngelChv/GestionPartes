package org.example.gestionpartes.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.example.gestionpartes.DAO.AlumnoDAO;
import org.example.gestionpartes.DAO.AlumnoDAOImpl;
import org.example.gestionpartes.model.Alumno;
import org.example.gestionpartes.model.ColorParte;
import org.example.gestionpartes.model.Parte;
import org.example.gestionpartes.model.TipoParte;
import org.example.gestionpartes.service.GestionPartesService;
import org.example.gestionpartes.util.AlertShow;
import org.example.gestionpartes.util.Validator;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.Set;

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

    private final static AlumnoDAOImpl alumnoDAO = new AlumnoDAOImpl();

    //private final static Set<TipoParte> tiposParte = new ;

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
        //Obtener los valores de los campos
        String numExpediente=numexpedienteTxt.getText();
        LocalDate fecha=datePick.getValue();
        String descripcion=descripcionTxt.getText();
        String sancion=sancionTxt.getText();
        String hora = horaComboBox.getSelectionModel().getSelectedItem();
        String minuto = minutoComboBox.getSelectionModel().getSelectedItem();

        if (numExpediente.isEmpty() || fecha==null || hora==null || minuto==null
            || descripcion.isEmpty() || sancion.isEmpty()){
            AlertShow.warning("Por favor. Rellene todos los campos.");
        } else if (!Validator.validarNumExp(numExpediente)){
            AlertShow.warning("Introduzca un numero de expediente correcto.");
        } else { //Todos los campos completados y el numero de expediente es valido.
            Alumno alumno = alumnoDAO.findByNumExp(Integer.parseInt(numExpediente));
            if (alumno==null){
                AlertShow.warning("No existe un alumno con número de expediente: "+numExpediente);
            } else {
                LocalTime time = LocalTime.of(Integer.parseInt(hora), Integer.parseInt(minuto));
              //  Parte parte = new Parte(alumno, GestionPartesService.getProfesor(),descripcion,fecha,time,sancion, color);
            }
        }
    }

}
