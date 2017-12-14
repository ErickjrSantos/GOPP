package com.example.admin.estoquescan.Classes;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by user on 08/11/17.
 */

public class Produto implements Serializable{
    private int id_produto;
    private String data_validade;
    private String codBarra;
    private int quantLocal;

    public int getQuantLocal() {return quantLocal;}

    public void setQuantLocal(int quantLocal) {this.quantLocal = quantLocal;}

    public int getId_produto() {return id_produto;}

    public void setId_produto(int id_produto) {this.id_produto = id_produto;}

    public String getData_validade() {return data_validade;}

    public void setData_validade(String data_validade) {this.data_validade = data_validade;}

    public String getCodBarra() {return codBarra;}

    public void setCodBarra(String codBarra) {this.codBarra = codBarra;}
}
