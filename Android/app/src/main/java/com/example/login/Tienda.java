package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.ModelosDeClases.CredencialesRespuesta;
import com.example.login.ModelosDeClases.Jugador;
import com.example.login.ModelosDeClases.ProductoVo;

import java.util.ArrayList;
import retrofit2.Call;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Tienda extends AppCompatActivity {


    private ArrayList<ProductoVo> listProductos;
    private AdapterDatos adaptador;
    private RecyclerView recyclerProd;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);

        listProductos=new ArrayList<>();
        recyclerProd=(RecyclerView) findViewById(R.id.recyclerId);
        recyclerProd.setLayoutManager(new LinearLayoutManager(this));


        HttpLoggingInterceptor loggin = new HttpLoggingInterceptor();
        loggin.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(loggin);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        TiendaService productos = retrofit.create(TiendaService.class);
        Call<ArrayList<ProductoVo>> call = productos.getTiendaProductos();
        call.enqueue(new Callback<ArrayList<ProductoVo>>() {
            @Override
            public void onResponse(Call<ArrayList<ProductoVo>> call, Response<ArrayList<ProductoVo>> response) {
                if (response.isSuccessful()){
                    ArrayList<ProductoVo> listProductos = response.body();

                    AdapterDatos adapter=new AdapterDatos(listProductos,Tienda.this);

                    recyclerProd.setAdapter(adapter);
                    adapter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Bundle miBundle=new Bundle();
                            miBundle.putString("imagen",listProductos.get(recyclerProd.getChildAdapterPosition(view)).getImagen());
                            miBundle.putString("id",listProductos.get(recyclerProd.getChildAdapterPosition(view)).getId());
                            miBundle.putInt("precio",listProductos.get(recyclerProd.getChildAdapterPosition(view)).getPrecio());
                            miBundle.putString("nombre",listProductos.get(recyclerProd.getChildAdapterPosition(view)).getNombre());
                            miBundle.putInt("efect",listProductos.get(recyclerProd.getChildAdapterPosition(view)).getEfect());
                            miBundle.putString("descrip",listProductos.get(recyclerProd.getChildAdapterPosition(view)).getDescrip());
                            Log.v("jdks","sodf " + listProductos.get(recyclerProd.getChildAdapterPosition(view)).getEfect());
                            //miBundle.putInt("efectType",listProductos.get(recyclerProd.getChildAdapterPosition(view)).getEfectType());
                            Intent miIntentq = new Intent(Tienda.this, ProductoTienda.class);
                            miIntentq.putExtras(miBundle);

                            startActivity(miIntentq);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProductoVo>> call, Throwable t) {
                Toast.makeText(Tienda.this,"error",Toast.LENGTH_SHORT).show();

            }
        });

        String username = SessionManager.getLoggedUsername(this);
        GetJugador jugador = retrofit.create(GetJugador.class);
        Call<Jugador> call2 = jugador.getJugador(username);
        call2.enqueue(new Callback<Jugador>() {
            @Override
            public void onResponse(Call<Jugador> call, Response<Jugador> response) {
                if (response.isSuccessful()) {
                    Jugador jugador = response.body();
                    int eurillos = jugador.getEurillos();
                    TextView textView3 = findViewById(R.id.textView3);
                    textView3.setText(String.valueOf(eurillos));
                }
            }

            @Override
            public void onFailure(Call<Jugador> call, Throwable t) {
                Toast.makeText(Tienda.this, "error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void menuClick(View view){
        startActivity(new Intent(this, MainActivity.class));
    }
}