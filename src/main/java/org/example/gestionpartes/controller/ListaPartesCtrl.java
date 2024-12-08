package org.example.gestionpartes.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import org.example.gestionpartes.DAO.ParteDAOImpl;
import org.example.gestionpartes.model.ColorParte;
import org.example.gestionpartes.model.Parte;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ListaPartesCtrl implements Initializable {
    private static final int FILAS_POR_PAGINA = 5;

    @FXML
    private DatePicker startDatePick;

    @FXML
    private DatePicker endDatePick;

    @FXML
    private TableColumn<Parte, String> fechaClmn;

    @FXML
    private TableColumn<Parte, String> groupClmn;

    @FXML
    private TableColumn<Parte, String> nombreClmn;

    @FXML
    private TableColumn<Parte, String> numExClmn;

    @FXML
    private TextField filterTxt;

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


        partesTbl.setRowFactory(_ -> new TableRow<>() {
            protected void updateItem(Parte parte, boolean empty) {
                super.updateItem(parte, empty);
                if (empty || parte == null) {
                    setStyle(""); // Si la fila está vacía no se aplica estilo.
                } else {
                    switch (parte.getTipo().getColor()) {
                        case ColorParte.VERDE -> setStyle("-fx-background-color: #9af69f;");
                        case ColorParte.NARANJA -> setStyle("-fx-background-color: #faac46;");
                        case ColorParte.ROJO -> setStyle("-fx-background-color: #d06f6f;");
                        default -> setStyle("");
                    }
                }
            }
        });

        initializePagination();
    }

    @FXML
    void onClearFiltersClick(ActionEvent event) {
        filterTxt.clear();
        startDatePick.setValue(null);
        endDatePick.setValue(null);
        partesObsL.setAll(partes);
        initializePagination();
    }


    @FXML
    void onStartDateClick(ActionEvent event) {
        dateFilter();
    }

    @FXML
    void onEndDateClick(ActionEvent event) {
        dateFilter();
    }

    @FXML
    void onSearchType(KeyEvent event) {
        String searchText = filterTxt.getText().toLowerCase();

        // Filtrar los partes originales según el texto de búsqueda
        List<Parte> partesFiltrados = partes.stream().filter(parte -> (
                parte.getAlumno().getNombre().toLowerCase().contains(searchText) ||
                        parte.getAlumno().getGrupo().getNombre().toLowerCase().contains(searchText) ||
                        parte.getProfesor().getNombre().toLowerCase().contains(searchText) ||
                        parte.getTipo().getColor().toString().toLowerCase().contains(searchText) ||
                        String.valueOf(parte.getAlumno().getNumExpediente()).contains(searchText)
        )).toList();

        partesObsL.setAll(partesFiltrados);

        // Actualizar la paginación para reflejar el número de elementos filtrados
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

    private void dateFilter() {
        LocalDate startDate = startDatePick.getValue();
        LocalDate endDate = endDatePick.getValue();

        if (startDate != null && endDate != null) {
            // Filtrar los partes originales según las fechas introducidas.
            List<Parte> partesFiltrados = partes.stream().filter(parte -> (
                    parte.getFecha().isAfter(startDatePick.getValue()) &&
                            parte.getFecha().isBefore(endDatePick.getValue())
            )).toList();

            partesObsL.setAll(partesFiltrados);

            // Actualizar la paginación para reflejar el número de elementos filtrados
            initializePagination();
        }
    }
}
