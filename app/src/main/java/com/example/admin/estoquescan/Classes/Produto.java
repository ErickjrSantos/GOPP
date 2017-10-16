package com.example.admin.estoquescan.Classes;

public class Produto {
    private String codigoBarra, codigoInterno, descricao, preco;
    private String enderereco;
    private String tipoEndereco;


    private int estoque;

    public boolean isPromocao() {
        return promocao;
    }

    private void setPromocao(boolean promocao) {
        this.promocao = promocao;
    }

    private boolean promocao;

    public Produto(String codigoBarra, String codigoInterno, String descricao, int estoque, String preco, boolean promocao){
        setCodigoBarra(codigoBarra);
        setCodigoInterno(codigoInterno);
        setDescricao(descricao);
        setEstoque(estoque);
        setPreco(preco);
        setPromocao(promocao);
    }


    public String getTipoEndereco() {return tipoEndereco;}

    public void setTipoEndereco(String tipoEndereco) {this.tipoEndereco = tipoEndereco;}

    public String getEnderereco() {return enderereco;}

    public void setEnderereco(String enderereco) {this.enderereco = enderereco;}

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

    public String getPreco() {
        return preco;
    }

    private void setPreco(String preco) {
        this.preco = preco;
    }
}
