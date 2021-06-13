/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.atv10Web2.model;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author joaoh
 */
@Entity
@DiscriminatorValue("F")
public class PessoaFisica extends Pessoa implements Serializable {
    
    @Size(min=11, max=14, message=" precisa ter 11 caracteres")
    @NotBlank
    private String cpf;
        
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    

}
