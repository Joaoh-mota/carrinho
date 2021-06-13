/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.atv10Web2.repository;

import com.example.atv10Web2.model.Produto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joaoh
 */
@Repository
public class ProdutoRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Produto produto) {
        em.persist(produto);
    }

    public Produto produto(Long id) {
        return em.find(Produto.class, id);
    }

    public List<Produto> produtos() {
        Query query = em.createQuery("from Produto");
        return query.getResultList();
    }

    public void remove(Long id) {
        Produto v = em.find(Produto.class, id);
        em.remove(v);
    }

    public void update(Produto produto) {
        em.merge(produto);
    }

}
