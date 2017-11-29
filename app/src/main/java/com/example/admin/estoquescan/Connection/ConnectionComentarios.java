package com.example.admin.estoquescan.Connection;



import android.content.Context;
import android.os.AsyncTask;
import com.example.admin.estoquescan.Classes.Comentarios;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Created by user on 14/11/17.
 */

public class ConnectionComentarios extends AsyncTask<Comentarios, Void, Comentarios[]> {


    Context context;

    public ConnectionComentarios(Context context){
        this.context = context;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        System.out.println("Antes");

    }
    @Override
    protected Comentarios[] doInBackground(Comentarios...comentarios) {
        try {
            String endereco = "http://187.35.128.157:70/EstoqueScan/comentarios.php";

            String inputLine  = WebClient.acessar(endereco);

            JSONObject json = new JSONObject(inputLine);
            JSONArray JEst = json.getJSONArray("Comentarios");
            Comentarios[] comentArray = new Comentarios[JEst.length()];

            for(int i = 0; i < JEst.length(); i++ ){
                String comentario = JEst.getJSONObject(i).getString("comentario");
                int id_comentario = JEst.getJSONObject(i).getInt("id_comentario");
                int usuario = JEst.getJSONObject(i).getInt("usuario");
                int produto = JEst.getJSONObject(i).getInt("produto");
                String data = JEst.getJSONObject(i).getString("data");
                String nome = JEst.getJSONObject(i).getString("nome");
                String nome_produto = JEst.getJSONObject(i).getString("nome_produto");
                int loja = JEst.getJSONObject(i).getInt("loja");

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date date = df.parse(data);
                df.applyPattern("dd/MM/yyyy");
                String dateBR = df.format(date);

                Comentarios coment = new Comentarios();
                coment.setComentario(comentario);
                coment.setId_comentarios(id_comentario);
                coment.setUsuario(usuario);
                coment.setProduto(produto);
                coment.setNome(nome);
                coment.setNome_produto(nome_produto);
                coment.setData(dateBR);
                coment.setLoja(loja);

                comentArray[i] = coment;

            }
            return  comentArray;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void onPostExecute(Comentarios[] comentarios) {
        super.onPostExecute(comentarios);
        System.out.println("depois");

    }
}
