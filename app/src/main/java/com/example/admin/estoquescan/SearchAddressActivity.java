//package com.example.admin.estoquescan;
//
//import android.app.AlertDialog;
//import android.app.Dialog;
//import android.content.Intent;
//import android.os.Handler;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.NumberPicker;
//import android.widget.ProgressBar;
//import android.widget.RadioButton;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.admin.estoquescan.Classes.Corredor;
//import com.example.admin.estoquescan.Classes.Estoque;
//import com.example.admin.estoquescan.Classes.Flags;
//import com.example.admin.estoquescan.Classes.NumeroPrateleira;
//import com.example.admin.estoquescan.Classes.Prateleira;
//import com.example.admin.estoquescan.Classes.Product;
//import com.example.admin.estoquescan.Classes.Produto;
//import com.example.admin.estoquescan.Connection.ConnectionBuscaProduto;
//import com.example.admin.estoquescan.Connection.ConnectionBuscaProdutoMysql;
//import com.example.admin.estoquescan.Connection.ConnectionScan;
//import com.example.admin.estoquescan.Connection.ConnectionSpinnerNumeroPrateleira;
//import com.example.admin.estoquescan.Connection.ConnectionSpinnerSearch;
//import com.example.admin.estoquescan.Connection.ConnectionSpinnerSearchCorredor;
//import com.example.admin.estoquescan.Connection.ConnectionSpinnerSearchLoja;
//import com.example.admin.estoquescan.Connection.ConnectionSpinnersearchPrateleira;
//import com.google.zxing.integration.android.IntentIntegrator;
//import com.google.zxing.integration.android.IntentResult;
//import java.util.ArrayList;
//
//public class SearchAddressActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener{
//
//    int txtTipo;
//    Spinner spnNomeEstoque;
//    Spinner spnCorredor;
//    Spinner spnPrateleira;
//    Spinner spnNumeroPrateleira;
//
//    String titulo;
//    String subtitulo;
//
//    TextView txtPreco;
//    TextView txtEstoque;
//    TextView txtTitulo;
//
//    int value = 0;
//
//    private Flags flags = Flags.getInstance();
//    int unidade = 1;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search_address);
//
//        final RadioButton tipoLoja = (RadioButton) findViewById(R.id.tipoLoja);
//        final RadioButton tipoEstoque = (RadioButton) findViewById(R.id.tipoEstoque);
//
//        spnNomeEstoque = (Spinner) findViewById(R.id.spinnerEstoqueSearch);
//        spnCorredor = (Spinner) findViewById(R.id.spinnerCorredorSearch);
//        spnPrateleira = (Spinner) findViewById(R.id.spinnerPrateleiraSearch);
//        spnNumeroPrateleira = (Spinner) findViewById(R.id.spinnerNumeroPrateleiraSearch);
//
//        txtPreco  = (TextView) findViewById(R.id.precoProduto);
//        txtEstoque = (TextView) findViewById(R.id.codigoProduto);
//        txtTitulo = (TextView) findViewById(R.id.description);
//
//        tipoLoja.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    updateLoja();
//                    Loading();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        tipoEstoque.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    updateEstoque();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        addSpinnerEstoque();
//        addSpinnerCorredor();
//        addSpinnerPrateleira();
//        addSpinnerNumPrateleira();
//
//        Button BTNPesquisa = (Button) findViewById(R.id.btn_adciona_produto);
//        BTNPesquisa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(tipoLoja.isChecked()){
//                    txtTipo = 1;
//                }else{
//                    txtTipo = 2;
//                }
//
//                showAdd(true);
//
//                if(flags.isFirstScan()) {
//                    IntentIntegrator scanIntegrator = new IntentIntegrator(SearchAddressActivity.this);
//                    scanIntegrator.initiateScan();
//                    flags.setFirstScan(false);
//                }
//
//            }
//        });
//
//        Button BTNRetira = (Button) findViewById(R.id.btn_retira_produto);
//        BTNRetira.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Toast.makeText(SearchAddressActivity.this, "TesteRetira", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//    }
//    int progressStatus = 0;
//    public void Loading(){
//        final ProgressBar load;
//
//        final TextView textView;
//        final Handler handler = new Handler();
//        setContentView(R.layout.load_progress);
//
//        load = (ProgressBar)findViewById(R.id.progressBar_cyclic);
//        textView = (TextView) findViewById(R.id.txtProgress);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try{
//                while (progressStatus <100){
//                    progressStatus += 1;
//
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                        load.setProgress(progressStatus);
//
//                        }
//
//
//                    });
//                    Thread.sleep(100);
//                }
//
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//
//    }
//
//
//    public void showAdd(boolean haveProd){
//        final Dialog dialog = new Dialog(this);
//        dialog.setContentView(R.layout.seach_address_showadd);
//
//        Button cancel = dialog.findViewById(R.id.btnCanceladd);
//        Button adciona = dialog.findViewById(R.id.btnRetiraadd);
//        TextView text = dialog.findViewById(R.id.tv);
//
//        text.setText("Adcione mais "+prod.getDescription());
//        cancel.setText(getResources().getText(R.string.cancelar));
//        adciona.setText("ADCIONA");
//        final EditText ed = dialog.findViewById(R.id.editShowadd);
//
//        adciona.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ed.getText();
//                dialog.dismiss();
//            }
//        });
//
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//        dialog.show();
//
//    }
//
//
//
//
//    public  Product show(final Produto produto){
//        final Dialog builder = new Dialog(SearchAddressActivity.this);
//        builder.setContentView(R.layout.alert_dialog_retira);
//
//        Button b1 =builder.findViewById(R.id.btnCancel);
//        Button b2 =builder.findViewById(R.id.btnRetira);
//        TextView t1 = builder.findViewById(R.id.tv);
//        t1.setText("Quantos/as "+prod.getDescription()+" deseja retirar?\n"+" Estoque: "+prod.getStock()+"\n na prateleira: "+produto.getQuantLocal());
//        t1.setTextSize(20);
//        t1.setTextColor(getResources().getColor(R.color.precoNormal));
//        b1.setText(getResources().getText(R.string.cancelar));
//        b1.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
//        b2.setText("RETIRAR");
//        b2.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
//
//        final NumberPicker np = builder.findViewById(R.id.editText4);
//        if(produto.getQuantLocal()<= 0){
//            np.setMinValue(0);
//            np.setMaxValue(0);
//        }else{
//            np.setMinValue(1);
//            int max = produto.getQuantLocal();
//            np.setMaxValue(max);
//        }
//
//        np.setOnValueChangedListener(this);
//
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(SearchAddressActivity.this, "botao 01", Toast.LENGTH_SHORT).show();
//                builder.dismiss();
//            }
//        });
//
//        b2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int stock = produto.getQuantLocal();
//
//                Toast.makeText(SearchAddressActivity.this, " "+(stock - value), Toast.LENGTH_SHORT).show();
//                builder.dismiss();
//            }
//        });
//        builder.show();
//        return null;
//    }
//
//    private  void updateLoja()throws Exception{
//        ConnectionSpinnerSearchLoja conLoja = new ConnectionSpinnerSearchLoja();
//        ArrayList<Estoque> estoques = (ArrayList<Estoque>) conLoja.execute().get();
//        ((CustomAdapterSpinner)spnNomeEstoque.getAdapter()).setEstoques(estoques);
//    }
//
//    private void updateEstoque() throws Exception{
//        ConnectionSpinnerSearch con = new ConnectionSpinnerSearch();
//        ArrayList<Estoque> estoques = (ArrayList<Estoque>) con.execute().get();
//        ((CustomAdapterSpinner)spnNomeEstoque.getAdapter()).setEstoques(estoques);
//    }
//
//    private void updateCorredor() throws Exception{
//        ConnectionSpinnerSearchCorredor conCo = new ConnectionSpinnerSearchCorredor();
//        int estoque = (int) spnNomeEstoque.getItemIdAtPosition(spnNomeEstoque.getSelectedItemPosition());
//        ArrayList<Corredor> corredores = (ArrayList<Corredor>) conCo.execute(estoque).get();
//
//        ((CustomAdapterSpinnerCorredores)spnCorredor.getAdapter()).setCorredores(corredores);
//    }
//    private void updatePrateleira()throws Exception{
//        ConnectionSpinnersearchPrateleira conPra = new ConnectionSpinnersearchPrateleira();
//            int corredor = (int) spnCorredor.getItemIdAtPosition(spnCorredor.getSelectedItemPosition());
//            ArrayList<Prateleira> prateleiras = (ArrayList<Prateleira>) conPra.execute(corredor).get();
//            ((CustomAdapterSpinnerPrateleiras)spnPrateleira.getAdapter()).setPrateleiras(prateleiras);
//    }
//
//    public void updateNumPrateleira()throws Exception{
//        ConnectionSpinnerNumeroPrateleira conNum = new ConnectionSpinnerNumeroPrateleira();
//        int prateleira = (int) spnPrateleira.getItemIdAtPosition(spnPrateleira.getSelectedItemPosition());
//        ArrayList<NumeroPrateleira> numeroPrateleiras = (ArrayList<NumeroPrateleira>) conNum.execute(prateleira).get();
//        ((CustomAdapterSpinnerNumPrateleiras)spnNumeroPrateleira.getAdapter()).setNumeroPrateleiras(numeroPrateleiras);
//    }
//
//    public void addSpinnerEstoque(){
//        CustomAdapterSpinner adapter = new CustomAdapterSpinner(SearchAddressActivity.this,null);
//        spnNomeEstoque.setAdapter(adapter);
//        spnNomeEstoque.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                try {
//                    if(adapterView.getItemIdAtPosition(i)==0){
//                        onNothingSelected(adapterView);
//                    }else {
//                        updateCorredor();
//                    }
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//                try {
//                    updateCorredor();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    public void addSpinnerCorredor(){
//        try {
//            CustomAdapterSpinnerCorredores adapterCor = new CustomAdapterSpinnerCorredores(SearchAddressActivity.this, null);
//            spnCorredor.setAdapter(adapterCor);
//            spnCorredor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                    try {
//                        if(adapterView.getItemIdAtPosition(i)==0){
//                            onNothingSelected(adapterView);
//                        }else {
//                            updatePrateleira();
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//                @Override
//                public void onNothingSelected(AdapterView<?> adapterView) {
//                    try {
//                        updatePrateleira();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            });
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    public void addSpinnerPrateleira(){
//        try{
//            CustomAdapterSpinnerPrateleiras adpterPra = new CustomAdapterSpinnerPrateleiras(SearchAddressActivity.this,null);
//            spnPrateleira.setAdapter(adpterPra);
//            spnPrateleira.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                    try {
//                        if(adapterView.getItemIdAtPosition(i)==0) {
//                            onNothingSelected(adapterView);
//                        }else{
//                            updateNumPrateleira();
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//                @Override
//                public void onNothingSelected(AdapterView<?> adapterView) {
//                    try {
//                        updateNumPrateleira();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            });
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    Product prod = new Product();
//    public void addSpinnerNumPrateleira(){
//        try{
//        CustomAdapterSpinnerNumPrateleiras adpterNumPra = new CustomAdapterSpinnerNumPrateleiras(SearchAddressActivity.this,null);
//        spnNumeroPrateleira.setAdapter(adpterNumPra);
//        spnNumeroPrateleira.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//                try {
//                    if(adapterView.getItemIdAtPosition(i)==0){
//                        txtTitulo.setText("");
//                        onNothingSelected(adapterView);
//                    }else {
//                        ConnectionBuscaProdutoMysql conMy = new ConnectionBuscaProdutoMysql();
//                        Produto produto = new Produto();
//                        produto = (Produto) conMy.execute(l).get();
//
//                        String cod = produto.getCodBarra();
//                        ConnectionBuscaProduto con = new ConnectionBuscaProduto();
//                        prod = (Product) con.execute(cod).get();
//                        int estoq = prod.getStock();
//                        String plu = prod.getInternalCode();
//                        String titulo = " " + prod.getDescription();
//                        txtTitulo.setText(titulo + "\n PLU:  " + plu + "\n" + " Quantidade em Estoque ERP: " + estoq);
//
//                        show(produto);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    onNothingSelected(adapterView);
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//                    txtTitulo.setText(" ");
//                    //showAdd(false);
//            }
//        });
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//
//        return super.onContextItemSelected(item);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.search_main_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if(id== R.id.item_estoque){
//            Intent goToCadastro = new Intent(getApplicationContext(),AddressRegisterActivity.class);
//            int endereco = (int) spnNumeroPrateleira.getItemIdAtPosition(spnNumeroPrateleira.getSelectedItemPosition());
//            goToCadastro.putExtra("endereco",endereco);
//            startActivity(goToCadastro);
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
//        assert scanningResult != null;
//        if (scanningResult.getContents() != null) {
//            String scanBar = scanningResult.getContents();
//            ConnectionScan con = new ConnectionScan();
//            try {
//                Product p = con.execute(scanBar, unidade).get();
//                if(p != null) {
//                    String preco = "R$ " + p.getPrice().replace('.', ',');
//                    txtPreco.setText(preco);
//                    txtEstoque.setText(String.valueOf(p.getStock()));
//                    int quantEstoque =  p.getStock();
//                    titulo = p.getDescription();
//                    subtitulo = "\nPLU: " + p.getInternalCode();
//                    txtTitulo.setText("Descrição: "+titulo+"  "+quantEstoque);
//                    txtEstoque.setText(subtitulo);
//
//                    if (p.isInSale()) {
//                        txtPreco.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.precoPromocional));
//                    } else {
//                        txtPreco.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.precoNormal));
//                    }
//                }else{
//                    titulo = "Produto não encontrado!";
//                    subtitulo = "Tente pesquisar novamente.";
//                    txtTitulo.setText(titulo);
//                    txtEstoque.setText(subtitulo);
//                }
//            } catch (Exception ex){
//                ex.printStackTrace();
//                finish();
//                flags.setFirstScan(true);
//            }
//        } else {
//            finish();
//            flags.setFirstScan(true);
//        }
//    }
//
//    @Override
//    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
//        Log.i("value is","oi"+ i1);
//        value = i1;
//    }
//}
