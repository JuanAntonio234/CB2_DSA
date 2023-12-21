package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.login.ModelosDeClases.CredencialesRegistro;
import com.example.login.ModelosDeClases.CredencialesRespuesta;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    private Window window;

    private Button registerButton;
    public static String pswd;
    public static String usrname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.window = getWindow();
    }

    public void registeronClick(View v) {
        // Restablecer el OnClickListener
        registerButton = findViewById(R.id.reg);

        // Recogemos los datos introducidos por el usuario
        EditText editText = findViewById(R.id.username);
        this.usrname = editText.getText().toString();
        EditText editText2 = findViewById(R.id.password);
        this.pswd = editText2.getText().toString();
        EditText editText3 = findViewById(R.id.email);
        String mail = editText3.getText().toString();
        EditText editText4 = findViewById(R.id.confirmPassword);
        String confirmPswd = editText4.getText().toString();


        registerButton=(Button)findViewById(R.id.reg);

        if (pswd.equals(confirmPswd)) {
            CredencialesRegistro c = new CredencialesRegistro(usrname, pswd, mail);


            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8080/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            RegisterService register = retrofit.create(RegisterService.class);

            Call<CredencialesRespuesta> call = register.CreateCredencialesRegistro(c);

            call.enqueue(new Callback<CredencialesRespuesta>() {
                @Override
                public void onResponse(Call<CredencialesRespuesta> call, Response<CredencialesRespuesta> response) {
                    if (response.isSuccessful()) {

                        Context context = RegisterActivity.this;

                        SessionManager.registerUser(context, usrname);

                        Toast.makeText(RegisterActivity.this, "Submitted Successfully", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(context, MainMenu.class);
                        startActivity(intent);
                        finish();
                    } else {
                        String errorMessage;
                        try {
                            // Intenta obtener el mensaje de error desde el cuerpo de error
                            CredencialesRespuesta errorResponse = new Gson().fromJson(response.errorBody().string(), CredencialesRespuesta.class);
                            errorMessage = errorResponse.getMessage();
                            editText.setText("");
                            editText2.setText("");
                            editText3.setText("");
                            editText4.setText("");
                        } catch (IOException e) {
                            errorMessage = "Error reading/parsing error response body";
                            e.printStackTrace();
                        }

                        Toast.makeText(RegisterActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<CredencialesRespuesta> call, Throwable t) {
                    Toast.makeText(RegisterActivity.this, "Error no response", Toast.LENGTH_SHORT).show();

                }
            });

        } else {

            editText.setText("");
            editText2.setText("");
            editText3.setText("");
            editText4.setText("");

            Toast.makeText(RegisterActivity.this, "Error, las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        }
    }
}