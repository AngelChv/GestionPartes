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
}
