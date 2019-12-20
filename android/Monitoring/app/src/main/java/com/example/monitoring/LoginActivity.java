package com.example.monitoring;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.monitoring.model.User;

import java.io.Serializable;

public class LoginActivity extends AppCompatActivity implements Serializable {

    EditText editUsername, editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        editUsername = (EditText) findViewById(R.id.login_username);
        editPassword = (EditText) findViewById(R.id.login_password);
    }

    public void tryLogin(View view){
        String login, password;

        login = editUsername.getText().toString();
        password = editPassword.getText().toString();

        User user = User.authorize(login, password);


        if(user != null) {
            String response = User.authenticate(user);
            if(response != null){
                Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                intent.putExtra("User", user);
                startActivity(intent);
            }
        }

    }

    public void goToSignUp(View view){
        Intent intent = new Intent(LoginActivity.this, SignUPActivity.class);
        startActivity(intent);
    }

}



