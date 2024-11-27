package com.Mangoroll.mangoroll.service;

import com.Mangoroll.mangoroll.model.Usuario;
import com.Mangoroll.mangoroll.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario cadastrar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public boolean autenticar(String identificador, String senha) {
       
        Optional<Usuario> usuario = usuarioRepository.findByEmail(identificador);


        if (!usuario.isPresent()) {
            usuario = usuarioRepository.findByNomeUsuario(identificador);
        }

      
        return usuario.isPresent() && usuario.get().getSenha().equals(senha);
    }
}
