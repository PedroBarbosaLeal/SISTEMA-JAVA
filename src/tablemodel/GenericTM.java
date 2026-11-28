package tablemodel;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import javax.swing.table.AbstractTableModel;


/**
 * Modelo de tabela do Swing para qualquer entidade. Cada tabela só informa os
 * títulos das colunas e como ler o valor de cada coluna, e o resto é comum.
 */
public class GenericTM<T> extends AbstractTableModel {


    private final List<T> linhas;
    private final String[] colunas;
    private final List<Function<T, Object>> leitores;


    public GenericTM(List<T> linhas, String[] colunas, List<Function<T, Object>> leitores) {
        this.linhas = new ArrayList<>(linhas);
        this.colunas = colunas;
        this.leitores = leitores;
    }


    @Override
    public int getColumnCount() {
        return colunas.length;
    }


    @Override
    public int getRowCount() {
        return linhas.size();
    }


    @Override
    public String getColumnName(int coluna) {
        return colunas[coluna];
    }


    @Override
    public Class<?> getColumnClass(int coluna) {
        return String.class;
    }


    @Override
    public Object getValueAt(int linha, int coluna) {
        return leitores.get(coluna).apply(linhas.get(linha));
    }


    @Override
    public boolean isCellEditable(int linha, int coluna) {
        return false;
    }


    public T getLinha(int indice) {
        return linhas.get(indice);
    }


    public void adicionar(T item) {
        linhas.add(item);
        int ultimo = linhas.size() - 1;
        fireTableRowsInserted(ultimo, ultimo);
    }


    public void addLista(List<T> itens) {
        int antigo = linhas.size();
        linhas.addAll(itens);
        fireTableRowsInserted(antigo, linhas.size() - 1);
    }


    // Troca a linha inteira pelo novo objeto e avisa a tabela.
    public void setValueAt(T item, int indice) {
        linhas.set(indice, item);
        fireTableRowsUpdated(indice, indice);
    }


    public void remove(int indice) {
        linhas.remove(indice);
        fireTableRowsDeleted(indice, indice);
    }


    public void limpar() {
        linhas.clear();
        fireTableDataChanged();
    }


    public boolean isEmpty() {
        return linhas.isEmpty();
    }
}