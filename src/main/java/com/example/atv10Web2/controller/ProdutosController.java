/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.atv10Web2.controller;

import com.example.atv10Web2.model.ItemVenda;
import com.example.atv10Web2.model.Produto;
import com.example.atv10Web2.model.Venda;
import com.example.atv10Web2.repository.ProdutoRepository;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author joaoh
 */
@Scope("request")
@Transactional
@Controller
@RequestMapping("produtos")
public class ProdutosController {

    @Autowired
    ProdutoRepository p_repository;

    @PostMapping("/addProd")
    public ModelAndView save(@Valid Produto produto, BindingResult result) {
        if (result.hasErrors()) {
            return form(produto);
        }
        produto.setId(null);
        p_repository.save(produto);
        return new ModelAndView("redirect:/produtos/listProd");
    }

    @GetMapping("/form")
    public ModelAndView form(Produto produto) {
        ModelAndView mv = new ModelAndView("/produtos/form");
        return mv;
    }

    @GetMapping("/listProd")
    public ModelAndView listProd(Venda venda, ItemVenda itemVenda) {
        ModelAndView mv = new ModelAndView("/produtos/list");
        mv.addObject("produtos", p_repository.produtos());
        return mv;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("produto", p_repository.produto(id));
        return new ModelAndView("/produtos/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(Produto produto) {
        p_repository.update(produto);
        return new ModelAndView("redirect:/produtos/listProd");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        p_repository.remove(id);
        return new ModelAndView("redirect:/produtos/listProd");
    }
}
