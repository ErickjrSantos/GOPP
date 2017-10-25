package com.example.admin.estoquescan;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.admin.estoquescan.Classes.Corredor;
import com.example.admin.estoquescan.Classes.Estoque;
import com.example.admin.estoquescan.Connection.ConnectionCadastro;
import com.example.admin.estoquescan.Connection.ConnectionSpinnerSearch;
import com.example.admin.estoquescan.Connection.ConnectionSpinnerSearchCorredor;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.zip.Inflater;

public class SearchAddressActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int txtTipo = 2;
    Spinner spnNomeEstoque;
    Spinner spnCorredor;
    Spinner spnPrateleira;
    Spinner spnNumeroPrateleira;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_address);

        final CheckBox tipoLoja = (CheckBox) findViewById(R.id.lojaBox);
        final CheckBox tipoEstoque = (CheckBox) findViewById(R.id.estoqueBox);

        addSpinnerEstoque();



        Button BTNPesquisa = (Button) findViewById(R.id.btnPesquisaSearch);
        BTNPesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tipoEstoque.isChecked()){
                    txtTipo = 1;
                }else if (tipoLoja.isChecked()){
                    txtTipo = 2;
                }


            }
        });

    }


    public void addSpinnerEstoque(){
        spnNomeEstoque = (Spinner) findViewById(R.id.spinnerEstoqueSearch);
        ConnectionSpinnerSearch con = new ConnectionSpinnerSearch();
        ArrayList<Estoque> estoque = null;
             try {
                 estoque = (ArrayList<Estoque>) con.execute().get();

              } catch (Exception e) {
              e.printStackTrace();
            }

        CustomAdapterSpinner adapter = new CustomAdapterSpinner(SearchAddressActivity.this, estoque);
        spnNomeEstoque.setAdapter(adapter);

        spnNomeEstoque.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                addSpinnerCorredor();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void addSpinnerCorredor(){
        spnCorredor = (Spinner) findViewById(R.id.spinnerCorredorSearch);
        ConnectionSpinnerSearchCorredor conCo = new ConnectionSpinnerSearchCorredor();
        ArrayList<Corredor> corredores = null;

        int estoque = (int) spnNomeEstoque.getItemIdAtPosition(spnNomeEstoque.getSelectedItemPosition());
        try {
            corredores = (ArrayList<Corredor>) conCo.execute(estoque).get();

        }catch (Exception e){
            e.printStackTrace();
        }
        CustomAdapterSpinnerCorredores adapterCor = new CustomAdapterSpinnerCorredores(SearchAddressActivity.this,corredores);
        spnCorredor.setAdapter(adapterCor);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id== R.id.item_estoque){
            Intent goToCadastro = new Intent(getApplicationContext(),AddressRegisterActivity.class);
            startActivity(goToCadastro);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        adapterView.getSelectedItem();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
