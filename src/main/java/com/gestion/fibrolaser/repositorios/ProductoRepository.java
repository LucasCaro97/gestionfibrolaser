package com.gestion.fibrolaser.repositorios;

import com.gestion.fibrolaser.entidades.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductoRepository extends JpaRepository<Productos, Integer> {



}