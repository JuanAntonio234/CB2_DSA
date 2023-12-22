package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.ModelosDeClases.Mensaje;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MensajesSistemas extends AppCompatActivity {

    TextView mensajes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensajes_sistemas);
        mensajes=(TextView) findViewById(R.id.textMensaje);

        HttpLoggingInterceptor loggin = new HttpLoggingInterceptor();
        loggin.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(loggin);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        GetMensajes getMensajesservice = retrofit.create(GetMensajes.class);

        Call<List<Mensaje>> call = getMensajesservice.getMensajes();
        call.enqueue(new Callback<List<Mensaje>>() {
            @Override
            public void onResponse(Call<List<Mensaje>> call, Response<List<Mensaje>> response) {
                if (response.isSuccessful()) {
                    List<Mensaje> listaMensajes=response.body();
                    StringBuilder mensajesStringBuilder=new StringBuilder();
                    for(Mensaje mensaje:listaMensajes){
                        mensajesStringBuilder.append(mensaje.getMensaje()).append("\n");
                    }
                    mensajes.setText(mensajesStringBuilder.toString());
                }
            }
            @Override
            public void onFailure(Call<List<Mensaje>> call, Throwable t) {
                Toast.makeText(MensajesSistemas.this, "error", Toast.LENGTH_SHORT).show();

            }
        });
    }
}