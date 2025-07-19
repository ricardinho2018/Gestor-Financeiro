package com.example.gestorfinanceiro.controller;

import com.example.gestorfinanceiro.model.Despesa;
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

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("despesas", despesaRepository.findAll());
        return "despesas/list";
    }

    @GetMapping("/nova")
    public String novaForm(Model model) {
        model.addAttribute("despesa", new Despesa());
        return "despesas/form";
    }

    @PostMapping
    public String salvar(@ModelAttribute Despesa despesa) {
        despesaRepository.save(despesa);
        return "redirect:/despesas";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        Despesa d = despesaRepository.findById(id).orElseThrow();
        model.addAttribute("despesa", d);
        return "despesas/form";
    }

    @PostMapping("/editar/{id}")
    public String atualizar(@PathVariable Long id, @ModelAttribute Despesa despesa) {
        despesa.setId(id);
        despesaRepository.save(despesa);
        return "redirect:/despesas";
    }

    @GetMapping("/apagar/{id}")
    public String apagar(@PathVariable Long id) {
        despesaRepository.deleteById(id);
        return "redirect:/despesas";
    }
}
