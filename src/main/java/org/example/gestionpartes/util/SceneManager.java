package org.example.gestionpartes.util;

import org.example.gestionpartes.model.LoadAbleData;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class
SceneManager {
    /**
     * Mapa para almacenar las ventanas abiertas, identificadas por el archivo FXML.
     */
    private static final Map<String, Stage> openWindows = new HashMap<>();

    /**
     * Cambia la escena de la ventana actual a una nueva escena cargada desde un archivo FXML.
     * No pasa información al controlador.
     *
     * @param event El evento de acción que desencadena el cambio de escena.
     * @param fxml  El nombre del archivo FXML que contiene la definición de la nueva escena.
     */
    public static void changeScene(ActionEvent event, String fxml) {
        changeScene(event, fxml, null);
    }

    /**
     * Cambia la escena de la ventana actual a una nueva escena cargada desde un archivo FXML.
     * Pasa información al controlador
     *
     * @param event El evento de acción que desencadena el cambio de escena.
     * @param fxml  El nombre del archivo FXML que contiene la definición de la nueva escena.
     * @param data  Objeto que es pasado al controlador mediante el método de la interfaz {@link LoadAbleData}.
     */
    public static <T> void changeScene(ActionEvent event, String fxml, T data) {
        // Obtener el botón que generó el evento.
        Button button = (Button) event.getSource();

        // Obtener la ventana (stage) del botón.
        Stage stage = (Stage) button.getScene().getWindow();

        // Eliminar la escena actual del mapa:
        openWindows.entrySet().removeIf(entry -> entry.getValue().getScene() == stage.getScene());

        // Cargar la nueva escena:
        loadScene(fxml, stage, data);
    }

    /**
     * Muestra una nueva ventana cargada desde un archivo FXML, solo si no está ya abierta.
     * No pasa información al controlador.
     *
     * @param fxml El nombre del archivo FXML que contiene la definición de la nueva escena.
     */
    public static void showNewScene(String fxml) {
        showNewScene(fxml, null);
    }

    /**
     * Muestra una nueva ventana cargada desde un archivo FXML, solo si no está ya abierta.
     * Pasa información al controlador.
     *
     * @param fxml El nombre del archivo FXML que contiene la definición de la nueva escena.
     * @param data Objeto que que es pasado al controlador mediante el método de la interfáz {@link LoadAbleData}
     */
    public static <T> void showNewScene(String fxml, T data) {
        // Crear una nueva ventana.
        Stage stage = new Stage();
        // Bloquear el resto de ventanas.
        stage.initModality(Modality.APPLICATION_MODAL);

        loadScene(fxml, stage, data);
    }

    /**
     * Crea una escena a partir de un fxml y lo añade a la ventana indicada.
     * Aplica los estilos y por último muestra la escena.
     *
     * @param fxml El nombre del archivo FXML que contiene la definición de la nueva escena.
     * @param stage ventana en donde se quiere cargar la escena.
     * @param data Objeto que que es pasado al controlador mediante el método de la interfáz {@link LoadAbleData}
     */
    private static <T> void loadScene(String fxml, Stage stage, T data) {
        // Si la ventana ya está abierta y visible, la trae al frente.
        if (openWindows.containsKey(fxml) && openWindows.get(fxml).isShowing()) {
            openWindows.get(fxml).toFront(); // Traer al frente si ya está abierta.
        } else {
            try {
                // Cargador del archivo FXML.
                FXMLLoader fxmlLoader = new FXMLLoader(R.getUI(fxml));
                // Nodo raíz del documento FXML.
                Parent root = fxmlLoader.load();

                // Obtener controlador del fxml.
                Object controller = fxmlLoader.getController();
                // Verificar si el controlador implementa LoadAble y enviar datos a la escena:
                if (data != null && controller instanceof LoadAbleData<?> loadAbleDataController) {
                    ((LoadAbleData<T>) loadAbleDataController).loadData(data);
                }

                // Crear una nueva escena (scene) con el nodo raíz.
                Scene scene = new Scene(root);

                loadCss(scene);

                // Establecer la nueva escena en la ventana.
                stage.setScene(scene);

                // Almacenar la ventana en el mapa de ventanas abiertas.
                openWindows.put(fxml, stage);

                // Escuchar el cierre de la ventana para eliminarla del mapa.
                stage.setOnCloseRequest(_ -> openWindows.remove(fxml));

                // Mostrar la ventana si aún no está visible.
                if (!stage.isShowing()) {
                    stage.show();
                }
            } catch (IOException e) {
                AlertShow.error("Error al cargar la escena: " + e.getMessage());
            }
        }
    }

    /**
     * Cerrar una ventana específica
     *
     * @param fxml de la ventana que se quiera cerrar.
     */
    public static void closeScene(String fxml) {
        Stage stage = openWindows.get(fxml);

        // Verifica si la ventana está abierta
        if (stage != null && stage.isShowing()) {
            stage.close(); // Cierra la ventana
            openWindows.remove(fxml); // Elimina la ventana del mapa
        } else {
            AlertShow.info("No hay ninguna ventana abierta para: " + fxml);
        }
    }

    private static void loadCss(Scene scene) {
        // Aplicar estilos a la escena:
        /*String css = GestionPartesService.getCss();
        if (css != null) scene.getStylesheets().add(css);*/
    }
}