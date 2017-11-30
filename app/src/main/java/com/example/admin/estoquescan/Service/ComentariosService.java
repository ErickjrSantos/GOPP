package com.example.admin.estoquescan.Service;

import com.example.admin.estoquescan.Classes.Comentarios;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by user on 30/11/17.
 */

public interface ComentariosService {
    @POST("insereComentarios.php")
    Call<Void> insere(@Path("json") Comentarios comentarios);
}
