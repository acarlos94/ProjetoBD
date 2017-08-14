/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.beans;

import java.util.Date;

/**
 *
 * @author Antonio
 */
public class TrabalhoConclusao {
    
    private int id;
    private int codTrabalho;
    private String titulo;
    private Date dataDefesa;
    private int codPessoaAluno;
    private int numAluno;
    private int codPessoaOrientador;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodTrabalho() {
        return codTrabalho;
    }

    public void setCodTrabalho(int codTrabalho) {
        this.codTrabalho = codTrabalho;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getDataDefesa() {
        return dataDefesa;
    }

    public void setDataDefesa(Date dataDefesa) {
        this.dataDefesa = dataDefesa;
    }

    public int getCodPessoaAluno() {
        return codPessoaAluno;
    }

    public void setCodPessoaAluno(int codPessoaAluno) {
        this.codPessoaAluno = codPessoaAluno;
    }

    public int getNumAluno() {
        return numAluno;
    }

    public void setNumAluno(int numAluno) {
        this.numAluno = numAluno;
    }

    public int getCodPessoaOrientador() {
        return codPessoaOrientador;
    }

    public void setCodPessoaOrientador(int codPessoaOrientador) {
        this.codPessoaOrientador = codPessoaOrientador;
    }
    
    
    
}
