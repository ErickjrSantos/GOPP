package com.example.admin.estoquescan.Service;


import com.example.admin.estoquescan.Classes.Comentarios;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;



/**
 * Created by user on 30/11/17.
 */

public interface ComentariosService {

    @POST("insereComentarios.php")
    Call<Void> insere(@Body Comentarios json);


}
