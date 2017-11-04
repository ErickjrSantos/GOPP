package com.example.admin.estoquescan.Connection;

import android.os.AsyncTask;
import com.example.admin.estoquescan.Classes.Prateleira;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by user on 25/10/17.
 */

public class ConnectionSpinnersearchPrateleira extends AsyncTask {

    StringBuilder response = new StringBuilder();
    @Override
    protected Object doInBackground(Object[] objects) {

        try {
            URL obj = new URL("http://187.35.128.157:70/EstoqueScan/spinnerPrateleira.php");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            //envia POST
            con.setRequestMethod("POST");

            //dados POST
            String urlParameters;
            urlParameters = "idCorredor=" + objects[0];

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
            int quantidadPrateleiras = json.getInt("quantPrateleira");
            JSONArray JEst = json.getJSONArray("Prateleiras");

            ArrayList<Prateleira> PrateleiraArray = new ArrayList<>();

            for (int i = 0; i < quantidadPrateleiras; i++) {
                int id_prateleira = JEst.getJSONObject(i).getInt("id_prateleira");
                String nome_prateleira = JEst.getJSONObject(i).getString("nome_prateleira");

                Prateleira prateleira = new Prateleira();
                prateleira.setId_prateleira(id_prateleira);
                prateleira.setNome_prateleira(nome_prateleira);

                PrateleiraArray.add(prateleira);
            }

            return PrateleiraArray;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
