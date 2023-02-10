package com.gestion.fibrolaser.repositorios;

import com.gestion.fibrolaser.entidades.Asistencia;
import com.gestion.fibrolaser.entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Integer> {

    @Query( value= "SELECT * FROM asistencia WHERE id_asistencia = (SELECT MAX(id_asistencia) FROM asistencia )", nativeQuery = true)
    Asistencia findLast();




}