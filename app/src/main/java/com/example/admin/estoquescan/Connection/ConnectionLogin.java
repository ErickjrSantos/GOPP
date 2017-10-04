package com.example.admin.estoquescan.Connection;

import android.os.AsyncTask;

import com.example.admin.estoquescan.Classes.Produto;
import com.example.admin.estoquescan.Classes.User;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Admin on 03/10/2017.
 */

public class ConnectionLogin extends AsyncTask {

    String url = "http://187.35.128.157:70/EstoqueScan/login.php";


    @Override
    protected User doInBackground(Object[] objects) {

        StringBuffer response = new StringBuffer();
        User usuario = new User();
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
            try{
                JSONObject jsonObjt = new JSONObject(JsonStr);
                int codigo = jsonObjt.getInt("codigo");
                String nome = jsonObjt.getString("nome");
                String resposta = jsonObjt.getString("resposta");
                if(resposta.equals("success")) {
                    usuario.setUser(nome);
                    usuario.setCodigo(codigo);
                    usuario.setResposta(resposta);
                }else{
                    return null;
                }

            }catch (Exception e){
                e.printStackTrace();
                return null;
            }


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return usuario;
    }
}
