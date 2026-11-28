package tablemodel;


import control.Produto;
import java.util.ArrayList;
import java.util.List;


public class ProdutoTM extends GenericTM<Produto> {


    private static final String[] COLUNAS = {"Nome", "Marca", "Data da compra", "Valor da venda", "Categoria",
            "Descrição", "Fornecedor", "Prateleira", "Quantidade"};


    public ProdutoTM() {
        this(new ArrayList<>());
    }


    public ProdutoTM(List<Produto> lista) {
        super(lista, COLUNAS, List.of(
                Produto::getNome, Produto::getMarca, Produto::getData_da_compra, Produto::getValor_da_venda,
                Produto::getCategoria, Produto::getDescricao, Produto::getFornecedor, Produto::getPrateleira,
                Produto::getQuant));
    }


    public Produto getFuncionario(int indice) {
        return getLinha(indice);
    }
}