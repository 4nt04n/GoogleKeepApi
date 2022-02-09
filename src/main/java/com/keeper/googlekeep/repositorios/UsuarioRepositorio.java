package com.keeper.googlekeep.repositorios;

import com.keeper.googlekeep.dominios.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepositorio extends CrudRepository<Usuario, Long> {
    Usuario findByEmail(String email);

}
