package com.keeper.googlekeep.controller;

import com.keeper.googlekeep.controller.interfaces.INotaController;
import com.keeper.googlekeep.dominios.Nota;
import com.keeper.googlekeep.servicios.ServNota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class NotaController implements INotaController {
    @Autowired
    private ServNota service;

    @GetMapping("/notas/{idUsuario}")
    public List<Nota> traerTodas(@PathVariable String idUsuario) {
        long oIdUsuario = Long.parseLong(idUsuario);

        return service.traerTodas(oIdUsuario);
    }

    @GetMapping("/notasFecha/{idUsuario}")
    public List<Nota> traerTodasPorFecha(@PathVariable String idUsuario) {
        long oIdUsuario = Long.parseLong(idUsuario);
        return service.traerTodasPorFecha(oIdUsuario);
    }

    @PostMapping("/traerTodasPorTags")
    public List<Nota> traerTodasPorTags(@RequestBody Nota pNota) {

        List<Nota> listNotas = service.traerTodas(pNota.getIdUsuario());
        List<Nota> listNotasTags = new ArrayList<>();
        String tagSplited[] = pNota.getTags().trim().split("#");
        tagSplited = Arrays.copyOfRange(tagSplited, 1, tagSplited.length);

        for (Nota item : listNotas) {
            for (String tag : tagSplited) {

                if (item.getTags() != null) {
                    if (item.getTags().contains(tag)) {
                        listNotasTags.add(item);
                    }
                }
            }
        }
        return listNotasTags;
    }

    @PostMapping("/traerTodasPorDescripcion")
    public List<Nota> traerTodasPorDescripcion(@RequestBody Nota pNota) {

        List<Nota> listNotas = service.traerTodas(pNota.getIdUsuario());
        List<Nota> listNotasDescripcion = new ArrayList<>();

        for (Nota item : listNotas) {
            if (item.getDescripcion().contains(pNota.getDescripcion())) {
                listNotasDescripcion.add(item);
            }
        }
        return listNotasDescripcion;
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

        if (!pNota.getTitulo().equals(editNota.getTitulo())) {
            editNota.setTitulo(pNota.getTitulo());
        }
        if (!editNota.getDescripcion().equals(pNota.getDescripcion())) {
            editNota.setDescripcion(pNota.getDescripcion());
        }
        if (!editNota.getTags().equals(pNota.getTags())) {
            editNota.setTags(pNota.getTags());
        }
        if (!editNota.getRecordatorio().equals(pNota.getRecordatorio())) {
            editNota.setRecordatorio(pNota.getRecordatorio());
        }
        service.actualizarNota(editNota);
        return "Nota actualizada con exito";
    }

    @GetMapping("/notasParaHoy/{idUsuario}")
    public List<Nota> notasParaHoy(@PathVariable String idUsuario) {
        long oIdUsuario = Long.parseLong(idUsuario);
        List<Nota> listNota = service.traerTodas(oIdUsuario);
        List<Nota> notasHoy = new ArrayList();
        if (!listNota.isEmpty()) {
            //Uso una operacion lambda para filtrar uno por uno cada nota que tiene un recordatorio null
            listNota = listNota.stream().filter(p -> p.getRecordatorio() != null).collect(Collectors.toList());
        }
        Date fechaHoy = new Date(System.currentTimeMillis());
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");

        for (Nota item : listNota
        ) {

            if (dt.format(item.getRecordatorio()).equals(dt.format(fechaHoy))) {
                notasHoy.add(item);
            }

        }
        return notasHoy;
    }

}


