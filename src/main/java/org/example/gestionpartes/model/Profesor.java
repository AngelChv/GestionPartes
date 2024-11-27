package org.example.gestionpartes.model;

import jakarta.persistence.*;

@Entity
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
    @Column(name = "tipo")
    TipoProfesor tipoProfesor;

    public Profesor() {}

    public Profesor(int id, String password, String nombre, String numAsignado, TipoProfesor tipoProfesor) {
        this.id = id;
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
