package dao;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import control.Funcionario;
import db.DataAccessException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;


// Roda no banco do db.properties e só mexe em funcionários com CPF começando por "TESTE-".
class FuncionarioDAOTest {


    private final FuncionarioDAO dao = new FuncionarioDAO();


    @AfterEach
    void apagarFuncionariosDeTeste() {
        dao.executar("DELETE FROM funcionarios WHERE cpf LIKE 'TESTE-%'");
    }


    private Funcionario novoFuncionario(String nome, String cpf) {
        Funcionario f = new Funcionario();
        f.nome = nome;
        f.cpf = cpf;
        f.email = "teste@teste.com";
        f.salario = "2500";
        f.numero = "10";
        f.data = "01/02/2020";
        f.departamento = "Vendas";
        f.funcao = "Atendente";
        f.observacao = "Observacao de teste";
        return f;
    }


    private Funcionario buscarPorCpf(String cpf) {
        for (Funcionario f : dao.listar()) {
            if (cpf.equals(f.cpf)) {
                return f;
            }
        }
        return null;
    }


    @Test
    void numeroEObservacaoFicamNasColunasCertas() {
        dao.inserir(novoFuncionario("Funcionario A", "TESTE-001"));


        Funcionario lido = buscarPorCpf("TESTE-001");


        assertEquals("10", lido.numero);
        assertEquals("Observacao de teste", lido.observacao);
        assertEquals("2500", lido.salario);
        assertEquals("01/02/2020", lido.data);
    }


    @Test
    void atualizarComCpfAlteradoGravaNoBanco() {
        dao.inserir(novoFuncionario("Funcionario B", "TESTE-002"));
        Funcionario f = buscarPorCpf("TESTE-002");


        f.cpf = "TESTE-999";
        f.email = "novo@teste.com";
        dao.atualizar(f);


        assertNull(buscarPorCpf("TESTE-002"));
        assertEquals("novo@teste.com", buscarPorCpf("TESTE-999").email);
    }


    @Test
    void excluirApagaSoOFuncionarioEscolhido() {
        dao.inserir(novoFuncionario("Funcionario A", "TESTE-001"));
        dao.inserir(novoFuncionario("Funcionario C", "TESTE-003"));


        dao.excluir(buscarPorCpf("TESTE-001").id);


        assertNull(buscarPorCpf("TESTE-001"));
        assertNotNull(buscarPorCpf("TESTE-003"));
    }


    @Test
    void cpfEmUsoIgnoraOProprioFuncionario() {
        dao.inserir(novoFuncionario("Funcionario A", "TESTE-001"));
        Funcionario a = buscarPorCpf("TESTE-001");


        assertTrue(dao.cpfEmUso("TESTE-001", 0));
        assertFalse(dao.cpfEmUso("TESTE-001", a.id));
    }


    @Test
    void cpfRepetidoDaErroNoBanco() {
        dao.inserir(novoFuncionario("Funcionario A", "TESTE-001"));


        assertThrows(DataAccessException.class,
                () -> dao.inserir(novoFuncionario("Funcionario B", "TESTE-001")));
    }


    @Test
    void aspasNoNomeNaoViramComandoSql() {
        int antes = dao.listar().size();
        String nome = "Robert'); DELETE FROM funcionarios; --";


        dao.inserir(novoFuncionario(nome, "TESTE-004"));


        assertEquals(antes + 1, dao.listar().size());
        assertEquals(nome, buscarPorCpf("TESTE-004").nome);
    }
}