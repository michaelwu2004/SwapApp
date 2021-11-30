package com.example.swapapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.swapapp.db.DBHelper;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button register, login;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);


        register = (Button) findViewById(R.id.register);
        login = (Button) findViewById(R.id.login);

        db = new DBHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                String user_id = db.checkusernamepassword(user, pass);

                if(user.equals("") || pass.equals("")){
                    Toast.makeText(LoginActivity.this, "Fill in the fields", Toast.LENGTH_SHORT).show();
                } else if(user_id != null) {
                    MainActivity.user_id = user_id;
                    Toast.makeText(LoginActivity.this, MainActivity.user_id, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);

                }
            }
        });
    }
}