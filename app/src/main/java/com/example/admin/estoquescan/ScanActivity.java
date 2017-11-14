package com.example.admin.estoquescan;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admin.estoquescan.Classes.Flags;
import com.example.admin.estoquescan.Classes.Product;
import com.example.admin.estoquescan.Connection.ConnectionScan;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

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

//        if(flags.isFirstScan()) {
//            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
//            scanIntegrator.initiateScan();
//            flags.setFirstScan(false);
//        }
    }

    public void onClick(View v){
        if( v.getId() == R.id.btnScan ){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }else if(v.getId()==R.id.btnAlert){
            show();
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

    public void show(){
        Dialog builder = new Dialog(this);
        builder.setContentView(R.layout.scan_show_alert);
        TextView txt = builder.findViewById(R.id.txt_comentario);
        txt.setText(subtitulo);
        EditText edt = builder.findViewById(R.id.edit_comentario);
        builder.show();
    }

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