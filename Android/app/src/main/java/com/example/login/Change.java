package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.ModelosDeClases.ChangeService;
import com.example.login.ModelosDeClases.CredencialesChangePassword;
import com.example.login.ModelosDeClases.CredencialesChangeUsername;
import com.example.login.ModelosDeClases.CredencialesRespuesta;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class Change extends AppCompatActivity {

    TextView textView2;
    EditText passwordEditUsername;
    EditText passwordEditCurrentPassword;
    EditText passwordEditNewPassword;
    EditText usernameEditUsername;
    EditText usernameEditNewUsername;
    EditText usernameEditPassword;
    EditText confirmPasswordChangePwd;
    Button enviarCambioBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        textView2=findViewById(R.id.textView2);
        passwordEditUsername=findViewById(R.id.usernameChangePassword);
        passwordEditCurrentPassword=findViewById(R.id.currentPasswordChangePwd);
        passwordEditNewPassword=findViewById(R.id.newPasswordChangePwd);
        confirmPasswordChangePwd=findViewById(R.id.confirmPasswordChangePwd);

        usernameEditUsername=findViewById(R.id.usernameChangeUsername);
        usernameEditNewUsername=findViewById(R.id.newUsernameChangeUsername);
        usernameEditPassword=findViewById(R.id.passwordChangeUsername);
        enviarCambioBtn=(Button)findViewById(R.id.enviarCambio);

        Intent intent=getIntent();

        if(intent!=null && intent.hasExtra("ACTION")){
            String action=intent.getStringExtra("ACTION");

            if("changePassword".equals(action)){

                textView2.setText("Change password");
                setupChangePasswordUI();

            }else {

                textView2.setText("Change username");
                setupChangeUsernameUI();

            }
        }
        enviarCambioBtn.setOnClickListener(v -> {

            if("changePassword".equals(intent.getStringExtra("ACTION"))){

                enviarCambioPwd();

            }else{

                enviarCambioUsername();

            }
        });
    }
    public void enviarCambioPwd(){

        String username=passwordEditUsername.getText().toString();
        String currentPassword=passwordEditCurrentPassword.getText().toString();
        String newPassword=passwordEditNewPassword.getText().toString();
        String confirmNewPassword=confirmPasswordChangePwd.getText().toString();

        if(newPassword.equals(confirmNewPassword)){

            CredencialesChangePassword c = new CredencialesChangePassword(username,currentPassword ,newPassword);

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8080/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();        ChangeService change=retrofit.create(ChangeService.class);

            ChangeService change1=retrofit.create(ChangeService.class);

            Call<CredencialesRespuesta>call=change1.CreateCredencialesChangePwd(c);

            call.enqueue(new Callback<CredencialesRespuesta>() {
                @Override
                public void onResponse(Call<CredencialesRespuesta> call, Response<CredencialesRespuesta> response) {
                    if(response.isSuccessful()){
                        Context context=Change.this;
                        Toast.makeText(Change.this, "Submitted Successfully", Toast.LENGTH_SHORT).show();

                        Intent intent=new Intent(context,Profile.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(Change.this,"Error, response is not as expected", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<CredencialesRespuesta> call, Throwable t) {
                    Toast.makeText(Change.this, "Error no response", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
           clearPasswordFields();
            Toast.makeText(Change.this, "Las contraseñas no coinciden",Toast.LENGTH_SHORT).show();
        }

    }
    public void enviarCambioUsername(){

        String username=usernameEditUsername.getText().toString();
        String newUsername=usernameEditNewUsername.getText().toString();
        String password=usernameEditPassword.getText().toString();

        CredencialesChangeUsername ca = new CredencialesChangeUsername(username, newUsername,password);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ChangeService change=retrofit.create(ChangeService.class);

        Call<CredencialesRespuesta>call=change.CreateCredencialesChangeUsername(ca);

        call.enqueue(new Callback<CredencialesRespuesta>() {
            @Override
            public void onResponse(Call<CredencialesRespuesta> call, Response<CredencialesRespuesta> response) {
                Context context=Change.this;
                Toast.makeText(Change.this, "Submitted Successfully", Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(context,Profile.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<CredencialesRespuesta> call, Throwable t) {
                Toast.makeText(Change.this,"Error, response is not as expected", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setupChangeUsernameUI(){
        usernameEditUsername.setVisibility(View.VISIBLE);
        usernameEditNewUsername.setVisibility(View.VISIBLE);
        usernameEditPassword.setVisibility(View.VISIBLE);
    }
    private void setupChangePasswordUI(){
        passwordEditUsername.setVisibility(View.VISIBLE);
        passwordEditCurrentPassword.setVisibility(View.VISIBLE);
        passwordEditNewPassword.setVisibility(View.VISIBLE);
        confirmPasswordChangePwd.setVisibility(View.VISIBLE);
    }
    private void clearPasswordFields(){
        passwordEditUsername.setText("");
        passwordEditCurrentPassword.setText("");
        passwordEditNewPassword.setText("");
        confirmPasswordChangePwd.setText("");
    }
    public void returnToProfile(View view){
        Intent intent=new Intent(this,Profile.class);
        startActivity(intent);
        finish();
    }
}