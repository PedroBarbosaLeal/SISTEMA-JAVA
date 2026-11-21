package dao;

import db.ConnectionFactory;
import db.DataAccessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericDAO<T> {

    protected abstract T mapear(ResultSet rs) throws SQLException;

    protected List<T> consultar(String sql, Object... parametros) {
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            preencher(ps, parametros);
            try (ResultSet rs = ps.executeQuery()) {
                List<T> resultado = new ArrayList<>();
                while (rs.next()) {
                    resultado.add(mapear(rs));
                }
                return resultado;
            }
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao consultar o banco de dados.", e);
        }
    }

    protected int executar(String sql, Object... parametros) {
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            preencher(ps, parametros);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Erro ao gravar no banco de dados.", e);
        }
    }

    private static void preencher(PreparedStatement ps, Object... parametros) throws SQLException {
        for (int i = 0; i < parametros.length; i++) {
            ps.setObject(i + 1, parametros[i]);
        }
    }
}