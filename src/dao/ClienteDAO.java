package dao;


import control.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class ClienteDAO extends GenericDAO<Cliente> {


	private static final String COLUNAS =
			"nome, cpf, fixo, idade, cidade, sexo, celular, email, cep, estado, bairro, numero, rua";


	public List<Cliente> listar() {
		return consultar("SELECT id, " + COLUNAS + " FROM cliente ORDER BY id");
	}


	public boolean cpfEmUso(String cpf, int idIgnorado) {
		List<Cliente> iguais = consultar(
				"SELECT id, " + COLUNAS + " FROM cliente WHERE cpf = ? AND id <> ?", cpf, idIgnorado);
		return !iguais.isEmpty();
	}


	public void inserir(Cliente c) {
		executar("INSERT INTO cliente (" + COLUNAS + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
				c.nome, c.cpf, c.fone, c.idade, c.cidade, c.sexo, c.fonec,
				c.email, c.cep, c.estado, c.bairro, c.numero, c.rua);
	}


	public void atualizar(Cliente c) {
		executar("UPDATE cliente SET nome = ?, cpf = ?, fixo = ?, idade = ?, cidade = ?, sexo = ?, celular = ?,"
						+ " email = ?, cep = ?, estado = ?, bairro = ?, numero = ?, rua = ? WHERE id = ?",
				c.nome, c.cpf, c.fone, c.idade, c.cidade, c.sexo, c.fonec,
				c.email, c.cep, c.estado, c.bairro, c.numero, c.rua, c.id);
	}


	public void excluir(int id) {
		executar("DELETE FROM cliente WHERE id = ?", id);
	}


	public void excluirTodos() {
		executar("DELETE FROM cliente");
	}


	@Override
	protected Cliente mapear(ResultSet rs) throws SQLException {
		Cliente c = new Cliente();
		c.id = rs.getInt("id");
		c.nome = rs.getString("nome");
		c.cpf = rs.getString("cpf");
		c.fone = rs.getString("fixo");
		c.idade = rs.getString("idade");
		c.cidade = rs.getString("cidade");
		c.sexo = rs.getString("sexo");
		c.fonec = rs.getString("celular");
		c.email = rs.getString("email");
		c.cep = rs.getString("cep");
		c.estado = rs.getString("estado");
		c.bairro = rs.getString("bairro");
		c.numero = rs.getString("numero");
		c.rua = rs.getString("rua");
		return c;
	}
}