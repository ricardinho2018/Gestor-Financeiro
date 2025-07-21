package com.example.gestorfinanceiro.controller;

import com.example.gestorfinanceiro.model.Despesa;
import com.example.gestorfinanceiro.repository.CategoriaRepository;
import com.example.gestorfinanceiro.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/despesas")
public class DespesaController {

    @Autowired
    private DespesaRepository despesaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public String listarDespesas(Model model) {
        model.addAttribute("despesas", despesaRepository.findAll());
        return "despesas/list";
    }

    @GetMapping("/nova")
    public String novaDespesaForm(Model model) {
        model.addAttribute("despesa", new Despesa());
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "despesas/form";
    }

    @PostMapping("/salvar")
    public String salvarDespesa(@ModelAttribute Despesa despesa) {
        if (despesa.getCategoria() == null || despesa.getCategoria().getId() == null) {
            throw new RuntimeException("Categoria inválida!");
        }
        despesaRepository.save(despesa);
        return "redirect:/despesas";
    }
}
