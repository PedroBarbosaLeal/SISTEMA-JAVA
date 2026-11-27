package control;


public class Produto {

    public int id;
    public String codigo_produto;
    public String nome;
    public String valor_da_compra;
    public String marca;
    public String data_da_compra;
    public String valor_da_venda;
    public String categoria;
    public String descricao;
    public String fornecedor;
    public String prateleira;
    public String quant;

    public Produto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo_produto() {
        return codigo_produto;
    }

    public void setCodigo_produto(String codigo_produto) {
        this.codigo_produto = codigo_produto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValor_da_compra() {
        return valor_da_compra;
    }

    public void setValor_da_compra(String valor_da_compra) {
        this.valor_da_compra = valor_da_compra;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getData_da_compra() {
        return data_da_compra;
    }

    public void setData_da_compra(String data_da_compra) {
        this.data_da_compra = data_da_compra;
    }

    public String getValor_da_venda() {
        return valor_da_venda;
    }

    public void setValor_da_venda(String valor_da_venda) {
        this.valor_da_venda = valor_da_venda;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getPrateleira() {
        return prateleira;
    }

    public void setPrateleira(String prateleira) {
        this.prateleira = prateleira;
    }

    public String getQuant() {
        return quant;
    }

    public void setQuant(String quant) {
        this.quant = quant;
    }
}