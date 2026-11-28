package tablemodel;


import control.Cliente;
import java.util.ArrayList;
import java.util.List;


public class ClienteTM extends GenericTM<Cliente> {


    private static final String[] COLUNAS = {"Nome", "CPF", "Fixo", "Idade", "Cidade", "Sexo", "Celular",
            "E-mail", "CEP", "Estado", "Bairro", "Número", "rua"};


    public ClienteTM() {
        this(new ArrayList<>());
    }


    public ClienteTM(List<Cliente> lista) {
        super(lista, COLUNAS, List.of(
                Cliente::getNome, Cliente::getCpf, Cliente::getFone, Cliente::getIdade, Cliente::getCidade,
                Cliente::getSexo, Cliente::getFonec, Cliente::getEmail, Cliente::getCep, Cliente::getEstado,
                Cliente::getBairro, Cliente::getNumero, Cliente::getRua));
    }


    public Cliente getFuncionario(int indice) {
        return getLinha(indice);
    }
}