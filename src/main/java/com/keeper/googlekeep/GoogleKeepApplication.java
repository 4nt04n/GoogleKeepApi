package com.keeper.googlekeep;

import com.keeper.googlekeep.dominios.Usuario;
import com.keeper.googlekeep.repositorios.UsuarioRepositorio;
import com.keeper.googlekeep.servicios.ServUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
public class GoogleKeepApplication  {
    @Autowired
    private ServUsuario service;

    public static void main(String[] args) {
        SpringApplication.run(GoogleKeepApplication.class, args);
    }


}
