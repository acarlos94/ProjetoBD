/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.beans.Aluno;

/**
 *
 * @author Antonio
 */
public class AlunoDao {
    
    public void create(Aluno aluno){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO aluno (codPessoa, numAluno, nomeCurso) VALUES(?,?,?)");
            stmt.setInt(1, aluno.getCodPessoa());
            stmt.setInt(2, aluno.getNumAluno());
            stmt.setString(3, aluno.getNomeCurso());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com suceeso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }
                
    }
    
    public List<Aluno> read(){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Aluno> alunos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM aluno");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                Aluno aluno = new Aluno();
                aluno.setCodPessoa(rs.getInt("codPessoa"));
                aluno.setNumAluno(rs.getInt("numAluno"));
                aluno.setNomeCurso(rs.getString("nomeCurso"));
                alunos.add(aluno);
                
            }
                    
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt, rs);
        }
        
        return alunos;
        
    }
    
    public List<ArrayList> read2(){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<ArrayList> tuplas = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT P.codPessoa, P.nomePessoa, A.numAluno, A.nomeCurso FROM pessoa as P inner join aluno as A ON P.codPessoa = A.codPessoa;");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                ArrayList<String> tupla = new ArrayList();
                tupla.add(rs.getString("codPessoa"));
                tupla.add(rs.getString("nomePessoa"));
                tupla.add(rs.getString("numAluno"));
                tupla.add(rs.getString("nomeCurso"));
                tuplas.add(tupla);
                            
            }
                    
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt, rs);
        }
        
        return tuplas;
        
    }
    
    public void delete(Aluno aluno){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM aluno WHERE codPessoa = ?");
            stmt.setInt(1, aluno.getCodPessoa()); 
          
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }
                
    }
    
    public void update(Aluno aluno){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE aluno SET numAluno = ?, nomeCurso = ? WHERE codPessoa = ?");           
            //stmt.setInt(1, aluno.getCodPessoa());
            stmt.setInt(1, aluno.getNumAluno());
            stmt.setString(2, aluno.getNomeCurso());
            stmt.setInt(3, aluno.getCodPessoa());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com suceeso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }
                
    }
    
}
