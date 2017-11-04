package com.example.admin.estoquescan.Connection;

import android.os.AsyncTask;

import com.example.admin.estoquescan.Classes.NumeroPrateleira;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by user on 30/10/17.
 */

public class ConnectionSpinnerNumeroPrateleira extends AsyncTask {

    StringBuilder response = new StringBuilder();
    @Override
    protected Object doInBackground(Object[] objects) {

        try {
            URL obj = new URL("http://187.35.128.157:70/EstoqueScan/spinnerNumeroPrateleira.php");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            //envia POST
            con.setRequestMethod("POST");

            //dados POST
            String urlParameters;
            urlParameters = "idPrateleira=" + objects[0];

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
            int quantidadPrateleiras = json.getInt("quantNumPrateleira");
            JSONArray JEst = json.getJSONArray("NumeroPrateleiras");

            ArrayList<NumeroPrateleira> NumPrateleiraArray = new ArrayList<>();

            for (int i = 0; i < quantidadPrateleiras; i++) {
                int id_endereco = JEst.getJSONObject(i).getInt("id_endereco");
                String nome_NumPrateleira = JEst.getJSONObject(i).getString("andar");
                String endereco = JEst.getJSONObject(i).getString("endereco");

                NumeroPrateleira NumPrateleira = new NumeroPrateleira();
                NumPrateleira.setId_endereco(id_endereco);
                NumPrateleira.setAndar(nome_NumPrateleira);
                NumPrateleira.setEndereco(endereco);


                NumPrateleiraArray.add(NumPrateleira);
            }

            return NumPrateleiraArray;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}