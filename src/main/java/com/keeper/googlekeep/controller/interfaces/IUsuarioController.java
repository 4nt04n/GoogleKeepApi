package com.keeper.googlekeep.controller.interfaces;

import com.keeper.googlekeep.dominios.Usuario;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IUsuarioController {
    public List<Usuario> mostrarListaUsuarios();

    public String login(@RequestBody Usuario pUsuario);

    public String eliminarUsuario(@RequestBody Usuario pUsuario);

    public String nuevoUsuario(@RequestBody Usuario pUsuario);

    public String actualizarUsuario(@RequestBody Usuario pUsuario);
}
