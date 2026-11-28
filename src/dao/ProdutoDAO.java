package dao;


import control.Produto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public class ProdutoDAO extends GenericDAO<Produto> {


    private static final String COLUNAS =
            "nome, valor_da_compra, marca, valor_da_venda, categoria, descricao, fornecedor, prateleira,"
                    + " data_da_compra, quantidade";

    private static final Map<String, String> COLUNAS_PESQUISA = Map.of(
            "Nome", "nome",
            "Marca", "marca",
            "Categoria", "categoria",
            "Fornecedor", "fornecedor",
            "Prateleira", "prateleira",
            "Descricao", "descricao");


    public List<Produto> listar() {
        return consultar("SELECT id, codigo_produto, " + COLUNAS + " FROM produtos ORDER BY id");
    }


    public List<Produto> pesquisar(String campo, String termo) {
        String coluna = COLUNAS_PESQUISA.get(campo);
        if (coluna == null) {
            throw new IllegalArgumentException("Campo de pesquisa inválido: " + campo);
        }
        return consultar("SELECT id, codigo_produto, " + COLUNAS + " FROM produtos WHERE " + coluna
                + " ILIKE ? ORDER BY id", "%" + termo + "%");
    }



    public void inserir(Produto p) {
        executar("INSERT INTO produtos (" + COLUNAS + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                p.nome, p.valor_da_compra, p.marca, p.valor_da_venda, p.categoria, p.descricao,
                p.fornecedor, p.prateleira, p.data_da_compra, p.quant);
    }


    public void atualizar(Produto p) {
        executar("UPDATE produtos SET nome = ?, valor_da_compra = ?, marca = ?, valor_da_venda = ?,"
                        + " categoria = ?, descricao = ?, fornecedor = ?, prateleira = ?, data_da_compra = ?,"
                        + " quantidade = ? WHERE id = ?",
                p.nome, p.valor_da_compra, p.marca, p.valor_da_venda, p.categoria, p.descricao,
                p.fornecedor, p.prateleira, p.data_da_compra, p.quant, p.id);
    }


    public void excluir(int id) {
        executar("DELETE FROM produtos WHERE id = ?", id);
    }


    public void excluirTodos() {
        executar("DELETE FROM produtos");
    }



    public String gerarTxt() {
        StringBuilder txt = new StringBuilder();
        int cont = 1;
        for (Produto p : consultar("SELECT id, codigo_produto, " + COLUNAS + " FROM produtos ORDER BY nome ASC")) {
            txt.append("<< ").append(cont).append(" \n");
            txt.append("Nome: ").append(p.nome).append("\n");
            txt.append("Código: ").append(p.codigo_produto).append("\n");
            txt.append("Valor da compra: ").append(p.valor_da_compra).append("\n");
            txt.append("Marca: ").append(p.marca).append("\n");
            txt.append("Valor da venda: ").append(p.valor_da_venda).append("\n");
            txt.append("Categoria: ").append(p.categoria).append("\n");
            txt.append("Descrição: ").append(p.descricao).append("\n");
            txt.append("Fornecedor: ").append(p.fornecedor).append("\n");
            txt.append("Prateleira: ").append(p.prateleira).append("\n");
            txt.append("Data da compra: ").append(p.data_da_compra).append("\n");
            txt.append("Quantidade: ").append(p.quant).append("\n");
            txt.append(">> \n \n \n");
            cont++;
        }
        return txt.toString();
    }


    @Override
    protected Produto mapear(ResultSet rs) throws SQLException {
        Produto p = new Produto();
        p.id = rs.getInt("id");
        p.codigo_produto = rs.getString("codigo_produto");
        p.nome = rs.getString("nome");
        p.valor_da_compra = rs.getString("valor_da_compra");
        p.marca = rs.getString("marca");
        p.valor_da_venda = rs.getString("valor_da_venda");
        p.categoria = rs.getString("categoria");
        p.descricao = rs.getString("descricao");
        p.fornecedor = rs.getString("fornecedor");
        p.prateleira = rs.getString("prateleira");
        p.data_da_compra = rs.getString("data_da_compra");
        p.quant = rs.getString("quantidade");
        return p;
    }
}