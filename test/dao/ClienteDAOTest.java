package dao;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


import control.Cliente;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;


// Roda no banco do db.properties e só mexe em clientes com CPF começando por "TESTE-".
class ClienteDAOTest {


    private final ClienteDAO dao = new ClienteDAO();


    @AfterEach
    void apagarClientesDeTeste() {
        dao.executar("DELETE FROM cliente WHERE cpf LIKE 'TESTE-%'");
    }


    private Cliente novoCliente(String nome, String cpf) {
        Cliente c = new Cliente();
        c.nome = nome;
        c.cpf = cpf;
        c.idade = "30";
        c.cidade = "Salvador";
        c.email = "teste@teste.com";
        return c;
    }


    private Cliente buscarPorCpf(String cpf) {
        for (Cliente c : dao.listar()) {
            if (cpf.equals(c.cpf)) {
                return c;
            }
        }
        return null;
    }


    @Test
    void idadeECidadeFicamNasColunasCertas() {
        dao.inserir(novoCliente("Cliente A", "TESTE-001"));


        Cliente lido = buscarPorCpf("TESTE-001");


        assertEquals("30", lido.idade);
        assertEquals("Salvador", lido.cidade);
    }


    @Test
    void atualizarComCpfAlteradoGravaNoBanco() {
        dao.inserir(novoCliente("Cliente B", "TESTE-002"));
        Cliente c = buscarPorCpf("TESTE-002");


        c.cpf = "TESTE-999";
        c.email = "novo@teste.com";
        dao.atualizar(c);


        assertNull(buscarPorCpf("TESTE-002"));
        assertEquals("novo@teste.com", buscarPorCpf("TESTE-999").email);
    }


    @Test
    void excluirApagaSoOClienteEscolhido() {
        dao.inserir(novoCliente("Cliente A", "TESTE-001"));
        dao.inserir(novoCliente("Cliente C", "TESTE-003"));


        dao.excluir(buscarPorCpf("TESTE-001").id);


        assertNull(buscarPorCpf("TESTE-001"));
        assertNotNull(buscarPorCpf("TESTE-003"));
    }


    @Test
    void cpfEmUsoIgnoraOProprioCliente() {
        dao.inserir(novoCliente("Cliente A", "TESTE-001"));
        Cliente a = buscarPorCpf("TESTE-001");


        assertTrue(dao.cpfEmUso("TESTE-001", 0));
        assertFalse(dao.cpfEmUso("TESTE-001", a.id));
    }


    @Test
    void aspasNoNomeNaoViramComandoSql() {
        int antes = dao.listar().size();
        String nome = "Robert'); DELETE FROM cliente; --";


        dao.inserir(novoCliente(nome, "TESTE-004"));


        assertEquals(antes + 1, dao.listar().size());
        assertEquals(nome, buscarPorCpf("TESTE-004").nome);
    }
}