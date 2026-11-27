package dao;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


import control.Produto;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class ProdutoDAOTest {


    private final ProdutoDAO dao = new ProdutoDAO();


    @AfterEach
    void apagarProdutosDeTeste() {
        dao.executar("DELETE FROM produtos WHERE nome LIKE 'TESTE-%'");
    }


    private Produto novoProduto(String nome) {
        Produto p = new Produto();
        p.nome = nome;
        p.valor_da_compra = "10.50";
        p.valor_da_venda = "15.90";
        p.marca = "Marca Teste";
        p.categoria = "Categoria Teste";
        p.descricao = "Descricao Teste";
        p.fornecedor = "Fornecedor Teste";
        p.prateleira = "A1";
        p.data_da_compra = "01/02/2020";
        p.quant = "7";
        return p;
    }


    private Produto buscarPorNome(String nome) {
        for (Produto p : dao.listar()) {
            if (nome.equals(p.nome)) {
                return p;
            }
        }
        return null;
    }


    @Test
    void camposFicamNasColunasCertas() {
        dao.inserir(novoProduto("TESTE-Produto A"));


        Produto lido = buscarPorNome("TESTE-Produto A");


        assertEquals("10.50", lido.valor_da_compra);
        assertEquals("15.90", lido.valor_da_venda);
        assertEquals("Marca Teste", lido.marca);
        assertEquals("01/02/2020", lido.data_da_compra);
        assertEquals("A1", lido.prateleira);
        assertEquals("7", lido.quant);
        assertNotNull(lido.codigo_produto);
    }


    @Test
    void atualizarGravaNoBanco() {
        dao.inserir(novoProduto("TESTE-Produto B"));
        Produto p = buscarPorNome("TESTE-Produto B");


        p.nome = "TESTE-Produto B2";
        p.quant = "20";
        dao.atualizar(p);


        assertNull(buscarPorNome("TESTE-Produto B"));
        assertEquals("20", buscarPorNome("TESTE-Produto B2").quant);
    }


    @Test
    void excluirApagaSoOProdutoEscolhido() {
        dao.inserir(novoProduto("TESTE-Produto A"));
        dao.inserir(novoProduto("TESTE-Produto C"));


        dao.excluir(buscarPorNome("TESTE-Produto A").id);


        assertNull(buscarPorNome("TESTE-Produto A"));
        assertNotNull(buscarPorNome("TESTE-Produto C"));
    }


    @Test
    void pesquisarPorNomeTrazSoOsCorrespondentes() {
        dao.inserir(novoProduto("TESTE-Alfa"));
        dao.inserir(novoProduto("TESTE-Beta"));


        List<Produto> achados = dao.pesquisar("Nome", "TESTE-Alf");


        assertEquals(1, achados.size());
        assertEquals("TESTE-Alfa", achados.get(0).nome);
    }


    @Test
    void pesquisarComCampoInvalidoNaoMontaSql() {
        assertThrows(IllegalArgumentException.class,
                () -> dao.pesquisar("nome; DROP TABLE produtos; --", "x"));
    }


    @Test
    void aspasNoNomeNaoViramComandoSql() {
        int antes = dao.listar().size();
        String nome = "TESTE-Robert'); DELETE FROM produtos; --";


        dao.inserir(novoProduto(nome));


        assertEquals(antes + 1, dao.listar().size());
        assertEquals(nome, buscarPorNome(nome).nome);
    }
}