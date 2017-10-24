package com.example.admin.estoquescan.Connection;

import android.os.AsyncTask;

import com.example.admin.estoquescan.Classes.Estoque;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by user on 24/10/17.
 */

public class ConnectionSpinnerSearch extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] objects) {

        StringBuilder response = new StringBuilder();

        try {
            URL obj = new URL("http://187.35.128.157:70/EstoqueScan/spinnerEstoque.php");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//            //envia POST
//            con.setRequestMethod("POST");
//
//            //dados POST
//            String urlParameters;
//            urlParameters = "json=" + objects[0];

            //Cria POST
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//            wr.writeBytes(urlParameters);
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
            String Jsonres = response.toString();

            JSONObject json = new JSONObject(Jsonres);
            int quantidadEstoques = json.getInt("QuantEstoques");
            JSONArray JEst = json.getJSONArray("EST");

            ArrayList<Estoque> estoqueArray = new ArrayList<>();

            for(int i = 0; i < quantidadEstoques; i++ ){
                int idEstoque = JEst.getJSONObject(i).getInt("id_estoque");
                String nomeEstoque = JEst.getJSONObject(i).getString("nome_estoque");
                String sigla = JEst.getJSONObject(i).getString("sigla");

                Estoque estoq = new Estoque();
                estoq.setId_estoque(idEstoque);
                estoq.setNome_estoque(nomeEstoque);
                estoq.setSigla(sigla);

                estoqueArray.add(estoq);
            }

            return  estoqueArray;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }
}
