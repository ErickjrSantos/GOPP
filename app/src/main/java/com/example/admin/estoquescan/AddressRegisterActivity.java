package com.example.admin.estoquescan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.estoquescan.Connection.ConnectionCadastro;

import org.json.JSONObject;

public class AddressRegisterActivity extends AppCompatActivity {
    EditText txtCorredor;
    EditText txtPrateleira;
    EditText txtNomeEstoque;
    EditText txtAndar;
    int tipoDoEndereco = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_register);

        txtAndar = (EditText) findViewById(R.id.txtAndarPrateleira);
        txtNomeEstoque = (EditText) findViewById(R.id.txtnomeEstoque);
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
                    String nomeEstoque = txtNomeEstoque.getText().toString();
                    String andar = txtAndar.getText().toString();
                    String nomeCorredor = txtCorredor.getText().toString();
                    String nomePrateleira = txtPrateleira.getText().toString();
                    if(checkLoja.isChecked()) {
                        tipoDoEndereco = 1;
                    }
                    if(checkEstoque.isChecked()) {
                        tipoDoEndereco = 2;
                    }
                    JSONObject json = new JSONObject();
                    json.put("nomeEstoque", nomeEstoque);
                    json.put("andar", andar);
                    json.put("nomeCorredor", nomeCorredor);
                    json.put("nomePrateleira", nomePrateleira);
                    json.put("tipoEndereco", tipoDoEndereco);

                    ConnectionCadastro con = new ConnectionCadastro();
                     con.execute(json);
                }catch (Exception e){
                    e.printStackTrace();
                }
                Toast.makeText(AddressRegisterActivity.this, "Salvo com Sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }


}
