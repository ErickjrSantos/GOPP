package com.example.admin.estoquescan.Connection;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.admin.estoquescan.Classes.User;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class ConnectionLogin extends AsyncTask<Object, Void, User> {
   private Context context;
   private ProgressDialog dialog;
   private int value = 10;


    @Override
    protected void onProgressUpdate(Void... value) {

        super.onProgressUpdate(value);
    }

    public ConnectionLogin(Context context){
       this.context = context;
   }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(context,"Aguarde","Verificando credenciais...");

        super.onPreExecute();
    }

    @Override
    protected User doInBackground(Object[] objects) {


        StringBuilder response = new StringBuilder();
        User usuario = User.getSavedUser();
        try {
            URL obj = new URL("http://187.35.128.157:70/EstoqueScan/login.php");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            //envia POST
            con.setRequestMethod("POST");
            //dados POST
            String urlParameters;
            if(objects.length == 2) {
                urlParameters = "user=" + objects[0] + "&password=" + objects[1];
            }else{
                urlParameters = "id=" + objects[0];
            }
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

            JSONObject jsonObjt = new JSONObject(JsonStr);
            int codigo = jsonObjt.getInt("codigo");
            String nome = jsonObjt.getString("nome");
            String image = jsonObjt.getString("foto");
            String resposta = jsonObjt.getString("resposta");

            if(resposta.equals("success")) {
                usuario.setUsername(nome);
                usuario.setId(codigo);
                usuario.setImage(image);
            }else{
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return usuario;
    }

    @Override
    protected void onPostExecute(User user) {

        try {
            for (int i = 0; i<=value; i++) {
                dialog.setProgress(value);
                dialog.setProgress(i);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            Toast.makeText(context, "Erro...", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        dialog.dismiss();
    }
}
