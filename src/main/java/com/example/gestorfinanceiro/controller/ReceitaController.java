package com.example.gestorfinanceiro.controller;

import com.example.gestorfinanceiro.model.Receita;
import com.example.gestorfinanceiro.repository.ReceitaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/receitas")
public class ReceitaController {
    private final ReceitaRepository receitaRepository;

    public ReceitaController(ReceitaRepository receitaRepository) {
        this.receitaRepository = receitaRepository;
    }

    @GetMapping
    public String listar(Model model) {
        List<Receita> receitas = receitaRepository.findAll();
        model.addAttribute("receitas", receitas);
        return "receitas/list";
    }

    @GetMapping("/nova")
    public String novaReceita(Model model) {
        model.addAttribute("receita", new Receita());
        return "receitas/form";
    }

    @PostMapping("/save")
    public String salvarReceita(@ModelAttribute Receita receita) {
        receitaRepository.save(receita);
        return "redirect:/receitas";
    }

    @GetMapping("/editar/{id}")
    public String editarReceita(@PathVariable Long id, Model model) {
        Receita receita = receitaRepository.findById(id).orElseThrow();
        model.addAttribute("receita", receita);
        return "receitas/form";
    }

    @GetMapping("/apagar/{id}")
    public String apagarReceita(@PathVariable Long id) {
        receitaRepository.deleteById(id);
        return "redirect:/receitas";
    }
}
