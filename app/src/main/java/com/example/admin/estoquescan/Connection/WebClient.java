package com.example.admin.estoquescan.Connection;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by user on 27/11/17.
 */

public class WebClient  {


    public static String acessar(String endereco){
        try {

            URL obj = new URL(endereco);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setDoOutput(true);

            InputStream is = con.getInputStream();
            Scanner scanner = new Scanner(is);
            String inputLine = scanner.useDelimiter("\\A").next();

            scanner.close();
            con.disconnect();

            return inputLine;

        }catch (Exception e){
            e.printStackTrace();
        }
    return null;
    }



}
