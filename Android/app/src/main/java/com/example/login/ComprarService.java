package com.example.login;

import com.example.login.ModelosDeClases.Credenciales;
import com.example.login.ModelosDeClases.Jugador;
import com.example.login.ModelosDeClases.ProductoVo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ComprarService {
    @GET("dsaApp/tienda/comprar/{pnombre}/{nombre}")
    Call<Jugador>comprarProducto(@Path("pnombre") String pnombre, @Path("nombre") String nombre);
}
