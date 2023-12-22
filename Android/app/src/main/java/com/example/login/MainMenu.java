package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import com.example.login.R;

public class MainMenu extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        this.window = getWindow();
    }
    public void onProfileImageClick(View view){
        Intent intent =new Intent(this, Profile.class);
        startActivity(intent);
        finish();
    }
    public void onClick(View view){
        Intent intent =new Intent(this, MensajesSistemas.class);
        startActivity(intent);
    }
    public void onStartGameClick(View view){

    }
    public void onShopClick(View view){
        Intent intent =new Intent(this, Tienda.class);
        startActivity(intent);
        finish();
    }
    public void onSettingsClick(View view){

    }
}