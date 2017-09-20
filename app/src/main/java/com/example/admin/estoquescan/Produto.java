package com.example.admin.estoquescan;

public class Produto {
    private String codigoBarra, codigoInterno, descricao;
    private int estoque;
    private double preco;

    public Produto(String codigoBarra, String codigoInterno, String descricao, int estoque, double preco){
        setCodigoBarra(codigoBarra);
        setCodigoInterno(codigoInterno);
        setDescricao(descricao);
        setEstoque(estoque);
        setPreco(preco);
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    private void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public String getCodigoInterno() {
        return codigoInterno;
    }

    private void setCodigoInterno(String codigoInterno) {
        this.codigoInterno = codigoInterno;
    }

    public String getDescricao() {
        return descricao;
    }

    private void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getEstoque() {
        return estoque;
    }

    private void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public double getPreco() {
        return preco;
    }

    private void setPreco(double preco) {
        this.preco = preco;
    }
}
