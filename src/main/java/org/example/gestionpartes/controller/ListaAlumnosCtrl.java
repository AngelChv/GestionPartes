package org.example.gestionpartes.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.InputMethodEvent;
import org.example.gestionpartes.model.Alumno;

public class ListaAlumnosCtrl {

    @FXML
    private TableView<Alumno> alumnosTbl;

    @FXML
    private TableColumn<Alumno, String> groupClmn;

    @FXML
    private TableColumn<Alumno, String> nombreClmn;

    @FXML
    private TableColumn<Alumno, Integer> numExClmn;

    @FXML
    private Pagination pagination;

    @FXML
    private TableColumn<Alumno, Integer> puntosClmn;

    @FXML
    void onNumExpedienteChange(InputMethodEvent event) {

    }

}
