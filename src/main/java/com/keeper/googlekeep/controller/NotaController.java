package com.keeper.googlekeep.controller;

import com.keeper.googlekeep.dominios.Nota;
import com.keeper.googlekeep.dominios.Usuario;
import com.keeper.googlekeep.servicios.ServNota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class NotaController {
    @Autowired
    private ServNota service;

    @GetMapping("/notas/{idUsuario}")
    public List<Nota> traerTodas(@PathVariable String idUsuario) {
        long oIdUsuario = Long.parseLong(idUsuario);

        return service.traerTodas(oIdUsuario);
    }

    @GetMapping("/nota/{id}")
    public Nota traerUnica(@PathVariable String id) {
        long oId = Long.parseLong(id);
        return service.traerUnica(oId);
    }

    @DeleteMapping("/eliminarNota")
    public String eliminarNota(@RequestBody Nota pNota) {
        if (pNota.getId() != null) {
            Nota oNota = service.traerUnica(pNota.getId());
            if (oNota != null)
                service.eliminar(oNota);
            return "Se elimin vo el Nota con exito";
        } else {
            return "Se requiere  el Id de la nota";
        }
    }

    @PostMapping("/crearNota")
    public String crearNota(@RequestBody Nota pNota) {
        if (pNota.getDescripcion() != null && pNota.getTitulo() != null && pNota.getIdUsuario() != null) {
            Date date = new Date(System.currentTimeMillis());
            pNota.setFecha(date);
            service.crearNota(pNota);
            return "Nota Creada con exito";
        } else
            return "Faltan Datos";
    }

    @PostMapping("/actualizarNota")
    public String actualizarNota(@RequestBody Nota pNota) {
        Nota editNota = service.traerUnica(pNota.getId());
        if (editNota == null) {
            return "No se encontro la nota";
        }

        if (pNota.getTitulo().equals(editNota.getTitulo()) ) {
            editNota.setTitulo(pNota.getTitulo());
        }
        if (pNota.getDescripcion().equals(pNota.getDescripcion()) ) {
            editNota.setDescripcion(pNota.getDescripcion());
        }
        service.actualizarNota(editNota);
        return "Nota actualizada con exito";


    }


}


