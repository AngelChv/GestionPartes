package org.example.gestionpartes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.gestionpartes.service.GestionPartesService;
import org.example.gestionpartes.util.HibernateUtil;
import org.example.gestionpartes.util.R;

import java.io.IOException;

public class GestionPartesApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(R.getUI("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Gestión de Partes");
        // Aplicar estilos.
        GestionPartesService.setCss(R.getUrl("style/light-theme.css").toExternalForm());
        scene.getStylesheets().add(GestionPartesService.getCss());
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        HibernateUtil.closeSessionFactory();
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }
}