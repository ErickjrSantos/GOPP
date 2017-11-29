package com.example.admin.estoquescan;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.estoquescan.Classes.Flags;
import com.example.admin.estoquescan.Classes.Product;
import com.example.admin.estoquescan.Classes.User;
import com.example.admin.estoquescan.Connection.ConnectionNovoComentario;
import com.example.admin.estoquescan.Connection.ConnectionScan;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ScanActivity extends AppCompatActivity implements OnClickListener {


    TextView txtPreco, txtEstoque;
    private Flags flags = Flags.getInstance();
    int unidade = 1;
    private String titulo, subtitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        txtPreco = (TextView) findViewById(R.id.textPreco);
        txtEstoque = (TextView) findViewById(R.id.textEstoque);

        FloatingActionButton btn = (FloatingActionButton) findViewById(R.id.btnAlert);
        btn.setOnClickListener(this);
        FloatingActionButton btnScan = (FloatingActionButton) findViewById(R.id.btnScan);
        btnScan.setOnClickListener(this);
        FloatingActionButton btnSearch = (FloatingActionButton) findViewById(R.id.btnPesquisa);
        btnSearch.setOnClickListener(this);

    }

    public void onClick(View v){
        if( v.getId() == R.id.btnScan ){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }else if(v.getId()==R.id.btnAlert){
            show(v);
        }else if(v.getId()==R.id.btnPesquisa){
       // search();
        }
    }


    @Override
    public void onBackPressed() {
        finish();
        flags.setFirstScan(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(titulo);
            getSupportActionBar().setSubtitle(subtitulo);
        }
    }



    public void show(final View ve){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("enviar", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(titulo == null) {
                    titulo = "Comentario Geral";
                }
                    String title = titulo;
                    String coment = String.valueOf(input.getText());
                    int user_cod = User.getSavedUser().getId();
                    int idProd = internalcode;
                    int ativo = 1;

                    int loja = 1;
                    String nome_usuario = User.getSavedUser().getUsername();

                    ConnectionNovoComentario conn = new ConnectionNovoComentario();
                    JSONObject json = new JSONObject();
                    try {
                        json.put("comentario", coment);
                        json.put("codigo_usuario", user_cod);
                        json.put("id_produto", idProd);
                        json.put("ativo", ativo);
                        json.put("Loja", loja);
                        json.put("nome_usuario", nome_usuario);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    conn.execute(json);

                    Snackbar.make(ve, "Aguarde...SALVANDO PROCESSO!! ", 3000)
                            .setAction("Action", null).show();

            }
        });

        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });


        if(subtitulo == null){
            subtitulo = "sem produto!";
            builder.setMessage("Comentario: "+subtitulo);
        }else{
            builder.setMessage("Comentario "+subtitulo);
        }
        builder.show();
    }

    int internalcode;


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        assert scanningResult != null;
        if (scanningResult.getContents() != null) {
            String scanBar = scanningResult.getContents();
            ConnectionScan con = new ConnectionScan();
            try {
                Product p = con.execute(scanBar, unidade).get();
                if(p != null) {
                    String preco = "R$ " + p.getPrice().replace('.', ',');
                    txtPreco.setText(preco);
                    txtEstoque.setText(String.valueOf(p.getStock()));
                    titulo = p.getDescription();
                    subtitulo = "PLU " + p.getInternalCode();
                    internalcode = Integer.parseInt(p.getInternalCode());
                    if (p.isInSale()) {
                        txtPreco.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.precoPromocional));
                    } else {
                        txtPreco.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.precoNormal));
                    }
                }else{
                    titulo = "Produto não encontrado!";
                    subtitulo = "Tente pesquisar novamente.";
                }
            } catch (Exception ex){
                ex.printStackTrace();
                finish();
                flags.setFirstScan(true);
            }
        } else {
            finish();
            flags.setFirstScan(true);
        }
    }
}