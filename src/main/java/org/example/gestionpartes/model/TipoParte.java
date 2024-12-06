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
}
