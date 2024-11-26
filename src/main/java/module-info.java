module org.example.gestionpartes {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires mysql.connector.j;

    opens ui;
    opens configuration;

    opens org.example.gestionpartes.model;
    exports org.example.gestionpartes.model;

    opens org.example.gestionpartes to javafx.fxml;
    exports org.example.gestionpartes;
    exports org.example.gestionpartes.controller;
    opens org.example.gestionpartes.controller to javafx.fxml;
}