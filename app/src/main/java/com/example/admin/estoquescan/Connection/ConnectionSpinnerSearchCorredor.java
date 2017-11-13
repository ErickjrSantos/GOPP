package com.example.admin.estoquescan.Connection;

import android.os.AsyncTask;

import com.example.admin.estoquescan.Classes.Corredor;
import com.example.admin.estoquescan.Classes.Estoque;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by user on 25/10/17.
 */

public class ConnectionSpinnerSearchCorredor extends AsyncTask {

    StringBuilder response = new StringBuilder();
    @Override
    protected Object doInBackground(Object[] objects) {

        try {
            URL obj = new URL("http://187.35.128.157:70/EstoqueScan/spinnerCorredor.php");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//            //envia POST
            con.setRequestMethod("POST");

//            //dados POST
            String urlParameters;
            urlParameters = "idEstoque=" + objects[0];

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
            String Jsonres = response.toString();

            JSONObject json = new JSONObject(Jsonres);
            int quantidadEstoques = json.getInt("quantCorredores");
            JSONArray JEst = json.getJSONArray("corredores");

            ArrayList<Corredor> CorredorArray = new ArrayList<>();
            Corredor corr = new Corredor();
            corr.setNome_corredor("");
            corr.setId_corredor(0);
            CorredorArray.add(corr);

            for (int i = 0; i < quantidadEstoques; i++) {
                int id_corredor = JEst.getJSONObject(i).getInt("id_corredor");
                String nome_corredor = JEst.getJSONObject(i).getString("nome_corredor");
                Corredor corredor = new Corredor();
                corredor.setId_corredor(id_corredor);
                corredor.setNome_corredor(nome_corredor);

                CorredorArray.add(corredor);
            }

            return CorredorArray;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

}