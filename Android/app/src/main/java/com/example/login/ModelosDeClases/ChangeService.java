package com.example.login.ModelosDeClases;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ChangeService {

    @Headers("Content-Type:application/json")
    @PUT("dsaApp/jugadores/updatePassword")
    Call<CredencialesRespuesta> CreateCredencialesChangePwd(@Body CredencialesChangePassword credenciales);
   @Headers("Content-Type:application/json")
    @PUT("dsaApp/jugadores/updateUsername")
    Call<CredencialesRespuesta>CreateCredencialesChangeUsername(@Body CredencialesChangeUsername credenciales);
}
