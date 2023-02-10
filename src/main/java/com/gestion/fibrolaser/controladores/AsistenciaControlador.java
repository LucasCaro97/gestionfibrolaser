package com.gestion.fibrolaser.controladores;

import com.gestion.fibrolaser.entidades.Asistencia;
import com.gestion.fibrolaser.servicios.AsistenciaServicio;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/asistencias")
@RequiredArgsConstructor
public class AsistenciaControlador {

    private final AsistenciaServicio asistenciaServicio;

    @GetMapping
    public ModelAndView getAll(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("asistencia");
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);

        if (inputFlashMap != null) mav.addObject("exito", inputFlashMap.get("exito"));

        mav.addObject("asistencias", asistenciaServicio.getAll());

        return mav;

    }

    @GetMapping("/entrada")
    public RedirectView entrada(RedirectAttributes attributes, HttpSession session){
        RedirectView redirect = new RedirectView("/asistencias");

        try{
            System.out.println("entre al try");
            asistenciaServicio.marcarLlegada(session);
            attributes.addFlashAttribute("exito", "Se ha realizado la operacion con exito");
        }catch (Exception e){
            attributes.addFlashAttribute("exception", "Error");
        }
    return redirect;
    }

    @GetMapping("/salida")
    public RedirectView salida(RedirectAttributes attributes, HttpSession session){
        RedirectView redirect = new RedirectView("/asistencias");

        try{
            asistenciaServicio.marcarSalida(session);
            attributes.addFlashAttribute("exito", "Se ha realizado la operacion con exito");
        }catch (Exception e){
            attributes.addFlashAttribute("exception", "Error");
        }
        return redirect;
    }



}
