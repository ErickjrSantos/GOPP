package com.example.admin.estoquescan.Classes;

/**
 * Created by user on 26/10/17.
 */

public class Prateleira {
    private int id_prateleira;
    private String nome_prateleira;


    public int getId_prateleira() {return id_prateleira;}

    public void setId_prateleira(int id_prateleira) {this.id_prateleira = id_prateleira;}

    public String getNome_prateleira() {return nome_prateleira;}

    public void setNome_prateleira(String nome_prateleira) {this.nome_prateleira = nome_prateleira;}

    @Override
    public String toString() {
        String preFixo;
        String idPr;
        if(getId_prateleira()==0){
            preFixo = "";
            idPr ="";
        }else{
            preFixo = "\nID: ";
            idPr = String.valueOf(getId_prateleira());
        }
        return getNome_prateleira()+preFixo+ idPr ;
    }
}
