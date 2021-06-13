/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.atv10Web2.controller;

import com.example.atv10Web2.repository.ProdutoRepository;
import com.example.atv10Web2.repository.VendaRepository;
import com.example.atv10Web2.model.ItemVenda;
import com.example.atv10Web2.model.PessoaFisica;
import com.example.atv10Web2.model.Produto;
import com.example.atv10Web2.model.Venda;
import com.example.atv10Web2.repository.ItemRepository;
import com.example.atv10Web2.repository.PessoaFisicaRepository;
import java.time.LocalDate;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author joaoh
 */
@Scope("request")
@Transactional
@Controller
@RequestMapping("vendas")
public class VendasController {

    @Autowired
    VendaRepository v_repository;

    @Autowired
    ProdutoRepository p_repository;

    @Autowired
    PessoaFisicaRepository pf_repository;
    
    @Autowired
    ItemRepository i_repository;

    @Autowired
    Venda venda;

    @GetMapping("/form")
    public ModelAndView form(ItemVenda itemVenda) {
        ModelAndView mv = new ModelAndView("/vendas/form");
        mv.addObject("pessoasF", pf_repository.pessoas());
        mv.addObject("produtos", p_repository.produtos());
        return mv;
    }

    @PostMapping("/save")
    public ModelAndView save(PessoaFisica pessoaFisica, RedirectAttributes attributes) {
        if (venda.getItens().isEmpty()) {
            attributes.addFlashAttribute("carrinhoVazio", "Impossivel finalizar compra com carrinho vazio");
        }
        if (pessoaFisica.getId() == null) {
            attributes.addFlashAttribute("semCliente", "Impossivel finalizar compra sem cliente");
        }
        if (!attributes.getFlashAttributes().isEmpty()) {
            return new ModelAndView("redirect:/vendas/form");
        }
        venda.setId(null);
        PessoaFisica p = pf_repository.pessoaFisica(pessoaFisica.getId());

        venda.setPessoa(p);
        v_repository.save(venda);
        venda.getItens().clear();
        return new ModelAndView("redirect:/vendas/form");
    }

    @PostMapping("/add")
    public ModelAndView addItem(@Valid ItemVenda itemVenda, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return form(itemVenda);
        }
        Produto p = p_repository.produto(itemVenda.getProduto().getId());
        itemVenda.setProduto(p);
        itemVenda.setVenda(venda);
        venda.getItens().add(itemVenda);
        return new ModelAndView("redirect:/vendas/form");
    }

    @GetMapping("/remove/{pos}")
    public ModelAndView removeItem(@PathVariable("pos") int pos) {
        venda.getItens().remove(pos);
        return new ModelAndView("redirect:/vendas/form");
    }

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("vendas", v_repository.vendas());
        return new ModelAndView("/vendas/list", model);
    }

    @GetMapping("/detalhes/{id}")
    public ModelAndView detalhes(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("detalhes", i_repository.buscaPorIdVenda(id));
        model.addAttribute("pessoa", pf_repository.pessoaFisica(id));
        model.addAttribute("data",v_repository.venda(id));
        model.addAttribute("venda",v_repository.venda(id));
        return new ModelAndView("/vendas/detalhes", model);
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }
    
    @DateTimeFormat(pattern = "dd-MM-YYYY")
    @PostMapping("/pesquisarVenda")
    public ModelAndView pesquisar(@RequestParam(value = "datapesquisa") String datapesquisa, ModelMap model) {
        if (datapesquisa.isEmpty()) {
            return new ModelAndView("redirect:/vendas/list");
        }
        model.addAttribute("vendas", v_repository.buscaPorData(LocalDate.parse(datapesquisa)));
        return new ModelAndView("/vendas/list", model);
    }

}
