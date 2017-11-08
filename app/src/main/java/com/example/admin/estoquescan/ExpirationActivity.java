package com.example.admin.estoquescan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ExpirationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expiration);

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
