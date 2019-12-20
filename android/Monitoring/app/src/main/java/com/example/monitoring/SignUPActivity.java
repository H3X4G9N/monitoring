package com.example.monitoring;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.monitoring.model.User;

public class SignUPActivity extends AppCompatActivity {

    EditText newUsernameEdit, newPasswordEdit, newEmailEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        newUsernameEdit = (EditText) findViewById(R.id.register_username);
        newEmailEdit = (EditText) findViewById(R.id.register_email);
        newPasswordEdit = (EditText) findViewById(R.id.register_password);
    }


    public void tryRegistration(View view){
        String login = newUsernameEdit.getText().toString();
        String email = newEmailEdit.getText().toString();
        String password = newPasswordEdit.getText().toString();

        if(loginIsValid(login) && emailIsValid(email) && passwordIsValid(password)){
            String response = User.register(login, email, password);

            if(response != null){
                Intent intent = new Intent(SignUPActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }


    }

    boolean loginIsValid(String logininput){
        if(logininput.length() != 0){
            return true;
        }else {
            Toast.makeText(this, "Login is invalid", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    boolean emailIsValid(String emailinput){
        if(emailinput.length() != 0){
            return true;
        }else {
            Toast.makeText(this, "Email is invalid", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    boolean passwordIsValid(String passinput){
        if(passinput.length() != 0){
            return true;
        }else {
            Toast.makeText(this, "Password is invalid", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
/*
    boolean passwordIsSame(String passwordinput, String secondpasswordinput){
        if(passwordinput == secondpasswordinput){
            return true;
        }else {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
*/
}

/*
* REGISTRATION FOR USER
* */
