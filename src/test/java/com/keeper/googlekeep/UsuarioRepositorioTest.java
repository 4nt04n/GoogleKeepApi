package com.keeper.googlekeep;

import com.keeper.googlekeep.dominios.Usuario;
import com.keeper.googlekeep.repositorios.UsuarioRepositorio;
import com.keeper.googlekeep.servicios.ServUsuario;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace =AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UsuarioRepositorioTest {
    @Autowired
    public ServUsuario service;

    @Test
    public void testNuevoUsuario(){
        Usuario usuario = new Usuario();
        usuario.setEmail("San@gmail.com");
        usuario.setApellido("Sanchez");
        usuario.setNombre("Antonio");
        usuario.setPassword("hola123");
        service.crearUsuario(usuario);
        Usuario usuarioGuardado = service.traerUnicoXEmail(usuario.getEmail());
        Assertions.assertThat(usuarioGuardado).isNotNull();
        Assertions.assertThat(usuarioGuardado.getId()).isGreaterThan(0);
    }
    @Test
    public void testUsuarioXEmail(){
        String email="Sanchez@gmail.com";
        Usuario usuario =new Usuario();
        usuario = service.traerUnicoXEmail(email);

        Assertions.assertThat(usuario.getEmail()).isEqualTo(email);
    }

}
