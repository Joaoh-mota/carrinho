/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.atv10Web2.repository;

import com.example.atv10Web2.model.Pessoa;
import com.example.atv10Web2.model.PessoaFisica;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository
public class PessoaFisicaRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(PessoaFisica pessoa) {
        em.persist(pessoa);
    }

    public PessoaFisica pessoaFisica(Long id) {
        return em.find(PessoaFisica.class, id);
    }

    public List<Pessoa> pessoas() {
        Query query = em.createQuery("from PessoaFisica");
        return query.getResultList();
    }

    public void remove(Long id) {
        Pessoa p = em.find(PessoaFisica.class, id);
        em.remove(p);
    }

    public void update(PessoaFisica pessoa) {
        em.merge(pessoa);
    }

    public  List <PessoaFisica> buscaPorNome(String nome) {
        String hql = "from PessoaFisica p where p.nome like :nome";
        Query query = em.createQuery(hql, PessoaFisica.class);
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList();
    }

}
