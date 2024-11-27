package com.Mangoroll.mangoroll.controller;

import com.Mangoroll.mangoroll.model.Usuario;
import com.Mangoroll.mangoroll.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/cadastro")
    public ResponseEntity<Map<String, Object>> cadastrarUsuario(@RequestBody Usuario usuario) {
        Map<String, Object> response = new HashMap<>();

        try {
            Usuario novoUsuario = usuarioService.cadastrar(usuario);
            response.put("success", true);
            response.put("message", "Usuário registrado com sucesso!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Erro ao cadastrar o usuário. Tente novamente.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> autenticarUsuario(@RequestBody Usuario usuario) {
        boolean autenticado = usuarioService.autenticar(usuario.getEmail(), usuario.getSenha());

        Map<String, Object> response = new HashMap<>();
        if (autenticado) {
            response.put("success", true);
            response.put("message", "Login bem-sucedido!");
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "Credenciais inválidas!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
