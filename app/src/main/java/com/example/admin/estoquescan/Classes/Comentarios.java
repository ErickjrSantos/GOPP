package com.example.admin.estoquescan.Classes;

import android.media.Image;

import java.sql.Date;

/**
 * Created by user on 14/11/17.
 */

public class Comentarios {
    private int id_comentarios;
    private String comentario;
    private int usuario;
    private String nome;
    private int produto;
    private String data;
    private String nome_produto;
    private int loja;
    private boolean ativo;
    private Image img;


    public String getNome_produto() {return nome_produto;}

    public void setNome_produto(String nome_produto) {this.nome_produto = nome_produto;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public int getLoja() {return loja;}

    public void setLoja(int loja) {this.loja = loja;}

    public Image getImg() {return img;}

    public void setImg(Image img) {this.img = img;}

    public int getId_comentarios() {return id_comentarios;}

    public void setId_comentarios(int id_comentarios) {this.id_comentarios = id_comentarios;}

    public String getComentario() {return comentario;}

    public void setComentario(String comentario) {this.comentario = comentario;}

    public int getUsuario() {return usuario;}

    public void setUsuario(int usuario) {this.usuario = usuario;}

    public int getProduto() {return produto;}

    public void setProduto(int produto) {this.produto = produto;}

    public boolean isAtivo() {return ativo;}

    public String getData() {return data;}

    public void setData(String data) {this.data = data;}

    public void setAtivo(boolean ativo) {this.ativo = ativo;}
}
