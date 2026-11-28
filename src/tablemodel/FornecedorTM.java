package tablemodel;


import control.Fornecedor;
import java.util.ArrayList;
import java.util.List;


public class FornecedorTM extends GenericTM<Fornecedor> {


    private static final String[] COLUNAS = {"Nome", "CNPJ", "E-mail", "Fone", "Numero", "Descricao",
            "Celular", "CEP", "Bairro", "Estado", "Cidade", "Rua"};


    public FornecedorTM() {
        this(new ArrayList<>());
    }


    public FornecedorTM(List<Fornecedor> lista) {
        super(lista, COLUNAS, List.of(
                Fornecedor::getNome, Fornecedor::getCnpj, Fornecedor::getEmail, Fornecedor::getFone,
                Fornecedor::getNumero, Fornecedor::getDescricao, Fornecedor::getFonec, Fornecedor::getCep,
                Fornecedor::getBairro, Fornecedor::getEstado, Fornecedor::getCidade, Fornecedor::getRua));
    }


    public Fornecedor getFornecedor(int indice) {
        return getLinha(indice);
    }
}