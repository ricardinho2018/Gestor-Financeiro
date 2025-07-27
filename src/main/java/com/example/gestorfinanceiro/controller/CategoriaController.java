package com.example.gestorfinanceiro.controller;

import com.example.gestorfinanceiro.model.Categoria;
import com.example.gestorfinanceiro.service.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/listar")
    public String listarCategorias(Model model) {
        model.addAttribute("categorias", categoriaService.findAll());
        return "categorias/lista";
    }

    @GetMapping("/novo")
    public String novoFormulario(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categorias/formulario";
    }

    @PostMapping("/salvar")
    public String salvarCategoria(@ModelAttribute Categoria categoria) {
        categoriaService.save(categoria);
        return "redirect:/categorias/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarFormulario(@PathVariable Long id, Model model) {
        Categoria categoria = categoriaService.findById(id);
        if (categoria == null) {
            return "redirect:/categorias/listar";
        }
        model.addAttribute("categoria", categoria);
        return "categorias/formulario";
    }

    @GetMapping("/apagar/{id}")
    public String apagarCategoria(@PathVariable Long id) {
        categoriaService.deleteById(id);
        return "redirect:/categorias/listar";
    }
}
