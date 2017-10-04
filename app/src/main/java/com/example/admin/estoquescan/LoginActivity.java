package com.example.admin.estoquescan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.estoquescan.Classes.User;
import com.example.admin.estoquescan.Connection.ConnectionLogin;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {
    private EditText mUserName;
    private EditText mUserPassword;

    private static final String GOPP_PREFERENCES = "GOPPPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        int codigo;
        String nome;

        SharedPreferences settings = getSharedPreferences(GOPP_PREFERENCES, 0);
        codigo = settings.getInt("codigo", 0);
        nome = settings.getString("nome","" );


        if(codigo != 0 && !nome.equals("")){
            Intent goHome = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(goHome);
            finish();
        }



        getSupportActionBar().setTitle("GOPP");
        getSupportActionBar().setSubtitle("Login");

        mUserName  = (EditText) findViewById(R.id.txtUsername);
        mUserPassword  = (EditText) findViewById(R.id.txtPassword);

        Button loginButton = (Button) findViewById(R.id.btnLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectionLogin CL = new ConnectionLogin();
                String password = mUserPassword.getText().toString();
                String nome = mUserName.getText().toString();

                try {
                    User user = (User) CL.execute(nome,password).get();

                    if(user!= null){
                        SharedPreferences settings = getSharedPreferences(GOPP_PREFERENCES, 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("nome", user.getUser());
                        editor.putInt("codigo", user.getCodigo());
                        editor.apply();

                        Intent goHome = new Intent(LoginActivity.this,MainActivity.class);
                        String userName = user.getUser();
                        goHome.putExtra("nome", userName);
                        startActivity(goHome);
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this, "Senha ou Usuario Incorretos", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


        });

    }


}
