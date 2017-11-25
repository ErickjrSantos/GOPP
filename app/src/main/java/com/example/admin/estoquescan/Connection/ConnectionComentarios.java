package com.example.admin.estoquescan.Connection;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.admin.estoquescan.Classes.Comentarios;
import com.example.admin.estoquescan.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by user on 14/11/17.
 */

public class ConnectionComentarios extends AsyncTask {

    ProgressDialog dialog;
    Context context;
    public ConnectionComentarios(Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setTitle("Carregando lista");
        dialog.setMessage("Aguarde...");
        dialog.show();
        System.out.println("Antes");
    }

    @Override
    protected List<Comentarios> doInBackground(Object[] objects) {

            //StringBuilder response = new StringBuilder();
            try {
                URL obj = new URL("http://192.168.0.221:70/EstoqueScan/comentarios.php");
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                con.setDoOutput(true);

                InputStream is = con.getInputStream();
                Scanner scanner = new Scanner(is);
                String inputLine = scanner.useDelimiter("\\A").next();

                scanner.close();
                con.disconnect();

                JSONObject json = new JSONObject(inputLine);
                JSONArray JEst = json.getJSONArray("Comentarios");
                List<Comentarios> comentArray = new ArrayList<>();

                for(int i = 0; i < JEst.length(); i++ ){
                    String comentario = JEst.getJSONObject(i).getString("comentario");
                    int id_comentario = JEst.getJSONObject(i).getInt("id_comentario");
                    int usuario = JEst.getJSONObject(i).getInt("usuario");
                    int produto = JEst.getJSONObject(i).getInt("produto");
                    String data = JEst.getJSONObject(i).getString("data");
                    String nome = JEst.getJSONObject(i).getString("nome");
                    String nome_produto = JEst.getJSONObject(i).getString("nome_produto");
                    int loja = JEst.getJSONObject(i).getInt("loja");

                    Comentarios coment = new Comentarios();
                    coment.setComentario(comentario);
                    coment.setId_comentarios(id_comentario);
                    coment.setUsuario(usuario);
                    coment.setProduto(produto);
                    coment.setNome(nome);
                    coment.setNome_produto(nome_produto);
                    coment.setData(data);
                    coment.setLoja(loja);

                    comentArray.add(coment);

                }
                return  comentArray;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

    }

    @Override
    protected void onPostExecute(Object o) {
       System.out.println("depois");
       dialog.dismiss();
        super.onPostExecute(o);
    }



    @Override
    protected void onProgressUpdate(Object[] values) {

        super.onProgressUpdate(values);
    }


}
