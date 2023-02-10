package com.gestion.fibrolaser.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "categorias")
public class Categorias {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_categoria")
    private Integer id;
    @Column(name = "nombre_categoria")
    private String nombre;
    @Column(name = "categoria_img")
    private String categoria_img;
    @Column(name ="alta_categoria")
    private Boolean alta;


}
