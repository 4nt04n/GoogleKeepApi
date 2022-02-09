package com.keeper.googlekeep.servicios;

import com.keeper.googlekeep.dominios.Nota;
import com.keeper.googlekeep.dominios.Usuario;
import com.keeper.googlekeep.repositorios.NotaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServNota {
    @Autowired
    private NotaRepositorio repo;

    public List<Nota> traerTodas(long pIdUsuario) {
        return repo.findAllByIdUsuarioOrderByFechaDesc(pIdUsuario);
    }

    public Nota traerUnica(long pId) {
        Optional<Nota> oNota = repo.findById(pId);
        if (oNota.isPresent())
            return oNota.get();
        else
            return null;
    }

    public void eliminar(Nota pNota) {
        repo.delete(pNota);
    }

    public void crearNota(Nota pNota) {
        repo.save(pNota);
    }

    public void actualizarNota(Nota pNota) {
        repo.save(pNota);
    }
}
