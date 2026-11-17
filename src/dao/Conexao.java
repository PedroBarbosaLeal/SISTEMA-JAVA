package dao;

import db.ConnectionFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import javax.swing.JOptionPane;

public class Conexao {

    public int executeUpdate(String query) {
        try (Connection con = ConnectionFactory.getConnection();
             Statement stm = con.createStatement()) {
            return stm.executeUpdate(query);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problemas com a conexão\n" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    public ResultSet executeQuery(String query) {
        try (Connection con = ConnectionFactory.getConnection();
             Statement stm = con.createStatement();
             ResultSet rs = stm.executeQuery(query)) {
            CachedRowSet copia = RowSetProvider.newFactory().createCachedRowSet();
            copia.populate(rs);
            return copia;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problemas com a conexão\n" + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}

