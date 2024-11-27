package com.Mangoroll.mangoroll.dto;

import java.util.List;

import java.util.Map;

public class JikanResponse {
    private List<AnimeData> data;

    public List<AnimeData> getData() {
        return data;
    }

    public void setData(List<AnimeData> data) {
        this.data = data;
    }

    public static class AnimeData {
        private String title;
        private String synopsis;
        private List<Genre> genres;
        private int episodes;
        private double score;
        private Map<String, Image> images;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSynopsis() {
            return synopsis;
        }

        public void setSynopsis(String synopsis) {
            this.synopsis = synopsis;
        }

        public List<Genre> getGenres() {
            return genres;
        }

        public void setGenres(List<Genre> genres) {
            this.genres = genres;
        }

        public int getEpisodes() {
            return episodes;
        }

        public void setEpisodes(int episodes) {
            this.episodes = episodes;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public Map<String, Image> getImages() {
            return images;
        }

        public void setImages(Map<String, Image> images) {
            this.images = images;
        }
    }

    public static class Genre {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Image {
        private String image_url;

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }
    }
}