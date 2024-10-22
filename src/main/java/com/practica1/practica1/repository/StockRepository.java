package com.practica1.practica1.repository;

import com.practica1.practica1.entidades.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Integer> {
}
