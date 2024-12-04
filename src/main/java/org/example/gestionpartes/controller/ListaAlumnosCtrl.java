package org.example.gestionpartes.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import org.example.gestionpartes.DAO.AlumnoDAOImpl;
import org.example.gestionpartes.model.Alumno;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ListaAlumnosCtrl implements Initializable {
    private static final int FILAS_POR_PAGINA = 5;

    @FXML
    private TextField numExTxt;

    @FXML
    private TableView<Alumno> alumnosTbl;

    @FXML
    private TableColumn<Alumno, Integer> numExClmn;

    @FXML
    private TableColumn<Alumno, String> nombreClmn;

    @FXML
    private TableColumn<Alumno, String> groupClmn;

    @FXML
    private TableColumn<Alumno, Integer> puntosClmn;

    @FXML
    private Pagination pagination;

    private List<Alumno> alumnos;
    private ObservableList<Alumno> alumnosObsL;
    private ObservableList<Alumno> alumnosPaginados; // Lista para la paginación
    private final AlumnoDAOImpl alumnoDAO = new AlumnoDAOImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numExClmn.setCellValueFactory(new PropertyValueFactory<>("numExpediente"));
        nombreClmn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        groupClmn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getGrupo().getNombre()));
        puntosClmn.setCellValueFactory(new PropertyValueFactory<>("puntos"));

        alumnos = alumnoDAO.findAll();
        alumnosObsL = FXCollections.observableArrayList(alumnos);
        alumnosTbl.setItems(alumnosObsL);

        initializePagination();
    }

    private void initializePagination() {
        // Redondear hacia arriba el número de páginas, para que entren todos los alumnos.
        pagination.setPageCount((int) Math.ceil((double) alumnosObsL.size() / FILAS_POR_PAGINA));
        pagination.setCurrentPageIndex(0);
        pagination.setPageFactory((pageIndex) -> {
            if (!alumnosObsL.isEmpty()) {
                int start = pageIndex * FILAS_POR_PAGINA;
                int end = Math.min(start + FILAS_POR_PAGINA, alumnosObsL.size());

                // Mostrar solo los elementos correspondientes a la página
                alumnosPaginados = FXCollections.observableArrayList(alumnosObsL.subList(start, end));
                alumnosTbl.setItems(alumnosPaginados);
            }
            return alumnosTbl;
        });
    }

    @FXML
    void onSearchType(KeyEvent event) {
        String searchText = numExTxt.getText().toLowerCase();

        // Filtrar los alumnos originales según el texto de búsqueda
        List<Alumno> alumnosFiltrados = alumnos.stream().filter(alumno -> (
                alumno.getNombre().toLowerCase().contains(searchText) ||
                        alumno.getGrupo().getNombre().toLowerCase().contains(searchText)) ||
                String.valueOf(alumno.getPuntos()).contains(searchText) ||
                String.valueOf(alumno.getNumExpediente()).contains(searchText)
        ).toList();

        alumnosObsL.setAll(alumnosFiltrados);

        // Actualizar la paginación para reflejar el número de elementos filtrados
        initializePagination();
    }
}
