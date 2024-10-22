package com.practica1.practica1.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_s;

    private int cantidad;

    private int total; // Agregar esta línea

    @ManyToOne
    @JoinColumn(name = "id_p")
    private Productos productos;
}
