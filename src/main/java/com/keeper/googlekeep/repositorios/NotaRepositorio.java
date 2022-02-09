package com.keeper.googlekeep.repositorios;

import com.keeper.googlekeep.dominios.Nota;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NotaRepositorio extends CrudRepository<Nota, Long> {
    List<Nota> findAllByIdUsuarioOrderByFechaDesc(long pId);
}
