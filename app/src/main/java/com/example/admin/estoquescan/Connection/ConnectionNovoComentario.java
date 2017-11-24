package com.example.admin.estoquescan.Connection;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by user on 23/11/17.
 */

public class ConnectionNovoComentario extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] objects) {


        StringBuilder response = new StringBuilder();

        try {
            URL obj = new URL("http://187.35.128.157:70/EstoqueScan/insereComentarios.php");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            //envia POST
            con.setRequestMethod("POST");

            //dados POST
            String urlParameters;
            urlParameters = "json=" + objects[0];

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

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }
}
