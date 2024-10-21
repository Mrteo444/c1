package com.practica1.practica1.entidades;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_p;
    private String nombre;
    private double precio ;
    private int stock;
}
