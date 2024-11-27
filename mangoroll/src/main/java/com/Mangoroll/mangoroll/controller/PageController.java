package com.Mangoroll.mangoroll.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Bem-vindo ao Mangoroll!");
        return "index"; 
    }

    @GetMapping("/anime-register")
    public String animeRegister(Model model) {
        model.addAttribute("message", "Cadastre um novo anime aqui!");
        return "anime-register"; 
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("message", "Faça login na sua conta!");
        return "login"; 
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("message", "Crie sua conta agora mesmo!");
        return "register"; 
    }
}
