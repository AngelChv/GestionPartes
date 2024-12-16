module org.example.gestionpartes {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires mysql.connector.j;
    requires org.apache.commons.codec;

    opens ui;
    opens configuration;
    opens style;

    opens org.example.gestionpartes.model;
    exports org.example.gestionpartes.model;

    opens org.example.gestionpartes;
    exports org.example.gestionpartes;
    exports org.example.gestionpartes.controller;
    opens org.example.gestionpartes.controller;
}