/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.beans.Pessoa;

/**
 *
 * @author Antonio
 */
public class PessoaDao {
    
    public void create(Pessoa pessoa){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO pessoa (codPessoa, nomePessoa) VALUES(?,?)");
            stmt.setInt(1, pessoa.getCodPessoa());
            stmt.setString(2, pessoa.getNomePessoa());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com suceeso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }
        
        
    }
    
}
