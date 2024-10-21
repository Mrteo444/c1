package com.practica1.practica1.repository;

import com.practica1.practica1.entidades.Productos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TablaProducRepository extends JpaRepository<Productos, Integer> {
}
