package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    TextView btnSignup;
    ImageView img;
    TextView txtTilte, txtSub;
    TextInputLayout txtUser, txtPass;
    LinearLayout txtSignup;
    Button btnLogin, btnForgotPass;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW);
        setContentView(R.layout.activity_login);

       // img = findViewById(R.id.logoImg);
        txtTilte = findViewById(R.id.loginTitle);
        txtSub = findViewById(R.id.loginSubtitle);
        txtUser = findViewById(R.id.usernameLogin);
        txtPass = findViewById(R.id.passwordLogin);
        btnLogin = findViewById(R.id.btnLogin);
        btnForgotPass = findViewById(R.id.btnForgotpass);
        btnSignup = findViewById(R.id.btnSignup);
        txtSignup = findViewById(R.id.llSignup);

        preferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        txtPass.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
              //  if (KeyEvent.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    String userValue = txtUser.getEditText().getText().toString();
                    String emailValue = txtUser.getEditText().getText().toString();
                    String passValue = txtPass.getEditText().getText().toString();

                    String loginUser = preferences.getString("user", "");
                    String emailUser = preferences.getString("email", "");
                    String loginPass = preferences.getString("pass", "");

                    if (userValue.equals(loginUser) && passValue.equals(loginPass) || emailValue.equals(emailUser) && passValue.equals(loginPass)) {
                        Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(LoginActivity.this, "Login", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "Username or Password doesn't match", Toast.LENGTH_SHORT).show();
                    }
              //  }
                return false;
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userValue = txtUser.getEditText().getText().toString();
                String emailValue = txtUser.getEditText().getText().toString();
                String passValue = txtPass.getEditText().getText().toString();

                String loginUser = preferences.getString("user", "");
                String emailUser = preferences.getString("email", "");
                String loginPass = preferences.getString("pass", "");

                if (userValue.equals(loginUser) && passValue.equals(loginPass) || emailValue.equals(emailUser) && passValue.equals(loginPass)) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(LoginActivity.this, "Login", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Username or Password doesn't match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}