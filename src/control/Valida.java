

package control;


import javax.swing.JOptionPane;
import model.FuncionarioWrite;
import model.ProdutoWrite;


public class Valida {

    public static boolean testa = false;

    public static void cpff(){
        for (int i = 0; i < FuncionarioWrite.cadastros.size(); i++) {
            String cpf = FuncionarioWrite.cadastros.get(i).cpf;

            if(Funcionario.v[1] == null ? cpf == null : Funcionario.v[1].equals(cpf)){
                JOptionPane.showMessageDialog(null, "O CPF já existe na lista de registros");
                testa = true;
                break;
            }
        }
    }

    public static void produto(){
        for (int i = 0; i < ProdutoWrite.produtos.size(); i++) {
            String produto = ProdutoWrite.produtos.get(i).codigo_produto;

            if(Produto.v[1] == null ? produto == null : Produto.v[1].equals(produto)){
                JOptionPane.showMessageDialog(null, "O Código do produto já existe na lista de registros");
                testa = true;
                break;
            }
        }
    }
}
