/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.atv10Web2.repository;

import com.example.atv10Web2.model.PessoaFisica;
import com.example.atv10Web2.model.Venda;
import java.time.LocalDate;
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
public class VendaRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Venda venda) {
        em.persist(venda);
    }

    public Venda venda(Long id) {
        return em.find(Venda.class, id);
    }

    public List<Venda> vendas() {
        Query query = em.createQuery("from Venda");
        return query.getResultList();
    }

    public void remove(Long id) {
        Venda v = em.find(Venda.class, id);
        em.remove(v);
    }

    public void update(Venda venda) {
        em.merge(venda);
    }
    
//    public  List <PessoaFisica> buscaPorNome(String nome) {
//        String hql = "from PessoaFisica p where p.nome like :nome";
//        Query query = em.createQuery(hql, PessoaFisica.class);
//        query.setParameter("nome", "%" + nome + "%");
//        return query.getResultList();
//    }
    
    public  List <Venda> buscaPorData(LocalDate data) {
        String hql = "from Venda v where v.data = :data";
        Query query = em.createQuery(hql, Venda.class);
        query.setParameter("data",data);
        return query.getResultList();
    }

}
