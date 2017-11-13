package com.example.admin.estoquescan.Classes;

/**
 * Created by user on 24/10/17.
 */

public class Estoque {
    int id_estoque;
    String nome_estoque;
    String sigla;

    public Estoque(int idEstoque,String nomeEstoque){
          this.id_estoque = idEstoque;
        this.nome_estoque = nomeEstoque;
    }

    public Estoque(){

    }

    public int getId_estoque() {return id_estoque;}

    public void setId_estoque(int id_estoque) {this.id_estoque = id_estoque;}

    public String getNome_estoque() {return nome_estoque;}

    public void setNome_estoque(String nome_estoque) {this.nome_estoque = nome_estoque;}

    public String getSigla() {return sigla;}

    public void setSigla(String sigla) {this.sigla = sigla;}

    @Override
    public String toString() {
        String sigla;

        if (getId_estoque()==0){
            sigla = "";
        }else{
            sigla = "\nSIGLA:   ";
        }
        return getNome_estoque()+sigla+getSigla();
    }
}
