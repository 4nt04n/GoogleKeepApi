package com.keeper.googlekeep;

import com.keeper.googlekeep.dominios.Usuario;
import com.keeper.googlekeep.repositorios.UsuarioRepositorio;
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
    private UsuarioRepositorio repo;

    @Test
    public void testNuevoUsuario(){
        Usuario usuario = new Usuario();
        usuario.setEmail("Sanchez@gmail.com");
        usuario.setApellido("Sanchez");
        usuario.setNombre("Antonio");
        usuario.setPassword("hola123");

        Usuario usuarioGuardado = repo.save(usuario);
        Assertions.assertThat(usuarioGuardado).isNotNull();
        Assertions.assertThat(usuarioGuardado.getId()).isGreaterThan(0);
    }
    @Test
    public void testUsuarioXEmail(){
        String email="Sanchez@gmail.com";
        Usuario usuario =new Usuario();
        usuario = repo.findByEmail(email);

        Assertions.assertThat(usuario.getEmail()).isEqualTo(email);
    }

}
