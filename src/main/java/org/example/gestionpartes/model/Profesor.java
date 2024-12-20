package org.example.gestionpartes.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "profesores")
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profesor")
    int id;
    @Column(name = "contrasena")
    String password;
    @Column(name = "nombre")
    String nombre;
    @Column(name = "numero_asignado")
    String numAsignado;
    @Enumerated(EnumType.STRING) // sirve para indicar a hibernate que mapee los valores en lugar de indice numerico
    @Column(name = "tipo")
    TipoProfesor tipoProfesor;
    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL)
    private Set<Parte> partes;

    public Profesor() {}

    public Profesor(String password, String nombre, String numAsignado, TipoProfesor tipoProfesor) {
        this.password = password;
        this.nombre = nombre;
        this.numAsignado = numAsignado;
        this.tipoProfesor = tipoProfesor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumAsignado() {
        return numAsignado;
    }

    public void setNumAsignado(String numAsignado) {
        this.numAsignado = numAsignado;
    }

    public TipoProfesor getTipoProfesor() {
        return tipoProfesor;
    }

    public void setTipoProfesor(TipoProfesor tipoProfesor) {
        this.tipoProfesor = tipoProfesor;
    }
}
