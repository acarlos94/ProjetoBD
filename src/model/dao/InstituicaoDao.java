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
import model.beans.Instituicao;

/**
 *
 * @author Antonio
 */
public class InstituicaoDao {
    
    public void create(Instituicao instituicao){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO instituicao (codInstituicao, nomeInstituicao) VALUES(?,?)");
            stmt.setInt(1, instituicao.getCodInstituicao());
            stmt.setString(2, instituicao.getNomeInstituicao());
                        
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }
                
    }
    
    public List<Instituicao> read(){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Instituicao> instituicoes = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM instituicao");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                Instituicao instituicao = new Instituicao();
                instituicao.setCodInstituicao(rs.getInt("codInstituicao"));
                instituicao.setNomeInstituicao(rs.getString("nomeInstituicao"));
                instituicoes.add(instituicao);
                
            }
                    
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt, rs);
        }
        
        return instituicoes;
    }
    
    public void delete(Instituicao instituicao){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM instituicao WHERE codInstituicao = ?");
            stmt.setInt(1, instituicao.getCodInstituicao()); 
          
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }
                
    }
    
    public void update(Instituicao instituicao, int codigo){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE instituicao SET codInstituicao = ?, nomeInstituicao = ? WHERE codInstituicao = ?");           
            stmt.setInt(1, instituicao.getCodInstituicao());
            stmt.setString(2, instituicao.getNomeInstituicao());
            stmt.setInt(3, codigo);
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }
                
    }
    
}
