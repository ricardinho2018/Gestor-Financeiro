package com.example.gestorfinanceiro.service;

import com.example.gestorfinanceiro.model.Receita;
import com.example.gestorfinanceiro.repository.ReceitaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceitaService {

    private final ReceitaRepository receitaRepository;

    public ReceitaService(ReceitaRepository receitaRepository) {
        this.receitaRepository = receitaRepository;
    }

    public List<Receita> findAll() {
        return receitaRepository.findAll();
    }

    public Receita findById(Long id) {
        return receitaRepository.findById(id).orElse(null);
    }

    public Receita save(Receita receita) {
        return receitaRepository.save(receita);
    }

    public void deleteById(Long id) {
        receitaRepository.deleteById(id);
    }

    public double calcularTotalReceitas() {
        return receitaRepository.findAll().stream()
                .mapToDouble(Receita::getValor)
                .sum();
    }
}
