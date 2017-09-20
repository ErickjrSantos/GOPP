package com.example.admin.estoquescan;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by user on 19/09/17.
 */

public class Connection extends AsyncTask {

    String url = "http://187.35.128.157:70/StoqueScan/Scan.php";

    @Override
    protected Object doInBackground(Object[] objects) {

        StringBuffer response = new StringBuffer();
        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            //envia POST
            con.setRequestMethod("POST");

            //dados POST
            String urlParameters = "user=" + objects[0] + "&password=" + objects[1];

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
                    String id = jsonObjt.getString("codigoP");
                    String nome = jsonObjt.getString("nomeP");
                    String resposta = jsonObjt.getString("resposta");

                }catch (Exception e){
                    e.printStackTrace();
                }
            }


        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;
    }
}
