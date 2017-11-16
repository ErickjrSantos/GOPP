package com.example.admin.estoquescan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class comentario_Activity extends AppCompatActivity {

    String nomeComent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario_review);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        String idComent = (String) b.get("posicao");
        nomeComent = (String) b.get("nome");
        String nomeUser = limitarString(nomeComent);


        String itemComent = (String) b.get("item");
        String dataComent = (String) b.get("data");

        TextView nome = (TextView) findViewById(R.id.txt_comentario_nome);
        setTitle(nomeUser+"\n"+dataComent);
        nome.setText(idComent);



    }
    public String limitarString(String nomeComent){
        if(nomeComent !=null && nomeComent.length() >15){
            return nomeComent.substring(0,16) + "...";
        }else{
            return nomeComent;
        }
    }
}
