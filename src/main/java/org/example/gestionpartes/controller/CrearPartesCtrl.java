package org.example.gestionpartes.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.example.gestionpartes.DAO.*;
import org.example.gestionpartes.model.*;
import org.example.gestionpartes.service.GestionPartesService;
import org.example.gestionpartes.util.AlertShow;
import org.example.gestionpartes.util.SceneManager;
import org.example.gestionpartes.util.Validator;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class CrearPartesCtrl implements Initializable, LoadAbleData<TableView<Parte>> {
    @FXML
    public HBox menuInclude;

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
    private Label sancionLabel;

    @FXML
    private TextArea sancionTxt;

    @FXML
    private Button guardarBttn;

    private ComboBox<String> tipoSancionCBox;

    private ColorParte color;

    private final static AlumnoDAOImpl alumnoDAO = new AlumnoDAOImpl();

    private final static ParteDAOImpl parteDAO = new ParteDAOImpl();

    private static final Map<ColorParte, TipoParte> tiposParteMap = new HashMap<>();

    private Parte oldParte = null;
    private TableView<Parte> table;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        onVerdeClick();

        TipoParteDAOImpl tipoParteDAO = new TipoParteDAOImpl();
        for (TipoParte t : tipoParteDAO.findAll()) {
            tiposParteMap.put(t.getColor(), t);
        }

        // Llenar ComboBox con valores
        for (int i = 8; i <= 22; i++) {
            horaComboBox.getItems().add(String.format("%02d", i));
        }
        for (int i = 0; i < 60; i++) {
            minutoComboBox.getItems().add(String.format("%02d", i));
        }

        // Configuración inicial
        horaComboBox.setValue("08");
        minutoComboBox.setValue("00");

        // Asignar tipos sancion comboBox.
        ArrayList<String> tiposSancion = new ArrayList<>();
        tiposSancion.add("Incoación de expediente o en su caso expediente abreviado.");
        tiposSancion.add("Reunión con la Comisión de Convivencia.");
        tiposSancion.add("Es obligatorio pedir disculpas a la persona/as contra las que se ejerció daño físico o moral, y/o reparar los daños materiales causados");
        tiposSancion.add("Otra");
        tipoSancionCBox = new ComboBox<>(FXCollections.observableArrayList(tiposSancion));
        tipoSancionCBox.setPromptText("Sanción que recibes por jefatura de Estudios:");

        // Evento del comboBox:
        tipoSancionCBox.getSelectionModel().selectedItemProperty().addListener((_, _, newValue) -> {
            if ("Otra".equals(newValue)) {
                // Añadir la sanción al VBox
                if (!vBox.getChildren().contains(sancionTxt)) {
                    vBox.getChildren().add(vBox.getChildren().indexOf(guardarBttn), sancionTxt);
                    VBox.setVgrow(sancionTxt, Priority.ALWAYS);
                }
            } else {
                // Eliminar la sanción si está presente
                vBox.getChildren().remove(sancionTxt);
            }
        });
    }

    @Override
    public void loadData(TableView<Parte> table) {
        vBox.getChildren().remove(menuInclude);
        guardarBttn.setText("Editar");
        this.table = table;
        oldParte = table.getSelectionModel().getSelectedItem();
        switch (oldParte.getTipo().getColor()) {
            case VERDE -> sancionTxt.setText(oldParte.getSancion());
            case NARANJA -> {
                onNaranjaClick();
                sancionTxt.setText(oldParte.getSancion());
            }
            case ROJO -> {
                onRojoClick();
                if (tipoSancionCBox.getItems().contains(oldParte.getSancion())) {
                    tipoSancionCBox.setValue(oldParte.getSancion());
                } else {
                    tipoSancionCBox.setValue("Otra");
                    sancionTxt.setText(oldParte.getSancion());
                }
            }
        }

        numexpedienteTxt.setText(String.valueOf(oldParte.getAlumno().getNumExpediente()));
        datePick.setValue(oldParte.getFecha());
        horaComboBox.setValue(String.format("%02d", oldParte.getHora().getHour()));
        minutoComboBox.setValue(String.format("%02d", oldParte.getHora().getMinute()));
        descripcionTxt.setText(oldParte.getDescripcion());
    }

    @FXML
    void onRojoClick() {
        // Cambiar color de fondo
        vBox.setStyle("-fx-background-color: #d06f6f;");
        titleLabel.setText("Parte rojo de nota negativa");
        color = ColorParte.ROJO;

        vBox.getChildren().remove(sancionLabel);
        vBox.getChildren().remove(sancionTxt);

        // Añadir tipoSancionCBox por encima del botón "Guardar"
        if (!vBox.getChildren().contains(tipoSancionCBox)) {
            vBox.getChildren().add(vBox.getChildren().indexOf(guardarBttn), tipoSancionCBox);
        }
    }

    @FXML
    void onNaranjaClick() {
        // Cambiar color de fondo
        vBox.setStyle("-fx-background-color: #faac46;");
        titleLabel.setText("Parte naranja de nota negativa");
        color = ColorParte.NARANJA;

        configureTextArea();
    }

    @FXML
    void onVerdeClick() {
        // Cambiar color de fondo
        vBox.setStyle("-fx-background-color: #9af69f;");
        titleLabel.setText("Parte verde de advertencia");
        color = ColorParte.VERDE;

        configureTextArea();
    }

    private void configureTextArea() {
        // Eliminar el ComboBox de tipos de sanción si está presente
        vBox.getChildren().remove(tipoSancionCBox);

        // Añadir sancionLabel por encima del botón "Guardar"
        if (!vBox.getChildren().contains(sancionLabel)) {
            vBox.getChildren().add(vBox.getChildren().indexOf(guardarBttn), sancionLabel);
            // Reasignar vgrow explícitamente
        }

        if (!vBox.getChildren().contains(sancionTxt)) {
            vBox.getChildren().add(vBox.getChildren().indexOf(guardarBttn), sancionTxt);
            VBox.setVgrow(sancionTxt, Priority.ALWAYS);
        }
    }

    @FXML
    void onSaveClick(ActionEvent event) {
        //Obtener los valores de los campos
        String numExpediente = numexpedienteTxt.getText();
        LocalDate fecha = datePick.getValue();
        String descripcion = descripcionTxt.getText();
        String sancion = sancionTxt.getText();
        String hora = horaComboBox.getSelectionModel().getSelectedItem();
        String minuto = minutoComboBox.getSelectionModel().getSelectedItem();
        String tipoSancion = tipoSancionCBox.getSelectionModel().getSelectedItem();


        if (numExpediente.isEmpty() || fecha == null || hora == null || minuto == null
                || descripcion.isEmpty()) { // Si los campos comunes no están rellenos.
            AlertShow.warning("Por favor. Rellene todos los campos.");
        } else if (color != ColorParte.ROJO && sancion.isEmpty()) { // Si los campos de parte verde y naranja no están rellenos.
            AlertShow.warning("Por favor, introduzca una sanción.");
        } else if (color == ColorParte.ROJO && tipoSancion == null) {
            // Si no se ha elegido una sanción en el parte rojo
            AlertShow.warning("Por favor, seleccione una sanción.");
        } else if (color == ColorParte.ROJO && tipoSancion.equals("Otra") && sancion.isEmpty()) {
            // Si no se ha rellenado la sanción personalizada.
            AlertShow.warning("Por favor, rellene la sanción personalizada.");
        } else if (!Validator.validarNumExp(numExpediente)) {
            AlertShow.warning("Introduzca un numero de expediente correcto.");
        } else { //Todos los campos completados y el número de expediente es válido.
            Alumno alumno = alumnoDAO.findByNumExp(Integer.parseInt(numExpediente));
            if (alumno == null) {
                AlertShow.warning("No existe un alumno con número de expediente: " + numExpediente);
            } else {
                LocalTime time = LocalTime.of(Integer.parseInt(hora), Integer.parseInt(minuto));

                // Aplicar sanción adecuada en caso de seleccionar una del comboBox.
                if (color == ColorParte.ROJO && !tipoSancion.equals("Otra")) {
                    sancion = tipoSancionCBox.getSelectionModel().getSelectedItem();
                }

                Parte newParte = new Parte(
                        alumno,
                        GestionPartesService.getProfesor(),
                        descripcion,
                        fecha,
                        time,
                        sancion,
                        tiposParteMap.get(color)
                );

                if (oldParte == null) { // Contexto de crear parte
                    if (parteDAO.crear(newParte)) {
                        AlertShow.info("Parte creado correctamente.");
                    } else {
                        AlertShow.error("No se pudo crear el parte.");
                    }
                } else { // Contexto de modificar parte
                    oldParte.setAlumno(newParte.getAlumno());
                    oldParte.setProfesor(newParte.getProfesor());
                    oldParte.setDescripcion(newParte.getDescripcion());
                    oldParte.setFecha(newParte.getFecha());
                    oldParte.setHora(newParte.getHora());
                    oldParte.setSancion(newParte.getSancion());
                    oldParte.setTipo(newParte.getTipo());
                    if (parteDAO.editar(oldParte)) {
                        SceneManager.closeScene("crear_parte-view.fxml");
                        table.refresh();
                        AlertShow.info("Parte modificado correctamente.");
                    } else {
                        AlertShow.error("Error al modificar el parte.");
                    }
                }

            }
        }
    }
}