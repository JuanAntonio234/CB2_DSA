package com.example.login;

import com.example.login.ModelosDeClases.Mensaje;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetMensajes {
    @GET("dsaApp/mensajes/todosmen")
    Call<List<Mensaje>> getMensajes();
}