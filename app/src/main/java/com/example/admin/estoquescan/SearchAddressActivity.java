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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.admin.estoquescan.Classes.Corredor;
import com.example.admin.estoquescan.Classes.Estoque;
import com.example.admin.estoquescan.Classes.NumeroPrateleira;
import com.example.admin.estoquescan.Classes.Prateleira;
import com.example.admin.estoquescan.Connection.ConnectionCadastro;
import com.example.admin.estoquescan.Connection.ConnectionSpinnerNumeroPrateleira;
import com.example.admin.estoquescan.Connection.ConnectionSpinnerSearch;
import com.example.admin.estoquescan.Connection.ConnectionSpinnerSearchCorredor;
import com.example.admin.estoquescan.Connection.ConnectionSpinnersearchPrateleira;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.zip.Inflater;

public class SearchAddressActivity extends AppCompatActivity {

    int txtTipo;
    Spinner spnNomeEstoque;
    Spinner spnCorredor;
    Spinner spnPrateleira;
    Spinner spnNumeroPrateleira;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_address);

        final RadioButton tipoLoja = (RadioButton) findViewById(R.id.tipoLoja);
        final RadioButton tipoEstoque = (RadioButton) findViewById(R.id.tipoEstoque);


        spnNomeEstoque = (Spinner) findViewById(R.id.spinnerEstoqueSearch);
        spnCorredor = (Spinner) findViewById(R.id.spinnerCorredorSearch);
        spnPrateleira = (Spinner) findViewById(R.id.spinnerPrateleiraSearch);
        spnNumeroPrateleira = (Spinner) findViewById(R.id.spinnerNumeroPrateleiraSearch);

        tipoLoja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        tipoEstoque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    updateEstoque();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        addSpinnerEstoque();
        addSpinnerCorredor();
        addSpinnerPrateleira();
        addSpinnerNumPrateleira();

        Button BTNPesquisa = (Button) findViewById(R.id.btnPesquisaSearch);
        BTNPesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tipoLoja.isChecked()){
                    txtTipo = 1;
                }else{
                    txtTipo = 2;
                }
                Toast.makeText(SearchAddressActivity.this, "Ainda nao configurado"+ txtTipo , Toast.LENGTH_SHORT).show();

            }
        });

    }


    private void updateEstoque() throws Exception{
        ConnectionSpinnerSearch con = new ConnectionSpinnerSearch();
        ArrayList<Estoque> estoques = (ArrayList<Estoque>) con.execute().get();
        ((CustomAdapterSpinner)spnNomeEstoque.getAdapter()).setEstoques(estoques);
    }

    private void updateCorredor() throws Exception{
        ConnectionSpinnerSearchCorredor conCo = new ConnectionSpinnerSearchCorredor();
        int estoque = (int) spnNomeEstoque.getItemIdAtPosition(spnNomeEstoque.getSelectedItemPosition());
        ArrayList<Corredor> corredores = (ArrayList<Corredor>) conCo.execute(estoque).get();

        ((CustomAdapterSpinnerCorredores)spnCorredor.getAdapter()).setCorredores(corredores);
    }
    private void updatePrateleira()throws Exception{
        ConnectionSpinnersearchPrateleira conPra = new ConnectionSpinnersearchPrateleira();
        try {
            int corredor = (int) spnCorredor.getItemAtPosition(spnCorredor.getSelectedItemPosition());
            ArrayList<Prateleira> prateleiras = (ArrayList<Prateleira>) conPra.execute(corredor).get();

        ((CustomAdapterSpinnerPrateleiras)spnPrateleira.getAdapter()).setPrateleiras(prateleiras);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addSpinnerEstoque(){
        CustomAdapterSpinner adapter = new CustomAdapterSpinner(SearchAddressActivity.this,null);
        spnNomeEstoque.setAdapter(adapter);
        spnNomeEstoque.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    updateCorredor();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    public void addSpinnerCorredor(){

        try {
            CustomAdapterSpinnerCorredores adapterCor = new CustomAdapterSpinnerCorredores(SearchAddressActivity.this, null);
            spnCorredor.setAdapter(adapterCor);
            spnCorredor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    try {
                        updatePrateleira();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addSpinnerPrateleira(){
        try{
            CustomAdapterSpinnerPrateleiras adpterPra = new CustomAdapterSpinnerPrateleiras(SearchAddressActivity.this,null);
            spnPrateleira.setAdapter(adpterPra);
            spnPrateleira.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {


                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void addSpinnerNumPrateleira(){

        /*ConnectionSpinnerNumeroPrateleira conNumPra = new ConnectionSpinnerNumeroPrateleira();
        ArrayList<NumeroPrateleira> numeroPrateleiras = null;

        int prateleira = (int) spnPrateleira.getItemAtPosition(spnPrateleira.getSelectedItemPosition());*/
        try{
            //numeroPrateleiras = (ArrayList<NumeroPrateleira>) conNumPra.execute(prateleira).get();


        CustomAdapterSpinnerNumPrateleiras adpterNumPra = new CustomAdapterSpinnerNumPrateleiras(SearchAddressActivity.this,null);
        spnNumeroPrateleira.setDropDownHorizontalOffset(R.layout.new_layout_spnner_01);
        spnNumeroPrateleira.setAdapter(adpterNumPra);
        spnNumeroPrateleira.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        }catch(Exception e){
            e.printStackTrace();
        }
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


}
