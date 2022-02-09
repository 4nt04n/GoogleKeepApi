package com.keeper.googlekeep.servicios;

import com.keeper.googlekeep.dominios.Usuario;
import com.keeper.googlekeep.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServUsuario {
    @Autowired
    private UsuarioRepositorio repo;

    public List<Usuario> traerTodos() {
        return (List<Usuario>) repo.findAll();
    }

    public Optional<Usuario> traerUnico(Long pId) {
        Optional<Usuario> oUsuario = repo.findById(pId);
        return oUsuario;
    }

    public void crearUsuario(Usuario pUsuario) {
        repo.save(pUsuario);
    }

    public void actualizarUsuario(Usuario pUsuario) {

        repo.save(pUsuario);
    }

    public Usuario traerUnicoXEmail(String email) {
        return repo.findByEmail(email);
    }

    public void eliminar(Usuario pUsuario) {
        repo.delete(pUsuario);
    }

}
