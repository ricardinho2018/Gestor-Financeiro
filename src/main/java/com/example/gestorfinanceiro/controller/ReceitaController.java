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
    public String listar(Model model) {
        model.addAttribute("receitas", receitaRepository.findAll());
        return "receitas/list";
    }

    @GetMapping("/nova")
    public String novaForm(Model model) {
        model.addAttribute("receita", new Receita());
        return "receitas/form";
    }

    @PostMapping
    public String salvar(@ModelAttribute Receita receita) {
        receitaRepository.save(receita);
        return "redirect:/receitas";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        Receita r = receitaRepository.findById(id).orElseThrow();
        model.addAttribute("receita", r);
        return "receitas/form";
    }

    @PostMapping("/editar/{id}")
    public String atualizar(@PathVariable Long id, @ModelAttribute Receita receita) {
        receita.setId(id);
        receitaRepository.save(receita);
        return "redirect:/receitas";
    }

    @GetMapping("/apagar/{id}")
    public String apagar(@PathVariable Long id) {
        receitaRepository.deleteById(id);
        return "redirect:/receitas";
    }
}
