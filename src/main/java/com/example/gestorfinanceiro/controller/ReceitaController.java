package com.example.gestorfinanceiro.controller;

import com.example.gestorfinanceiro.model.Receita;
import com.example.gestorfinanceiro.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaRepository receitaRepository;

    @GetMapping
    public String listarReceitas(Model model) {
        model.addAttribute("receitas", receitaRepository.findAll());
        return "receitas/list";
    }

    @GetMapping("/nova")
    public String novaReceitaForm(Model model) {
        model.addAttribute("receita", new Receita());
        return "receitas/form";
    }

    @PostMapping
    public String salvarReceita(@ModelAttribute Receita receita) {
        receitaRepository.save(receita);
        return "redirect:/receitas";
    }
}
