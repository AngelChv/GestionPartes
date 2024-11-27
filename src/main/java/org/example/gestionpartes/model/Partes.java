package org.example.gestionpartes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
@Entity
@Table
public class Partes {
    @Id
    @Column(name = "id_alumn")
    Integer id_alum;

    @Column(name = "id_grupo")
    Integer id_grupo;

    @Column(name = "id_parte")
    Integer id_parte;

    @Column(name = "id_profesor")
    Integer id_profesor;

    @Column(name = "id_punt_partes")
    Integer id_punt_partes;

    @Column(name = "descripcion")
    String descripcion;

    @Column(name = "fecha")
    LocalDateTime fecha;

    @Column(name = "hora")
    String hora;

    @Column(name = "sancion")
    String sancion;

    public Partes(Integer id_alum, Integer id_grupo, Integer id_parte, Integer id_profesor, Integer id_punt_partes, String descripcion, LocalDateTime fecha, String hora, String sancion) {
        this.id_alum = id_alum;
        this.id_grupo = id_grupo;
        this.id_parte = id_parte;
        this.id_profesor = id_profesor;
        this.id_punt_partes = id_punt_partes;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.hora = hora;
        this.sancion = sancion;
    }

    public Integer getId_alum() {
        return id_alum;
    }

    public void setId_alum(Integer id_alum) {
        this.id_alum = id_alum;
    }

    public Integer getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(Integer id_grupo) {
        this.id_grupo = id_grupo;
    }

    public Integer getId_parte() {
        return id_parte;
    }

    public void setId_parte(Integer id_parte) {
        this.id_parte = id_parte;
    }

    public Integer getId_profesor() {
        return id_profesor;
    }

    public void setId_profesor(Integer id_profesor) {
        this.id_profesor = id_profesor;
    }

    public Integer getId_punt_partes() {
        return id_punt_partes;
    }

    public void setId_punt_partes(Integer id_punt_partes) {
        this.id_punt_partes = id_punt_partes;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getSancion() {
        return sancion;
    }

    public void setSancion(String sancion) {
        this.sancion = sancion;
    }
}
