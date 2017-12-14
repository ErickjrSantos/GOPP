package com.example.admin.estoquescan.Retrofit;


import com.example.admin.estoquescan.Service.ComentariosService;
import com.example.admin.estoquescan.Service.LoginService;
import com.example.admin.estoquescan.Service.ProdutosService;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 30/11/17.
 */

public class RetrofitInicializador {
    private final Retrofit retrofit;



    public RetrofitInicializador(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .readTimeout(300, TimeUnit.SECONDS);
        httpClient.addInterceptor(loggingInterceptor);

         retrofit = new Retrofit.Builder()
                 .baseUrl("http://187.35.128.157:70/EstoqueScan/")
                 .addConverterFactory(GsonConverterFactory.create())
                 .client(httpClient.build())
                 .build();

    }

    public ComentariosService setComentarioService() {
        return retrofit.create(ComentariosService.class);
    }
    public LoginService getLoginService(){
        return retrofit.create(LoginService.class);
    }
    public ProdutosService getProdutosService(){
        return retrofit.create(ProdutosService.class);
    }

}
