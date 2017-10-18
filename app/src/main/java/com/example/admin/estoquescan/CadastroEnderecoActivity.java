package com.example.admin.estoquescan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CadastroEnderecoActivity extends AppCompatActivity {
    EditText txtCorredor;
    EditText txtPrateleira;
    int tipoDoEndereco = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_endereco);
        txtCorredor = (EditText) findViewById(R.id.txtnomeCorredor);
        txtPrateleira = (EditText) findViewById(R.id.txtnomePrateleira);
        final CheckBox checkLoja = (CheckBox) findViewById(R.id.lojaBox);
        final CheckBox checkEstoque = (CheckBox) findViewById(R.id.estoqueBox);
        checkEstoque.setChecked(true);
        Button btbCadastro = (Button) findViewById(R.id.botaoEnvia);
        btbCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    String nomeCorredor = txtCorredor.getText().toString();
                    String nomePrateleira = txtPrateleira.getText().toString();
                    if(checkLoja.isChecked()) {
                        tipoDoEndereco = 1;
                    }
                    if(checkEstoque.isChecked()) {
                        tipoDoEndereco = 2;
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
                Toast.makeText(CadastroEnderecoActivity.this, "Salvo com Sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
