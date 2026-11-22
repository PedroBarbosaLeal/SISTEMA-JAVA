package dao;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


import control.Fornecedor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;


// Roda no banco do db.properties e só mexe em fornecedores com CNPJ começando por "TESTE-".
class FornecedorDAOTest {


    private final FornecedorDAO dao = new FornecedorDAO();


    @AfterEach
    void apagarFornecedoresDeTeste() {
        dao.executar("DELETE FROM fornecedores WHERE cnpj LIKE 'TESTE-%'");
    }


    private Fornecedor novoFornecedor(String nome, String cnpj) {
        Fornecedor f = new Fornecedor();
        f.nome = nome;
        f.cnpj = cnpj;
        f.email = "teste@teste.com";
        f.cidade = "Salvador";
        return f;
    }


    private Fornecedor buscarPorCnpj(String cnpj) {
        for (Fornecedor f : dao.listar()) {
            if (cnpj.equals(f.cnpj)) {
                return f;
            }
        }
        return null;
    }


    @Test
    void inserirEListarMantemOsCampos() {
        dao.inserir(novoFornecedor("Fornecedor A", "TESTE-001"));


        Fornecedor lido = buscarPorCnpj("TESTE-001");


        assertEquals("Fornecedor A", lido.nome);
        assertEquals("Salvador", lido.cidade);
    }


    @Test
    void atualizarComCnpjAlteradoGravaNoBanco() {
        dao.inserir(novoFornecedor("Fornecedor B", "TESTE-002"));
        Fornecedor f = buscarPorCnpj("TESTE-002");


        f.cnpj = "TESTE-999";
        f.email = "novo@teste.com";
        dao.atualizar(f);


        assertNull(buscarPorCnpj("TESTE-002"));
        assertEquals("novo@teste.com", buscarPorCnpj("TESTE-999").email);
    }


    @Test
    void excluirApagaSoOFornecedorEscolhido() {
        dao.inserir(novoFornecedor("Fornecedor A", "TESTE-001"));
        dao.inserir(novoFornecedor("Fornecedor C", "TESTE-003"));


        dao.excluir(buscarPorCnpj("TESTE-001").id);


        assertNull(buscarPorCnpj("TESTE-001"));
        assertNotNull(buscarPorCnpj("TESTE-003"));
    }


    @Test
    void cnpjEmUsoIgnoraOProprioFornecedor() {
        dao.inserir(novoFornecedor("Fornecedor A", "TESTE-001"));
        Fornecedor a = buscarPorCnpj("TESTE-001");


        assertTrue(dao.cnpjEmUso("TESTE-001", 0));
        assertFalse(dao.cnpjEmUso("TESTE-001", a.id));
    }


    @Test
    void aspasNoNomeNaoViramComandoSql() {
        int antes = dao.listar().size();
        String nome = "Robert'); DELETE FROM fornecedores; --";


        dao.inserir(novoFornecedor(nome, "TESTE-004"));


        assertEquals(antes + 1, dao.listar().size());
        assertEquals(nome, buscarPorCnpj("TESTE-004").nome);
    }
}
