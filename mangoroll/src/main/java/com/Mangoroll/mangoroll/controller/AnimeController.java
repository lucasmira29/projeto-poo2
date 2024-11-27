package com.Mangoroll.mangoroll.controller;

import com.Mangoroll.mangoroll.model.Anime;
import com.Mangoroll.mangoroll.service.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/animes")
public class AnimeController {

    @Autowired
    private AnimeService animeService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> criarAnime(@RequestBody String titulo) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            Anime novoAnime = animeService.buscarAnimePorTitulo(titulo);

            response.put("success", true);
            response.put("message", "Anime cadastrado com sucesso!");
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Erro ao cadastrar o anime. Tente novamente.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping
    public List<Anime> listarTodos() {
        return animeService.listarTodos();
    }
}
