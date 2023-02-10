package com.gestion.fibrolaser.controladores;

import com.gestion.fibrolaser.entidades.Categorias;
import com.gestion.fibrolaser.entidades.Cliente;
import com.gestion.fibrolaser.servicios.CategoriaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;
import java.nio.file.Path;

@Controller
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaServicio categoriaServicio;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ModelAndView getAll(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("tabla-categorias");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) mav.addObject("exito", inputFlashMap.get("exito"));
        mav.addObject("categorias", categoriaServicio.getAll());
        return mav;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/form")
    public ModelAndView getForm(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("form-categorias");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null){
            mav.addObject("categoria", inputFlashMap.get("categoria"));
            mav.addObject("exception", inputFlashMap.get("exception"));
        }else {
            mav.addObject("categoria", new Categorias());
        }
        mav.addObject("action", "create");
        return mav;
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/form/{id}")
    public ModelAndView getFormUpdate(@PathVariable Integer id){
        ModelAndView mav = new ModelAndView("form-categorias");
        mav.addObject("categoria", categoriaServicio.getById(id));
        mav.addObject("action", "update");
        return mav;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public RedirectView create(Categorias dto, @RequestParam("file") MultipartFile imagen, RedirectAttributes attributes){
        RedirectView redirect = new RedirectView("/categorias");
        try{
            if (!imagen.isEmpty()){
                Path directorioImagenes = Paths.get("src//main//resources//static/img");
                String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

                try{
                    byte[] bytesImg = imagen.getBytes();
                    //Path rutaCompleta

                } catch (IOException e){
                    e.printStackTrace();
                }
            }

            categoriaServicio.createCategoria(dto);
            attributes.addFlashAttribute("exito", "La operacion se ha realizado con exito");
        }catch(Exception e){
            attributes.addFlashAttribute("categoria", dto);
            attributes.addFlashAttribute("exception", e.getMessage());
            redirect.setUrl("/categorias/form");
        }

        return redirect;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update")
    public RedirectView update(Categorias dto, RedirectAttributes attributes){
        RedirectView redirect = new RedirectView("/categorias");
        try{
            categoriaServicio.updateCategoria(dto);
            attributes.addFlashAttribute("exito", "La operacion se ha realizado con exito");
        } catch (Exception e){
            attributes.addFlashAttribute("categoria", dto);
            attributes.addFlashAttribute("exception", e.getMessage());
            redirect.setUrl("/categorias/form");
        }
        return redirect;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/delete/{id}")
    public ModelAndView deleteById(@PathVariable Integer id){
        ModelAndView mav = new ModelAndView("redirect:/categorias");
        categoriaServicio.deleteById(id);
        return mav;
    }


}
