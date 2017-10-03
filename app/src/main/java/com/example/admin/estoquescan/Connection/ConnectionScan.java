package com.example.admin.estoquescan.Connection;

import android.os.AsyncTask;

import com.example.admin.estoquescan.Classes.Produto;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by user on 19/09/17.
 */

public class ConnectionScan extends AsyncTask {

    String url = "http://187.35.128.157:70/EstoqueScan/estoqueQuery.php";
    Produto p;

    @Override
    protected Produto doInBackground(Object[] objects) {

        StringBuffer response = new StringBuffer();
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            //envia POST
            con.setRequestMethod("POST");

            //dados POST
            String urlParameters = "codb=" + objects[0] + "&codEmpresa=" + objects[1];

            //Cria POST
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
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
            String JsonStr = response.toString();
            if(JsonStr != null || JsonStr != ""){
                try{

                    JSONObject jsonObjt = new JSONObject(JsonStr);
                    String codb = jsonObjt.getString("codigoBarra");
                    String codi = jsonObjt.getString("codigoInterno");
                    String desc = jsonObjt.getString("descricao");
                    int estoque = jsonObjt.getInt("estoque");
                    String preco = jsonObjt.getString("preco");
                    boolean promocao = jsonObjt.getBoolean("promocao");

                    p = new Produto(codb, codi, desc, estoque, preco, promocao);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return p;
    }
}
