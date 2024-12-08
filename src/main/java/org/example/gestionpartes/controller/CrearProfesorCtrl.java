package org.example.gestionpartes.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.apache.commons.codec.digest.DigestUtils;
import org.example.gestionpartes.DAO.ProfesorDAOImpl;
import org.example.gestionpartes.model.Profesor;
import org.example.gestionpartes.model.TipoProfesor;
import org.example.gestionpartes.util.AlertShow;
import org.example.gestionpartes.util.Validator;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CrearProfesorCtrl implements Initializable {

    @FXML
    private Button buttonCrear;

    @FXML
    private ComboBox<TipoProfesor> cbTipo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtNumAsig;

    @FXML
    private PasswordField txtPassword;

    private final ProfesorDAOImpl profesorDAO = new ProfesorDAOImpl();

    @FXML
    void clickCrear(ActionEvent event) {
        String nombre = txtNombre.getText();
        String numAsig = txtNumAsig.getText();
        String password = txtPassword.getText();
        TipoProfesor tipo = cbTipo.getValue();

        if (!Validator.validarNumAsig(numAsig)) {
            AlertShow.info("El número asignado debe estar formado de 4 números.");
        }else {
            if (!nombre.isEmpty() && !password.isEmpty() && tipo != null) {
                String passwordEncriptada = DigestUtils.sha256Hex(password);
                Profesor p = new Profesor(passwordEncriptada,nombre,numAsig,tipo);
                if (profesorDAO.crearProfesor(p)){
                    AlertShow.info("Profesor creada correctamente.");
                }else {
                    AlertShow.error("Error al crear el profesor");
                }
            }else {
                AlertShow.info("Rellene todos los campos");
            }
        }
    }//clickCrear

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<TipoProfesor> tipoProfesores = new ArrayList<>();
        tipoProfesores.add(TipoProfesor.PROFESOR);
        tipoProfesores.add(TipoProfesor.JEFE_DE_ESTUDIOS);
        cbTipo.getItems().addAll(tipoProfesores);

    }
}
