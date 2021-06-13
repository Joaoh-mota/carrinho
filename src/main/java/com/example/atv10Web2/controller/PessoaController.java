/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.atv10Web2.controller;

import com.example.atv10Web2.model.Pessoa;
import com.example.atv10Web2.model.PessoaFisica;
import com.example.atv10Web2.repository.PessoaFisicaRepository;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Transactional
@Controller
@RequestMapping("pessoas")
public class PessoaController {

    @Autowired
    PessoaFisicaRepository repository;

    @GetMapping("/form")
    public ModelAndView form(PessoaFisica pessoa) {
        return new ModelAndView("/pessoas/form");
    }

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("pessoas", repository.pessoas());
        return new ModelAndView("/pessoas/list", model);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid PessoaFisica pessoa, BindingResult result) {
        if (result.hasErrors()) {
            return form(pessoa);
        }
        repository.save(pessoa);
        return new ModelAndView("redirect:/pessoas/list");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        repository.remove(id);
        return new ModelAndView("redirect:/pessoas/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("pessoaFisica", repository.pessoaFisica(id));
        return new ModelAndView("/pessoas/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(PessoaFisica pessoa) {
        repository.update(pessoa);
        return new ModelAndView("redirect:/pessoas/list");
    }

    @PostMapping("/pesquisarPessoa")
    public ModelAndView pesquisar(@RequestParam(value = "nomepesquisa") String nomepesquisa, ModelMap model) {
        if ("nomepesquisa" == null) {
            return new ModelAndView("redirect:/pessoas/list");
        }
        model.addAttribute("pessoas", repository.buscaPorNome(nomepesquisa));
        return new ModelAndView("/pessoas/list", model);
    }

}
