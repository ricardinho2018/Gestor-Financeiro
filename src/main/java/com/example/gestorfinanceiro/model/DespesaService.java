package com.example.gestorfinanceiro.service;

import com.example.gestorfinanceiro.model.Despesa;
import com.example.gestorfinanceiro.repository.DespesaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DespesaService {

    private final DespesaRepository despesaRepository;

    public DespesaService(DespesaRepository despesaRepository) {
        this.despesaRepository = despesaRepository;
    }

    public List<Despesa> findAll() {
        return despesaRepository.findAll();
    }

    public Despesa findById(Long id) {
        return despesaRepository.findById(id).orElse(null);
    }

    public Despesa save(Despesa despesa) {
        return despesaRepository.save(despesa);
    }

    public void deleteById(Long id) {
        despesaRepository.deleteById(id);
    }

    public double calcularTotalDespesas() {
        return despesaRepository.findAll().stream()
                .mapToDouble(Despesa::getValor)
                .sum();
    }
}
