package com.example.admin.estoquescan;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.estoquescan.Connection.ConnectionComentarioAtivo;

import org.json.JSONException;
import org.json.JSONObject;

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

        final int id_comentario = b.getInt("ID");
        String itemComent = (String) b.get("item");
        String dataComent = (String) b.get("data");

        TextView nome = (TextView) findViewById(R.id.txt_comentario_nome);
        TextView descricao = (TextView) findViewById(R.id.txt_comentario);
        setTitle(nomeUser+"\n"+dataComent);

        descricao.setText(idComent);
        nome.setText(itemComent);

        FloatingActionButton btn = (FloatingActionButton) findViewById(R.id.float_btn_desativa);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConnectionComentarioAtivo conn = new ConnectionComentarioAtivo();
                JSONObject json = new JSONObject();
                try {
                    json.put("id_comentario", id_comentario);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                conn.execute(json);
                Toast.makeText(comentario_Activity.this, "Desativado com sucesso", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public String limitarString(String nomeComent){
        if(nomeComent !=null && nomeComent.length() >15){
            return nomeComent.substring(0,16) + "...";
        }else{
            return nomeComent;
        }
    }
//    public void DesativaSimOrNao(){
//        AlertDialog alert =
//    }
}
