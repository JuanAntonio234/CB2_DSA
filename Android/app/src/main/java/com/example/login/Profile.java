package com.example.login;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import com.example.login.R;

public class Profile extends AppCompatActivity {

    private ActivityResultLauncher<Intent>galleryLauncher;
    private ImageButton imageProfileButton;
    private SharedPreferences sharedPreferences;
    private Window window;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        imageProfileButton=findViewById(R.id.imageButtonProfile);
        sharedPreferences=getSharedPreferences("MyPrefs",MODE_PRIVATE);

        String savedImageUri=sharedPreferences.getString("profileimuri",null);
        if(savedImageUri!=null){
            imageProfileButton.setImageURI(Uri.parse(savedImageUri));
        }
        galleryLauncher=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode()==RESULT_OK&&result.getData()!=null){
                        Uri selecteImUri=result.getData().getData();
                        updateProfileImage(selecteImUri);
                    }
                }
        );
    }
    public void onChangeImageClick(View view){
        openGallery();
    }
    private void updateProfileImage(Uri newImageUri){
        imageProfileButton.setImageURI(newImageUri);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("profileimuri",newImageUri.toString());
        editor.apply();
    }
    public void onChangeUser(View view){
        String a ="changeUsername";
        Intent intent=new Intent(this,Change.class);
        intent.putExtra("ACTION",a);
        startActivity(intent);
    }
    private void openGallery(){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryLauncher.launch(galleryIntent);
    }
    public void onChangePassword(View view){
        String a = "changePassword";
        Intent intent=new Intent(this,Change.class);
        intent.putExtra("ACTION",a);
        startActivity(intent);
    }
    public void onSignOff(View view){
        SessionManager.logOutUser(this);
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void returnToMainMenu(View view){
        Intent intent=new Intent(this, MainMenu.class);
        startActivity(intent);
        finish();
    }

}