package tablemodel;


import control.Funcionario;
import java.util.ArrayList;
import java.util.List;


public class FuncionarioTM extends GenericTM<Funcionario> {


    private static final String[] COLUNAS = {"Nome", "CPF", "E-mail", "Fone", "Idade", "Salário", "Celular",
            "Número", "Data de entrega", "Departamento", "Função", "Observação", "Sexo"};


    public FuncionarioTM() {
        this(new ArrayList<>());
    }


    public FuncionarioTM(List<Funcionario> lista) {
        super(lista, COLUNAS, List.of(
                Funcionario::getNome, Funcionario::getCpf, Funcionario::getEmail, Funcionario::getFone,
                Funcionario::getIdade, Funcionario::getSalario, Funcionario::getFonec, Funcionario::getNumero,
                Funcionario::getData, Funcionario::getDepartamento, Funcionario::getFuncao,
                Funcionario::getObservacao, Funcionario::getSexo));
    }


    public Funcionario getFuncionario(int indice) {
        return getLinha(indice);
    }
}