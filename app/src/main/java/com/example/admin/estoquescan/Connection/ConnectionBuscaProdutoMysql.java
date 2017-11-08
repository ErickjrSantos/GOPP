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
 * Created by user on 08/11/17.
 */

public class ConnectionBuscaProdutoMysql extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] objects) {

        StringBuilder response = new StringBuilder();

        try {
            URL obj = new URL("http://192.168.0.221:70/EstoqueScan/estoqueQueryProduto.php");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            //envia POST
            con.setRequestMethod("POST");

            //dados POST
            String urlParameters;
            urlParameters = "idEndereco=" + objects[0];

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
            String responta = response.toString();
            JSONObject json = new JSONObject(responta);

            String codBarra = json.getString("codBarra");
            int codInterno = json.getInt("id_produto");
            String data = json.getString("data_validade");
            String check = json.getString("check");


            Produto produto = new Produto();

                produto.setCodBarra(codBarra);
                produto.setId_produto(codInterno);
                produto.setData_validade(data);
                return produto;


        } catch (Exception e) {
            e.printStackTrace();
        return null;
        }
    }
}
