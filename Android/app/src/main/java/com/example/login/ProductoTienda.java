package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.ModelosDeClases.Jugador;
import com.example.login.ModelosDeClases.ProductoVo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductoTienda extends AppCompatActivity {
    TextView id;
    ImageView imagen;
    TextView nom;
    TextView precio;
    TextView descrip ;
    TextView estado ;
    private ProgressBar spinner;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_tienda);
        id = (TextView) findViewById(R.id.idd);
        nom = (TextView) findViewById(R.id.nombre);
        precio= (TextView) findViewById(R.id.precio);
        descrip = (TextView) findViewById(R.id.descrip);
        estado = (TextView) findViewById(R.id.efectType);
        imagen = (ImageView) findViewById(R.id.imageView2);
        Bundle miBundle = this.getIntent().getExtras();
        if(miBundle!=null){
            Integer idd = miBundle.getInt("id");
            id.setText("Id: "+idd);

            String nomb = miBundle.getString("nombre");
            nom.setText(nomb);

            Integer pr = miBundle.getInt("precio");
            precio.setText("Precio: "+pr+" $");

            Integer stat = miBundle.getInt("efect");
            estado.setText("Estado: "+stat);

            String des = miBundle.getString("descrip");
            descrip.setText(des);
            String im = miBundle.getString("imagen");
            //imagen.setImageResource(Integer.parseInt(im));
            Picasso.get().load(im).into(imagen);


        }

        HttpLoggingInterceptor loggin = new HttpLoggingInterceptor();
        loggin.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(loggin);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        String username = SessionManager.getLoggedUsername(this);

        GetJugador jugador = retrofit.create(GetJugador.class);
        Call<Jugador> call2 = jugador.getJugador(username);
        call2.enqueue(new Callback<Jugador>() {
            @Override
            public void onResponse(Call<Jugador> call, Response<Jugador> response) {
                if (response.isSuccessful()) {
                    Jugador jugador = response.body();
                    int eurillos = jugador.getEurillos();
                    TextView coin = findViewById(R.id.textView4);
                    coin.setText(String.valueOf(eurillos));
                }
            }

            @Override
            public void onFailure(Call<Jugador> call, Throwable t) {
                Toast.makeText(ProductoTienda.this, "error", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void salirClick(View view){
        finish();

    }
    public void comprarClick(View view){

        TextView coin = findViewById(R.id.textView4);
        String coinText = coin.getText().toString();
        String precioTexto = precio.getText().toString();
        String precioNumerico = precioTexto.replaceAll("[^0-9]", "");
        if (Integer.parseInt(coinText) >= Integer.parseInt(precioNumerico)) {

            spinner = (ProgressBar) findViewById(R.id.progressBar2);

            HttpLoggingInterceptor loggin = new HttpLoggingInterceptor();
            loggin.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(loggin);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
            ComprarService producto = retrofit.create(ComprarService.class);

            String username = SessionManager.getLoggedUsername(this);

            Call<Jugador> call = producto.comprarProducto(nom.getText().toString(), username);
            spinner.setVisibility(View.VISIBLE);
            call.enqueue(new Callback<Jugador>() {
                @Override
                public void onResponse(Call<Jugador> call, Response<Jugador> response) {
                    if (response.isSuccessful()) {
                        spinner.setVisibility(View.GONE);
                        Toast.makeText(ProductoTienda.this, "Submitted Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ProductoTienda.this, Tienda.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(ProductoTienda.this, "Error, response is not as expected", Toast.LENGTH_SHORT).show();
                        spinner.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<Jugador> call, Throwable t) {
                    Log.e("Retrofit", "Error: " + t.getMessage()); // Log the error message
                    Toast.makeText(ProductoTienda.this, "Error No response", Toast.LENGTH_SHORT).show();
                    spinner.setVisibility(View.GONE);

                }
            });
        } else{
            Toast.makeText(ProductoTienda.this, "Error, Estás tieso hermano", Toast.LENGTH_SHORT).show();
        }



    }
}