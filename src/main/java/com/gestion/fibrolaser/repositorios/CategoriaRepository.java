package com.gestion.fibrolaser.repositorios;

import com.gestion.fibrolaser.entidades.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categorias, Integer> {




}