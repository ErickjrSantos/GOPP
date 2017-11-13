package com.example.admin.estoquescan.Classes;

/**
 * Created by user on 30/10/17.
 */

public class NumeroPrateleira {
    int id_endereco;
    String andar;
    String endereco;

    public int getId_endereco() {return id_endereco;}

    public void setId_endereco(int id_endereco) {this.id_endereco = id_endereco;}

    public String getAndar() {return andar;}

    public void setAndar(String andar) {this.andar = andar;}

    public String getEndereco() {return endereco;}

    public void setEndereco(String endereco) {this.endereco = endereco;}

    @Override
    public String toString() {
        String andar;
        String grau;
        String endereco;
        if(getId_endereco()==0){
            andar = "";
            grau = "";
            endereco = "";
        }else{
            andar = "ANDAR: "+String.valueOf(getAndar());
            grau = "°";
            endereco = "\nEnd:. ";
        }
        return andar+grau+endereco+getEndereco();
    }
}
