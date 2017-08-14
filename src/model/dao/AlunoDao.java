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
    
}
