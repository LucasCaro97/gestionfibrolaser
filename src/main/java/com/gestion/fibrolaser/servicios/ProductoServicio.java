package com.gestion.fibrolaser.servicios;

import com.gestion.fibrolaser.entidades.Productos;
import com.gestion.fibrolaser.repositorios.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServicio {

    private final ProductoRepository productoRepository;

    @Transactional
    public void createProducto(Productos dto){
        Productos productos = new Productos();
        productos.setNombre(dto.getNombre());
        productos.setCategoria(dto.getCategoria());
        productos.setProducto_img(dto.getProducto_img());
        productoRepository.save(productos);

    }

    @Transactional(readOnly = true)
    public List<Productos> getAll(){ return productoRepository.findAll(); }

    @Transactional(readOnly = true)
    public Productos getById(Integer id){ return productoRepository.findById(id).get(); }

    @Transactional
    public void deleteById(Integer id){ productoRepository.deleteById(id);}

    @Transactional
    public void updateProductos(Productos dto){
        Productos productos = productoRepository.findById(dto.getId()).get();
        productos.setNombre(dto.getNombre());
        productos.setCategoria(dto.getCategoria());
        productos.setProducto_img(dto.getProducto_img());
        productoRepository.save(productos);
    }
}
