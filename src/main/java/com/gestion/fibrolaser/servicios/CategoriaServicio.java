package com.gestion.fibrolaser.servicios;

import com.gestion.fibrolaser.entidades.Categorias;
import com.gestion.fibrolaser.repositorios.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaServicio {

    private final CategoriaRepository categoriaRepository;

    @Transactional
    public void createCategoria(Categorias dto){
        Categorias categorias = new Categorias();
        categorias.setNombre(dto.getNombre());
        categorias.setCategoria_img(dto.getCategoria_img());
        categoriaRepository.save(categorias);
    }

    @Transactional(readOnly = true)
    public List<Categorias> getAll(){ return categoriaRepository.findAll();   }

    @Transactional(readOnly = true)
    public Categorias getById(Integer id){ return categoriaRepository.findById(id).get(); }

    @Transactional
    public void deleteById(Integer id){  categoriaRepository.deleteById(id);}

    @Transactional
    public void updateCategoria(Categorias dto){
        Categorias categorias = categoriaRepository.findById(dto.getId()).get();
        categorias.setNombre(dto.getNombre());
        categorias.setCategoria_img(dto.getCategoria_img());
        categoriaRepository.save(categorias);
    }
}
