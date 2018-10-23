package com.example.admin.needbookapplication.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.needbookapplication.ConnectToDB;
import com.example.admin.needbookapplication.R;

public class LoginPage extends AppCompatActivity {


    EditText etUsername,etPassword;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.admin.needbookapplication.R.layout.activity_login_page);

        etUsername = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        int temp;
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectToDB db = new ConnectToDB(LoginPage.this);
                int temp;
                try{
                    db.open();
                    temp = db.checkLogin(etUsername.getText().toString(),etPassword.getText().toString());
                    if(temp == 1){
                        System.exit(1);
//                        startActivity(new Intent(LoginPage.this, com.example.admin.needbookapplication.MainActivity.class));
                    }else{
                        System.exit(1);
                        Toast.makeText(LoginPage.this, "Kindly double check your Username & Password", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(LoginPage.this,com.example.admin.needbookapplication.Login.LoginPage.class));
                    }
                    db.close();
                }catch (Exception e){

                }
            }
        });

    }
}
