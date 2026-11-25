package dao;


import control.Funcionario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class FuncionarioDAO extends GenericDAO<Funcionario> {


	private static final String COLUNAS =
			"nome, cpf, email, fixo, idade, salario, celular, numero, data1, departamento, funcao, observacao, sexo";


	public List<Funcionario> listar() {
		return consultar("SELECT id, " + COLUNAS + " FROM funcionarios ORDER BY id");
	}


	public boolean cpfEmUso(String cpf, int idIgnorado) {
		List<Funcionario> iguais = consultar(
				"SELECT id, " + COLUNAS + " FROM funcionarios WHERE cpf = ? AND id <> ?", cpf, idIgnorado);
		return !iguais.isEmpty();
	}


	public void inserir(Funcionario f) {
		executar("INSERT INTO funcionarios (" + COLUNAS + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
				f.nome, f.cpf, f.email, f.fone, f.idade, f.salario, f.fonec, f.numero, f.data,
				f.departamento, f.funcao, f.observacao, f.sexo);
	}


	public void atualizar(Funcionario f) {
		executar("UPDATE funcionarios SET nome = ?, cpf = ?, email = ?, fixo = ?, idade = ?, salario = ?,"
						+ " celular = ?, numero = ?, data1 = ?, departamento = ?, funcao = ?, observacao = ?,"
						+ " sexo = ? WHERE id = ?",
				f.nome, f.cpf, f.email, f.fone, f.idade, f.salario, f.fonec, f.numero, f.data,
				f.departamento, f.funcao, f.observacao, f.sexo, f.id);
	}


	public void excluir(int id) {
		executar("DELETE FROM funcionarios WHERE id = ?", id);
	}


	public void excluirTodos() {
		executar("DELETE FROM funcionarios");
	}


	@Override
	protected Funcionario mapear(ResultSet rs) throws SQLException {
		Funcionario f = new Funcionario();
		f.id = rs.getInt("id");
		f.nome = rs.getString("nome");
		f.cpf = rs.getString("cpf");
		f.email = rs.getString("email");
		f.fone = rs.getString("fixo");
		f.idade = rs.getString("idade");
		f.salario = rs.getString("salario");
		f.fonec = rs.getString("celular");
		f.numero = rs.getString("numero");
		f.data = rs.getString("data1");
		f.departamento = rs.getString("departamento");
		f.funcao = rs.getString("funcao");
		f.observacao = rs.getString("observacao");
		f.sexo = rs.getString("sexo");
		return f;
	}
}