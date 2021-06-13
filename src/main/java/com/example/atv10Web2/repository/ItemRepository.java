/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.atv10Web2.repository;

import com.example.atv10Web2.model.ItemVenda;
import com.example.atv10Web2.model.PessoaFisica;
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
public class ItemRepository {

    @PersistenceContext
    private EntityManager em;

    public List<ItemVenda> buscaPorIdVenda(Long idVenda) {
        String hql = "from ItemVenda i where i.venda.id = :idVenda";
        Query query = em.createQuery(hql, ItemVenda.class);
        query.setParameter("idVenda", idVenda);
        return query.getResultList();
    }

}
