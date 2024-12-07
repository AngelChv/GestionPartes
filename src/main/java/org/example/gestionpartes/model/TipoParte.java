package org.example.gestionpartes.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tipos_parte")
public class TipoParte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo")
    private int id;
    @Enumerated(EnumType.STRING) // sirve para indicar a hibernate que mapee los valores en lugar de indice numerico
    private ColorParte color;
    private int puntos;
    @OneToMany(mappedBy = "tipo", cascade = CascadeType.ALL)
    private Set<Parte> partes;

    public TipoParte() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ColorParte getColor() {
        return color;
    }

    public void setColor(ColorParte color) {
        this.color = color;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public Set<Parte> getPartes() {
        return partes;
    }

    public void setPartes(Set<Parte> partes) {
        this.partes = partes;
    }
}
