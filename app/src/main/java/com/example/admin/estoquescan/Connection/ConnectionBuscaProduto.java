package com.example.admin.estoquescan.Connection;

import android.os.AsyncTask;

import com.example.admin.estoquescan.Classes.Product;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by user on 07/11/17.
 */

public class ConnectionBuscaProduto extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] objects) {
        StringBuilder response = new StringBuilder();

        try {
            URL obj = new URL("http://192.168.0.221:70/EstoqueScan/estoqueQuery.php");
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

            String codBarra = json.getString("codigoBarra");
            String codInterno = json.getString("codigoInterno");
            String descricao = json.getString("descricao");
            int estoque = json.getInt("estoque");
            String preco = json.getString("preco");
            boolean promocao = json.getBoolean("promocao");

            Product produto = new Product(codBarra,codInterno,descricao,estoque,preco,promocao);


            return produto;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


}
