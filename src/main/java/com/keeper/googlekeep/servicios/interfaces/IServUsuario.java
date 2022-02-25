package com.keeper.googlekeep.servicios.interfaces;

import com.keeper.googlekeep.dominios.Usuario;

import java.util.List;
import java.util.Optional;

public interface IServUsuario {
    public List<Usuario> traerTodos();

    public Optional<Usuario> traerUnico(Long pId);

    public void crearUsuario(Usuario pUsuario);

    public void actualizarUsuario(Usuario pUsuario);

    public Usuario traerUnicoXEmail(String email);

    public void eliminar(Usuario pUsuario);
}
