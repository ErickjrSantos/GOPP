package com.example.admin.estoquescan;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
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
import android.widget.TextView;
import android.widget.Toast;

import com.Fragmento.FragmentDialog;
import com.example.admin.estoquescan.Classes.Corredor;
import com.example.admin.estoquescan.Classes.Estoque;
import com.example.admin.estoquescan.Classes.Flags;
import com.example.admin.estoquescan.Classes.NumeroPrateleira;
import com.example.admin.estoquescan.Classes.Prateleira;
import com.example.admin.estoquescan.Classes.Product;
import com.example.admin.estoquescan.Classes.Produto;
import com.example.admin.estoquescan.Connection.ConnectionBuscaProduto;
import com.example.admin.estoquescan.Connection.ConnectionBuscaProdutoMysql;
import com.example.admin.estoquescan.Connection.ConnectionCadastro;
import com.example.admin.estoquescan.Connection.ConnectionScan;
import com.example.admin.estoquescan.Connection.ConnectionSpinnerNumeroPrateleira;
import com.example.admin.estoquescan.Connection.ConnectionSpinnerSearch;
import com.example.admin.estoquescan.Connection.ConnectionSpinnerSearchCorredor;
import com.example.admin.estoquescan.Connection.ConnectionSpinnerSearchLoja;
import com.example.admin.estoquescan.Connection.ConnectionSpinnersearchPrateleira;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

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

    TextView txtPreco;
    TextView txtEstoque;
    TextView txtTitulo;

    private Flags flags = Flags.getInstance();
    int unidade = 1;

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

        txtPreco  = (TextView) findViewById(R.id.precoProduto);
        txtEstoque = (TextView) findViewById(R.id.codigoProduto);
        txtTitulo = (TextView) findViewById(R.id.description);

        tipoLoja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    updateLoja();
                } catch (Exception e) {
                    e.printStackTrace();
                }
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

        Button BTNPesquisa = (Button) findViewById(R.id.btn_adciona_produto);
        BTNPesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tipoLoja.isChecked()){
                    txtTipo = 1;
                }else{
                    txtTipo = 2;
                }
                if(flags.isFirstScan()) {
                    IntentIntegrator scanIntegrator = new IntentIntegrator(SearchAddressActivity.this);
                    scanIntegrator.initiateScan();
                    flags.setFirstScan(false);
                }

            }
        });

        final String[] string = {"teste","teste2","teste3"};

        Button BTNRetira = (Button) findViewById(R.id.btn_retira_produto);
        BTNRetira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SearchAddressActivity.this);
                builder.setMessage("OLHA EU AQUI ;)")
                        .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        }).show();
            }
        });


    }
    private  void updateLoja()throws Exception{
        ConnectionSpinnerSearchLoja conLoja = new ConnectionSpinnerSearchLoja();
        ArrayList<Estoque> estoques = (ArrayList<Estoque>) conLoja.execute().get();
        ((CustomAdapterSpinner)spnNomeEstoque.getAdapter()).setEstoques(estoques);
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
            int corredor = (int) spnCorredor.getItemIdAtPosition(spnCorredor.getSelectedItemPosition());
            ArrayList<Prateleira> prateleiras = (ArrayList<Prateleira>) conPra.execute(corredor).get();
            ((CustomAdapterSpinnerPrateleiras)spnPrateleira.getAdapter()).setPrateleiras(prateleiras);
    }

    public void updateNumPrateleira()throws Exception{
        ConnectionSpinnerNumeroPrateleira conNum = new ConnectionSpinnerNumeroPrateleira();
        int prateleira = (int) spnPrateleira.getItemIdAtPosition(spnPrateleira.getSelectedItemPosition());
        ArrayList<NumeroPrateleira> numeroPrateleiras = (ArrayList<NumeroPrateleira>) conNum.execute(prateleira).get();
        ((CustomAdapterSpinnerNumPrateleiras)spnNumeroPrateleira.getAdapter()).setNumeroPrateleiras(numeroPrateleiras);
    }

    public void addSpinnerEstoque(){
        CustomAdapterSpinner adapter = new CustomAdapterSpinner(SearchAddressActivity.this,null);
        spnNomeEstoque.setAdapter(adapter);
        //spnNomeEstoque.setBackgroundResource(R.drawable.ic_spinner);
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
                try {
                    updateCorredor();
                } catch (Exception e) {
                    e.printStackTrace();
                }

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
                    try {
                        updatePrateleira();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

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
                    try {
                        updateNumPrateleira();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    try {
                        updateNumPrateleira();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    Product prod = new Product();
    public void addSpinnerNumPrateleira(){

        try{
        CustomAdapterSpinnerNumPrateleiras adpterNumPra = new CustomAdapterSpinnerNumPrateleiras(SearchAddressActivity.this,null);
        spnNumeroPrateleira.setAdapter(adpterNumPra);
        spnNumeroPrateleira.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    ConnectionBuscaProdutoMysql conMy = new ConnectionBuscaProdutoMysql();
                    Produto produto = new Produto();
                     produto = (Produto) conMy.execute(l).get();

                       String cod = produto.getCodBarra();
                        ConnectionBuscaProduto con = new ConnectionBuscaProduto();
                        prod = (Product) con.execute(cod).get();
                        String plu = prod.getInternalCode();
                        String titulo = prod.getDescription();
                        txtTitulo.setText(titulo + " PLU:  " + plu);


                } catch (Exception e) {
                    e.printStackTrace();
                    txtTitulo.setText("nao ha produtos");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                    txtTitulo.setText("");
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
            int endereco = (int) spnNumeroPrateleira.getItemIdAtPosition(spnNumeroPrateleira.getSelectedItemPosition());
            goToCadastro.putExtra("endereco",endereco);
            startActivity(goToCadastro);
        }
        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

         String titulo;
         String subtitulo;

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
                    subtitulo = "\nPLU: " + p.getInternalCode();
                    txtTitulo.setText("Descrição: "+titulo + subtitulo);
                    if (p.isInSale()) {
                        txtPreco.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.precoPromocional));
                    } else {
                        txtPreco.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.precoNormal));
                    }
                }else{
                    titulo = "Produto não encontrado!";
                    subtitulo = "Tente pesquisar novamente.";
                    txtTitulo.setText(titulo);

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
