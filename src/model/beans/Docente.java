/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.beans;

/**
 *
 * @author Antonio
 */
public class Docente {
    
    private int codPessoa;    
    private int codInstituicao;
    private String nomeDepartamento;

    public int getCodPessoa() {
        return codPessoa;
    }

    public void setCodPessoa(int codPessoa) {
        this.codPessoa = codPessoa;
    }
    
    public int getCodInstituicao() {
        return codInstituicao;
    }

    public void setCodInstituicao(int codInstituicao) {
        this.codInstituicao = codInstituicao;
    }

    public String getNomeDepartamento() {
        return nomeDepartamento;
    }

    public void setNomeDepartamento(String nomeDepartamento) {
        this.nomeDepartamento = nomeDepartamento;
    }
    
    
    
    
}
