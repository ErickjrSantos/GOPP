package com.example.admin.estoquescan.Connection;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.admin.estoquescan.Classes.Comentarios;
import com.example.admin.estoquescan.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 14/11/17.
 */

public class ConnectionComentarios extends AsyncTask {


    @Override
    protected List<Comentarios> doInBackground(Object[] objects) {

            StringBuilder response = new StringBuilder();
            try {
                URL obj = new URL("http://192.168.0.221:70/EstoqueScan/comentarios.php");
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                //envia POST
                con.setRequestMethod("POST");

                //Cria POST
                con.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.flush();
                wr.close();

                int responseCode = con.getResponseCode();
                System.out.println("Codigo de resposta: " + responseCode);

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                String responta = response.toString();
                JSONObject json = new JSONObject(responta);
                int quanti = json.getInt("quant");
                JSONArray JEst = json.getJSONArray("Comentarios");

                List<Comentarios> comentArray = new ArrayList<>();

                for(int i = 0; i < quanti; i++ ){
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
                    publishProgress(coment);
                }
                return  comentArray;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

    @Override
    protected void onPostExecute(Object o) {
        Log.d("onPostExecute","meupai do ceu");
       System.out.println("depois");
        super.onPostExecute(o);
    }

    @Override
    protected void onPreExecute() {


        System.out.println("Antes");
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        System.out.println("NO time");
        super.onProgressUpdate(values);
    }


}
