package dao;


import control.Fornecedor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class FornecedorDAO extends GenericDAO<Fornecedor> {


	private static final String COLUNAS =
			"nome, cnpj, email, fixo, numero, descricao, celular, cep, bairro, estado, cidade, rua";


	public List<Fornecedor> listar() {
		return consultar("SELECT id, " + COLUNAS + " FROM fornecedores ORDER BY id");
	}


	public boolean cnpjEmUso(String cnpj, int idIgnorado) {
		List<Fornecedor> iguais = consultar(
				"SELECT id, " + COLUNAS + " FROM fornecedores WHERE cnpj = ? AND id <> ?", cnpj, idIgnorado);
		return !iguais.isEmpty();
	}


	public void inserir(Fornecedor f) {
		executar("INSERT INTO fornecedores (" + COLUNAS + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
				f.nome, f.cnpj, f.email, f.fone, f.numero, f.descricao,
				f.fonec, f.cep, f.bairro, f.estado, f.cidade, f.rua);
	}


	public void atualizar(Fornecedor f) {
		executar("UPDATE fornecedores SET nome = ?, cnpj = ?, email = ?, fixo = ?, numero = ?, descricao = ?,"
						+ " celular = ?, cep = ?, bairro = ?, estado = ?, cidade = ?, rua = ? WHERE id = ?",
				f.nome, f.cnpj, f.email, f.fone, f.numero, f.descricao,
				f.fonec, f.cep, f.bairro, f.estado, f.cidade, f.rua, f.id);
	}


	public void excluir(int id) {
		executar("DELETE FROM fornecedores WHERE id = ?", id);
	}


	public void excluirTodos() {
		executar("DELETE FROM fornecedores");
	}


	@Override
	protected Fornecedor mapear(ResultSet rs) throws SQLException {
		Fornecedor f = new Fornecedor();
		f.id = rs.getInt("id");
		f.nome = rs.getString("nome");
		f.cnpj = rs.getString("cnpj");
		f.email = rs.getString("email");
		f.fone = rs.getString("fixo");
		f.numero = rs.getString("numero");
		f.descricao = rs.getString("descricao");
		f.fonec = rs.getString("celular");
		f.cep = rs.getString("cep");
		f.bairro = rs.getString("bairro");
		f.estado = rs.getString("estado");
		f.cidade = rs.getString("cidade");
		f.rua = rs.getString("rua");
		return f;
	}
}

