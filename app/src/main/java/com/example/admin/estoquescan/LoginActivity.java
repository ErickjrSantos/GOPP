package com.example.admin.estoquescan;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.example.admin.estoquescan.Classes.User;
import com.example.admin.estoquescan.Connection.ConnectionLogin;
import com.example.admin.estoquescan.TheadDialog.ProgressDialogLoad;

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
        nome = settings.getString("nome", "");


        if(codigo != 0 && !nome.equals("")){
            Intent goHome = new Intent(LoginActivity.this,MainActivity.class);

            ConnectionLogin CL = new ConnectionLogin(this);
            User user = null;
            try {
                user = CL.execute(codigo).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            User.setSavedUser(user);

            startActivity(goHome);
            finish();
        }

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle("GOPP");
            getSupportActionBar().setSubtitle("Login");
        }

        mUserName  = (EditText) findViewById(R.id.txtUsername);
        mUserPassword  = (EditText) findViewById(R.id.txtPassword);
        final CheckBox checkRemember = (CheckBox) findViewById(R.id.checkRemember);

        final ProgressDialogLoad load = new ProgressDialogLoad(LoginActivity.this);

        Button loginButton = (Button) findViewById(R.id.btnLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load.progress_dialog_creation();

                ConnectionLogin CL = new ConnectionLogin(LoginActivity.this);
                String password = mUserPassword.getText().toString();
                String nome = mUserName.getText().toString();

//                retrofit2.Call<User> call = new RetrofitInicializador().getLoginService().existe(nome,password);
//                call.enqueue(new Callback<User>() {
//                    @Override
//                    public void onResponse(retrofit2.Call<User> call, Response<User> response) {
//                        User user = User.setSavedUser(response.body());
//
//
//                    }
//
//                    @Override
//                    public void onFailure(retrofit2.Call<User> call, Throwable t) {
//
//                    }
//                });
                try {


                    User user = CL.execute(nome,password).get();
                    User.setSavedUser(user);

                    if(user!= null){
                        if(checkRemember.isChecked()) {
                            SharedPreferences settings = getSharedPreferences(GOPP_PREFERENCES, 0);
                            SharedPreferences.Editor editor = settings.edit();
                            editor.putString("nome", user.getUsername());
                            editor.putInt("codigo", user.getId());
                            editor.apply();
                        }

                        Intent goHome = new Intent(LoginActivity.this,MainActivity.class);
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
