/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.atv10Web2.repository;

import com.example.atv10Web2.model.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joaoh
 */
@Repository
public class UsuarioRepository {

    @PersistenceContext
    private EntityManager em;

    public Usuario usuario(String login) {
        return em.find(Usuario.class, login); 
    }
}
