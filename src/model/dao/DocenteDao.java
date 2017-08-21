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
import model.beans.Docente;


public class DocenteDao {
    
    public void create(Docente docente){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO docente (codPessoa, codInstituicao, nomeDepartamento) VALUES(?,?,?)");
            stmt.setInt(1, docente.getCodPessoa());
            stmt.setInt(2, docente.getCodInstituicao());
            stmt.setString(3, docente.getNomeDepartamento());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }
                
    }
    
    public List<ArrayList> read2(){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<ArrayList> tuplas = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT p.codPessoa, p.nomePessoa, d.codInstituicao, d.nomeDepartamento from pessoa as p inner join docente as d on p.codPessoa=d.codPessoa;");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                
                ArrayList<String> tupla = new ArrayList();
                tupla.add(rs.getString("codPessoa"));
                tupla.add(rs.getString("nomePessoa"));
                tupla.add(rs.getString("codInstituicao"));
                tupla.add(rs.getString("nomeDepartamento"));
                tuplas.add(tupla);
                            
            }
                    
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt, rs);
        }
        
        return tuplas;
        
    }
       
    public void delete(Docente docente){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM docente WHERE codPessoa = ?");
            stmt.setInt(1, docente.getCodPessoa()); 
          
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }
                
    }
    
    public void update(Docente docente){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE docente SET codInstituicao = ?, nomeDepartamento = ? where codPessoa = ?");        
            stmt.setInt(1, docente.getCodInstituicao());
            stmt.setString(2, docente.getNomeDepartamento());
            stmt.setInt(3, docente.getCodPessoa());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }
               
    }
    
    public Docente pesquisarDocente(int codigo){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM docente WHERE codPessoa = ?");
            stmt.setInt(1, codigo);                       
            rs = stmt.executeQuery();
            
            while (rs.next()){
                Docente docente = new Docente();
                docente.setCodPessoa(rs.getInt("codPessoa"));
                docente.setCodInstituicao(rs.getInt("codInstituicao"));
                docente.setNomeDepartamento(rs.getString("nomeDepartamento"));                
                return docente;
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Docente não encontrado. " +ex);
        } finally {
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt, rs);
        }
        return null;
                
    }
}
