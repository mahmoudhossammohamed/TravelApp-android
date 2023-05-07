package com.example.finalproject;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {
    TextView txtSignIn;

    ImageView img;
    TextView txtTilte, txtSub;
    TextInputLayout inpFullName, inpEmail, inpPhone, inpUser, inpPass, inpRePass;
    Button btnSignup, btnReset;

    SharedPreferences preferences;

    private static final String KeyName = "name";
    private static final String KeyEmail = "email";
    private static final String KeyPhone = "phone";
    private static final String KeyUser = "user";
    private static final String KeyPass = "pass";
    private static final String KeyRePass = "repass";

    private static final String KeyTotalPrice = "totalprice";
    private static final String KeyNameTour = "nametour";
    private static final String KeyCountItems = "countitems";

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        img = findViewById(R.id.logoImg);
        txtTilte = findViewById(R.id.registrationtitle);
        inpFullName = findViewById(R.id.name);
        inpEmail = findViewById(R.id.email);
        inpPhone = findViewById(R.id.phone);
        inpUser = findViewById(R.id.usernameRegister);
        inpPass = findViewById(R.id.passwordRegister);
        inpRePass = findViewById(R.id.retypePassword);
        btnSignup = findViewById(R.id.btnSignup);
        txtSignIn = findViewById(R.id.btnSignIn);
        btnReset = findViewById(R.id.btnReset);

        preferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);

        inpRePass.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int KeyCode, KeyEvent keyEvent) {
                String nameValue = inpFullName.getEditText().getText().toString();
                String emailValue = inpEmail.getEditText().getText().toString();
                String phoneValue = inpPhone.getEditText().getText().toString();
                String userValue = inpUser.getEditText().getText().toString();
                String passValue = inpPass.getEditText().getText().toString();
                String repassValue = inpRePass.getEditText().getText().toString();

                if (passValue.equals(repassValue)) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(KeyName ,nameValue);
                    editor.putString(KeyEmail ,emailValue);
                    editor.putString(KeyPhone ,phoneValue);
                    editor.putString(KeyUser ,userValue);
                    editor.putString(KeyPass ,passValue);
                    editor.putString(KeyRePass ,repassValue);
                    editor.apply();

                    try {
                        if (nameValue.equals("") ||
                                emailValue.equals("") ||
                                phoneValue.equals("") ||
                                userValue.equals("") ||
                                passValue.equals("") ||
                                repassValue.equals("")) {
                            Toast.makeText(RegisterActivity.this, "Data cann't be empty", Toast.LENGTH_SHORT).show();
                        }else {
                            String name = preferences.getString(KeyName, null);
                            if (name != null){
                               // if (KeyEvent.getAction() == KeyEvent.ACTION_DOWN && KeyCode == KeyEvent.KEYCODE_ENTER){
                                    Toast.makeText(RegisterActivity.this, "Successful Registeration", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    resetDetailTour();
                                    finish();
                               // }
                            }
                        }

                    }catch (Exception e){
                        Toast.makeText(RegisterActivity.this, "UserName has been used", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(RegisterActivity.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameValue = inpFullName.getEditText().getText().toString();
                String emailValue = inpEmail.getEditText().getText().toString();
                String phoneValue = inpPhone.getEditText().getText().toString();
                String userValue = inpUser.getEditText().getText().toString();
                String passValue = inpPass.getEditText().getText().toString();
                String repassValue = inpRePass.getEditText().getText().toString();

                if (passValue.equals(repassValue)) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(KeyName ,nameValue);
                    editor.putString(KeyEmail ,emailValue);
                    editor.putString(KeyPhone ,phoneValue);
                    editor.putString(KeyUser ,userValue);
                    editor.putString(KeyPass ,passValue);
                    editor.putString(KeyRePass ,repassValue);
                    editor.apply();

                    try {
                        if (nameValue.equals("") ||
                                emailValue.equals("") ||
                                phoneValue.equals("") ||
                                userValue.equals("") ||
                                passValue.equals("") ||
                                repassValue.equals("")) {
                            Toast.makeText(RegisterActivity.this, "Data cann't be empty", Toast.LENGTH_SHORT).show();
                        }else {
                            String name = preferences.getString(KeyName, null);
                            if (name != null){
                                Toast.makeText(RegisterActivity.this, "Successful Registeration", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                                resetDetailTour();
                                finish();
                            }
                        }
                    }catch (Exception e){
                        Toast.makeText(RegisterActivity.this, "UserName has been used", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(RegisterActivity.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });

        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    public void reset(){
        inpFullName.getEditText().setText(null);
        inpEmail.getEditText().setText(null);
        inpPhone.getEditText().setText(null);
        inpUser.getEditText().setText(null);
        inpPass.getEditText().setText(null);
        inpRePass.getEditText().setText(null);
    }
    private void resetDetailTour(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KeyNameTour ,null);
        editor.putString(KeyCountItems ,null);
        editor.putString(KeyTotalPrice ,null);
        editor.apply();
    }
}

