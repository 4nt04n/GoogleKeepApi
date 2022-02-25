package com.keeper.googlekeep.servicios.interfaces;

import com.keeper.googlekeep.dominios.Nota;

import java.util.List;

public interface IServNota {
    public List<Nota> traerTodas(long pIdUsuario);

    public List<Nota> traerTodasPorFecha(long pIdUsuario);

    public Nota traerUnica(long pId);

    public void eliminar(Nota pNota);

    public void crearNota(Nota pNota);

    public void actualizarNota(Nota pNota);
}
