package tablemodel;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import control.Cliente;
import control.Funcionario;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;


// Não usa o banco: só confere o que cada tabela mostra em cada coluna.
class TableModelTest {


    private Cliente cliente() {
        Cliente c = new Cliente();
        c.nome = "Cliente A";
        c.cpf = "111.111.111-11";
        c.idade = "30";
        c.cidade = "Salvador";
        return c;
    }


    @Test
    void clienteMostraIdadeECidadeNasColunasCertas() {
        List<Cliente> lista = new ArrayList<>();
        lista.add(cliente());
        ClienteTM tm = new ClienteTM(lista);


        assertEquals(13, tm.getColumnCount());
        assertEquals("Idade", tm.getColumnName(3));
        assertEquals("30", tm.getValueAt(0, 3));
        assertEquals("Cidade", tm.getColumnName(4));
        assertEquals("Salvador", tm.getValueAt(0, 4));
    }


    @Test
    void funcionarioMostraSalarioNaColunaCerta() {
        Funcionario f = new Funcionario();
        f.nome = "Funcionario A";
        f.salario = "2500";
        List<Funcionario> lista = new ArrayList<>();
        lista.add(f);
        FuncionarioTM tm = new FuncionarioTM(lista);


        assertEquals("Salário", tm.getColumnName(5));
        assertEquals("2500", tm.getValueAt(0, 5));
    }


    @Test
    void removerELimparAtualizamAsLinhas() {
        List<Cliente> lista = new ArrayList<>();
        lista.add(cliente());
        lista.add(cliente());
        ClienteTM tm = new ClienteTM(lista);


        tm.remove(0);
        assertEquals(1, tm.getRowCount());


        tm.limpar();
        assertTrue(tm.isEmpty());
    }


    @Test
    void celulasNaoSaoEditaveis() {
        ClienteTM tm = new ClienteTM();


        assertFalse(tm.isCellEditable(0, 0));
    }
}