package org.example.gestionpartes.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.example.gestionpartes.DAO.ParteDAOImpl;
import org.example.gestionpartes.model.Parte;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ListaPartesCtrl implements Initializable {
    private static final int FILAS_POR_PAGINA = 5;

    @FXML
    private TableColumn<Parte, String> fechaClmn;

    @FXML
    private TableColumn<Parte, String> groupClmn;

    @FXML
    private TableColumn<Parte, String> nombreClmn;

    @FXML
    private TableColumn<Parte, String> numExClmn;

    @FXML
    private TextField numExTxt;

    @FXML
    private Pagination pagination;

    @FXML
    private TableView<Parte> partesTbl;

    @FXML
    private TableColumn<Parte, String> profesorClmn;

    private List<Parte> partes;
    private ObservableList<Parte> partesObsL;
    private ObservableList<Parte> partesOBsLPaginados;
    private static final ParteDAOImpl parteDAO = new ParteDAOImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numExClmn.setCellValueFactory(
                data -> new SimpleStringProperty(String.valueOf(data.getValue().getAlumno().getNumExpediente()))
        );
        nombreClmn.setCellValueFactory(
                data -> new SimpleStringProperty(data.getValue().getAlumno().getNombre())
        );
        groupClmn.setCellValueFactory(
                data -> new SimpleStringProperty(data.getValue().getAlumno().getGrupo().getNombre())
        );
        profesorClmn.setCellValueFactory(
                data -> new SimpleStringProperty(data.getValue().getProfesor().getNombre())
        );
        fechaClmn.setCellValueFactory(
                data -> new SimpleStringProperty(data.getValue().getFecha().toString())
        );

        partes = parteDAO.findAll();
        partesObsL = FXCollections.observableArrayList(partes);
        partesTbl.setItems(partesObsL);

        initializePagination();
    }


    private void initializePagination() {
        // Redondear hacia arriba el número de páginas, para que entren todos los alumnos.
        pagination.setPageCount((int) Math.ceil((double) partesObsL.size() / FILAS_POR_PAGINA));
        pagination.setCurrentPageIndex(0);
        pagination.setPageFactory((pageIndex) -> {
            if (!partesObsL.isEmpty()) {
                int start = pageIndex * FILAS_POR_PAGINA;
                int end = Math.min(start + FILAS_POR_PAGINA, partesObsL.size());

                // Mostrar solo los elementos correspondientes a la página
                partesOBsLPaginados = FXCollections.observableArrayList(partesObsL.subList(start, end));
                partesTbl.setItems(partesOBsLPaginados);
            }
            return partesTbl;
        });
    }

    @FXML
    void onSearchByDateClick(ActionEvent event) {

    }

    @FXML
    void onSearchType(KeyEvent event) {

    }

}
