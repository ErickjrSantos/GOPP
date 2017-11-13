package com.example.admin.estoquescan.Classes;

/**
 * Created by user on 25/10/17.
 */

public class Corredor {
    private int id_corredor;
    private String nome_corredor;



    public int getId_corredor() {return id_corredor;}

    public void setId_corredor(int id_corredor) {this.id_corredor = id_corredor;}

    public String getNome_corredor() {return nome_corredor;}

    public void setNome_corredor(String nome_corredor) {this.nome_corredor = nome_corredor;}

    @Override
    public String toString() {
        String id;
        String pulo;
        if(getId_corredor()==0){
            id = "";
            pulo ="";
        }else{
            id=" ID: "+getId_corredor();
            pulo = "\n";
        }
        return id +pulo+getNome_corredor();
    }
}
