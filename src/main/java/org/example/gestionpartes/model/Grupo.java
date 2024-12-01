package org.example.gestionpartes.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "grupos")
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grupo")
    private int id;
    @Column(name = "nombre_grupo")
    private String nombre;

    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL)
    private List<Alumno> alumnos;

    public Grupo() {
    }

    public Grupo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
