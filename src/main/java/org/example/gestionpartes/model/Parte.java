package org.example.gestionpartes.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "partes_incidencia")
public class Parte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parte")
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_alum", referencedColumnName = "id_alum")
    private Alumno alumno;
    @ManyToOne
    @JoinColumn(name = "id_profesor", referencedColumnName = "id_profesor")
    private Profesor profesor;
    private String descripcion;
    private LocalDate fecha;
    private LocalTime hora;
    private String sancion;
    @ManyToOne
    @JoinColumn(name = "id_tipo_parte", referencedColumnName = "id_tipo")
    private TipoParte tipo;

    public Parte() {
    }

    public Parte(Alumno alumno, Profesor profesor, String descripcion, LocalDate fecha, LocalTime hora, String sancion, TipoParte tipo) {
        this.alumno = alumno;
        this.profesor = profesor;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.hora = hora;
        this.sancion = sancion;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getSancion() {
        return sancion;
    }

    public void setSancion(String sancion) {
        this.sancion = sancion;
    }

    public TipoParte getTipo() {
        return tipo;
    }

    public void setTipo(TipoParte tipo) {
        this.tipo = tipo;
    }
}
