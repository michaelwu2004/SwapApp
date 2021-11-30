package com.example.swapapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.swapapp.db.DBHelper;

public class RegisterActivity extends AppCompatActivity {

    EditText firstname, lastname, username, password, repassword;
    Button signup, signin;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstname = (EditText) findViewById(R.id.firstname);
        lastname = (EditText) findViewById(R.id.lastname);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);

        signup = (Button) findViewById(R.id.signup);

        db = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fName = firstname.getText().toString();
                String lName = lastname.getText().toString();
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("") || pass.equals("") || repass.equals("")){
                    Toast.makeText(RegisterActivity.this, "Fill in the fields", Toast.LENGTH_SHORT).show();
                } else {
                    if(pass.equals(repass)){
                        if(!db.checkusername(user)){
                            if(db.insertData(user, pass, fName, lName)){
                                Toast.makeText(RegisterActivity.this, "Registered", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "User is already registered", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                }



            }
        });
    }
}