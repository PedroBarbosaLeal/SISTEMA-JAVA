package dao;

import db.ConnectionFactory;
import db.DataAccessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class Conexao {

    public int executeUpdate(String query) {
        try (Connection con = ConnectionFactory.getConnection();
             Statement stm = con.createStatement()) {
            return stm.executeUpdate(query);
        } catch (SQLException ex) {
            throw new DataAccessException("Erro ao gravar no banco de dados.", ex);
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
            throw new DataAccessException("Erro ao consultar o banco de dados.", ex);
        }
    }
}
