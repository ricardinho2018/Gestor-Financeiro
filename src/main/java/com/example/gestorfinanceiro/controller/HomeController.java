package com.example.gestorfinanceiro.controller;

import com.example.gestorfinanceiro.service.DespesaService;
import com.example.gestorfinanceiro.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private ReceitaService receitaService;

    @Autowired
    private DespesaService despesaService;

    @GetMapping("/")
    public String home(Model model) {
        double totalReceitas = receitaService.calcularTotalReceitas();
        double totalDespesas = despesaService.calcularTotalDespesas();
        double saldo = totalReceitas - totalDespesas;

        model.addAttribute("totalReceitas", totalReceitas);
        model.addAttribute("totalDespesas", totalDespesas);
        model.addAttribute("saldo", saldo);

        return "home";
    }
}
