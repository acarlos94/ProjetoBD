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
import javax.swing.JOptionPane;
import model.beans.Docente;

/**
 *
 * @author Antonio
 */
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
            
            JOptionPane.showMessageDialog(null, "Salvo com suceeso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) stmt);
        }
                
    }
    
    
}
