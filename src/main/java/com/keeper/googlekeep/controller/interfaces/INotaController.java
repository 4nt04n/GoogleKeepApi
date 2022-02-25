package com.keeper.googlekeep.controller.interfaces;

import com.keeper.googlekeep.dominios.Nota;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface INotaController {
    public List<Nota> traerTodas(@PathVariable String idUsuario);

    public List<Nota> traerTodasPorFecha(@PathVariable String idUsuario);

    public List<Nota> traerTodasPorTags(@RequestBody Nota pNota);

    public List<Nota> traerTodasPorDescripcion(@RequestBody Nota pNota);

    public Nota traerUnica(@PathVariable String id);

    public String eliminarNota(@RequestBody Nota pNota);

    public String crearNota(@RequestBody Nota pNota);

    public String actualizarNota(@RequestBody Nota pNota);

    public List<Nota> notasParaHoy(@PathVariable String idUsuario);

}
