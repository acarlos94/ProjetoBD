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
import model.beans.Aluno;
import model.beans.CoOrientacao;

/**
 *
 * @author Matheus
 */
public class CoOrientacaoDao {
        public void create(CoOrientacao coOrientacao){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement st = null;
        
        try {
          st = con.prepareStatement("INSERT INTO coorientacao (codPessoa, codTrab) values (?,?)");
          st.setInt(1, coOrientacao.getCodPessoa());
          st.setInt(2, coOrientacao.getCodTrabalho());
        
            
            st.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com suceeso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, (com.mysql.jdbc.PreparedStatement) st);
        }
                
    }
}
