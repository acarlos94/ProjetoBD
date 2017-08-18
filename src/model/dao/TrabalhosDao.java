/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.beans.Aluno;
import model.beans.TrabalhoConclusao;

/**
 *
 * @author Matheus
 */
public class TrabalhosDao {
    public void create(TrabalhoConclusao trabalho){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        DocenteDao docDao = new DocenteDao();
        AlunoDao aluDao = new AlunoDao();
        
        if (docDao.pesquisarDocente(trabalho.getCodPessoaOrientador()) == null){
            JOptionPane.showMessageDialog(null, "Não existe docente com esse código cadastrado no sistema.");
        }else if (aluDao.pesquisarAluno(trabalho.getCodPessoaAluno(), trabalho.getNumAluno()) == null){
            JOptionPane.showMessageDialog(null, "Aluno não cadastrado no sistema.");
        }else{
        
            try {

                stmt = con.prepareStatement("INSERT INTO trabalhoConclusao(codTrab, titulo, dataDefesa, codPessoaAluno, numAluno, codPessoaOrientador) values (?,?,?,?,?,?)");

                stmt.setInt(1, trabalho.getCodTrabalho());
                stmt.setString(2, trabalho.getTitulo());
                stmt.setTimestamp(3, new Timestamp (trabalho.getDataDefesa().getTime()));
                stmt.setInt(4, trabalho.getCodPessoaAluno());
                stmt.setInt(5, trabalho.getNumAluno());
                stmt.setInt(6, trabalho.getCodPessoaOrientador());



                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Salvo com sucesso!");


            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao salvar: "+ex);
            }finally{
                ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
            }
        }
    }
    
     public List<ArrayList> read2(){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<ArrayList> tuplas = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT codTrab, titulo, dataDefesa,codPessoaOrientador, numAluno, codPessoaAluno from trabalhoconclusao;");
            rs = stmt.executeQuery();
            
            while (rs.next()){
                ArrayList<Object> tupla = new ArrayList();
                tupla.add(rs.getString("codTrab"));
                tupla.add(rs.getString("titulo"));      
                tupla.add(rs.getDate("dataDefesa"));
                tupla.add(rs.getString("codPessoaAluno"));
                tupla.add(rs.getString("numAluno"));                
                tupla.add(rs.getString("codPessoaOrientador"));
                
     
                tuplas.add(tupla);
            }
            
        
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt, rs);
        }
        
        return tuplas;
         
    }
     
     public void delete(TrabalhoConclusao trabalho){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM trabalhoconclusao WHERE codTrab = ?");
            stmt.setInt(1, trabalho.getCodTrabalho()); 
          
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }
                
    }
     
    public void update(TrabalhoConclusao trabalho, int codigo){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE trabalhoconclusao SET  codTrab = ?, titulo = ?, dataDefesa = ?, codPessoaOrientador = ? where codTrab = ?");        
            stmt.setInt(1, trabalho.getCodTrabalho());
            stmt.setString(2, trabalho.getTitulo());
            stmt.setDate(3, (Date) trabalho.getDataDefesa());
            stmt.setInt(4, trabalho.getCodPessoaOrientador());
            stmt.setInt(5, codigo);
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }
               
    }
    
       
     
}