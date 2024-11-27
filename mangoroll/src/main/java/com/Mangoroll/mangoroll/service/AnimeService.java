package com.Mangoroll.mangoroll.service;

import com.Mangoroll.mangoroll.dto.JikanResponse;
import com.Mangoroll.mangoroll.model.Anime;
import com.Mangoroll.mangoroll.repository.AnimeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AnimeService {
    private final AnimeRepository animeRepository;
    private final RestTemplate restTemplate;

    public AnimeService(AnimeRepository animeRepository, RestTemplate restTemplate) {
        this.animeRepository = animeRepository;
        this.restTemplate = restTemplate;
    }

    public List<Anime> listarTodos() {
        return animeRepository.findAll();
    }

    public Anime buscarAnimePorTitulo(String titulo) {
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("O título não pode ser nulo ou vazio.");
        }


        titulo = sanitizeInput(titulo);

        String url = "https://api.jikan.moe/v4/anime?q={titulo}&limit=1";

        JikanResponse response = restTemplate.getForObject(url, JikanResponse.class, titulo);

        if (response != null && response.getData() != null && !response.getData().isEmpty()) {
           
            JikanResponse.AnimeData animeData = response.getData().get(0);

            Anime anime = new Anime();
            anime.setTitulo(animeData.getTitle());
            anime.setDescricao(animeData.getSynopsis());

            
            if (animeData.getGenres() != null && !animeData.getGenres().isEmpty()) {
                anime.setGenero(animeData.getGenres().get(0).getName()); 
            } else {
                anime.setGenero("Desconhecido");
            }

            anime.setEpisodios(animeData.getEpisodes());
            anime.setNota(animeData.getScore());

            if (animeData.getImages() != null && animeData.getImages().containsKey("jpg")) {
                anime.setImagemUrl(animeData.getImages().get("jpg").getImage_url());
            } else if (animeData.getImages() != null && animeData.getImages().containsKey("webp")) {
                anime.setImagemUrl(animeData.getImages().get("webp").getImage_url());
            } else {
                anime.setImagemUrl(null); // Ou definir uma URL padrão
            }
    
            return animeRepository.save(anime);
        } else {
            throw new RuntimeException("Anime não encontrado.");
        }
    }

    private String sanitizeInput(String input) {
        if (input == null) return null;
        
        return input.replaceAll("[\\n\\r\\t]", "").trim();
    }
}