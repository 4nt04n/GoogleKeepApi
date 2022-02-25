package com.keeper.googlekeep.controller;

import com.keeper.googlekeep.controller.interfaces.IUsuarioController;
import com.keeper.googlekeep.dominios.Usuario;
import com.keeper.googlekeep.servicios.ServUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController implements IUsuarioController {
    @Autowired
    private ServUsuario service;

    @GetMapping("/usuarios")
    public List<Usuario> mostrarListaUsuarios() {

        return service.traerTodos();
    }

    //Api de loguin Recibe un json Ej: {"email":"sanchez@gmail.com","password":"hola123"}
    @PostMapping(value = "/login", consumes = "application/json")
    public String login(@RequestBody Usuario pUsuario) {
        Usuario usuario = service.traerUnicoXEmail(pUsuario.getEmail());
        String contraUsuario = usuario.getPassword().trim();
        if (contraUsuario.equals(pUsuario.getPassword().trim())) {
            return "<h1>Se a iniciado Secion</h1>";
        } else {
            return "Contrasenia o mail incorrecto";
        }
    }

    //Requiere el email o el id para eliminar el usuario
    @DeleteMapping("/eliminarUsuario")
    public String eliminarUsuario(@RequestBody Usuario pUsuario) {
        if (pUsuario.getId() != null) {
            Optional<Usuario> oUsuario = service.traerUnico(pUsuario.getId());
            if (oUsuario.isPresent())
                service.eliminar(oUsuario.get());
            return "Se elimin vo el usuario con exito";
        } else if (pUsuario.getEmail() != null) {
            Usuario oUsuario = service.traerUnicoXEmail(pUsuario.getEmail());
            service.eliminar(oUsuario);
            return "Se elimino el usuario con exito";
        } else {
            return "Se requiere el email o el Id del usuario";
        }
    }

    @PostMapping(value = "/nuevoUsuario", consumes = "application/json")
    public String nuevoUsuario(@RequestBody Usuario pUsuario) {


        if (pUsuario.getApellido() != null && pUsuario.getNombre() != null && pUsuario.getEmail() != null && pUsuario.getPassword() != null) {
            //Busca si hay un usuario con este mail
            Usuario enUso = service.traerUnicoXEmail(pUsuario.getEmail());
            String prueba = Usuario.prueba;
            if (enUso != null) {
                return "Error, El USUARIO ESTA EN USO";

            } else {
                service.crearUsuario(pUsuario);
                return "Creacion Exitosa";
            }
        } else {
            return "Error,FALTAN DATOS";
        }
    }

    //Requiere el Id del usuario para poder cambiar los valores || El email no se puede cambiar
    @PostMapping(value = "/actualizarUsuario", consumes = "application/json")
    public String actualizarUsuario(@RequestBody Usuario pUsuario) {
        Optional<Usuario> oUsuario = service.traerUnico(pUsuario.getId());
        try {
            if (oUsuario.isPresent()) {
                Usuario editUsuario = oUsuario.get();
                if (pUsuario.getPassword() != null) {
                    editUsuario.setPassword(pUsuario.getPassword());
                }
                if (pUsuario.getNombre() != null) {
                    editUsuario.setNombre(pUsuario.getNombre());
                }
                if (pUsuario.getNombre() != null) {
                    editUsuario.setNombre(pUsuario.getNombre());
                }
                if (pUsuario.getApellido() != null) {
                    editUsuario.setApellido(pUsuario.getApellido());
                }
                service.actualizarUsuario(editUsuario);
                return "Usuario actualizado con exito";

            }
            return "No se encontro el Usuario";
        } catch (Exception e) {

            return "Ocurrio un Error";
        }
    }

}
