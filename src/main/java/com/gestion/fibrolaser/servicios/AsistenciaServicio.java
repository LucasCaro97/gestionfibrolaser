package com.gestion.fibrolaser.servicios;

import com.gestion.fibrolaser.entidades.Asistencia;
import com.gestion.fibrolaser.entidades.Usuario;
import com.gestion.fibrolaser.repositorios.AsistenciaRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AsistenciaServicio {

    private final AsistenciaRepository asistenciaRepository;
    private final UsuarioServicio usuarioServicio;

    @Transactional
    public void marcarLlegada(HttpSession session){
        Asistencia asist = new Asistencia();
        asist.setUsuario(usuarioServicio.getById(Integer.parseInt(session.getAttribute("id").toString())));
        asist.setEntrada(LocalDateTime.now());
        asistenciaRepository.save(asist);

    }

    @Transactional
    public void marcarSalida(HttpSession session){

        Asistencia asist = asistenciaRepository.findLast();
        asist.setSalida(LocalDateTime.now());
        asistenciaRepository.save(asist);

    }

    @Transactional(readOnly = true)
    public List<Asistencia> getAll(){ return asistenciaRepository.findAll();    }


}
