package com.example.admin.estoquescan.Service;

import com.example.admin.estoquescan.Classes.Produto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by user on 07/12/17.
 */

public interface ProdutosService {
    @POST("buscaProduto.php")
    Call<String> busca(@Body int json);
}
