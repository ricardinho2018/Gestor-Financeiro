package com.example.gestorfinanceiro.repository;

import com.example.gestorfinanceiro.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {}
