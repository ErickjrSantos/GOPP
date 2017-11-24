package com.example.admin.estoquescan.TheadDialog;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.admin.estoquescan.Classes.Comentarios;

/**
 * Created by user on 21/11/17.
 */


public class EsperarLogin extends AsyncTask{

ProgressBar load;


    @Override
    protected void onPreExecute() {
        Log.i("AsyncTask","Exibindo ProgressDialog");
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        return null;
    }


    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }
}
