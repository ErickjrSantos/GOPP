package com.example.admin.estoquescan;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.admin.estoquescan.Classes.Comentarios;
import com.example.admin.estoquescan.Classes.User;
import com.example.admin.estoquescan.Connection.ConnectionComentarios;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import static java.lang.Math.floor;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public ListView comentariosList;
    public EditText comentariosSetor;
    public RadioButton ra_ti;
    private static final String GOPP_PREFERENCES = "GOPPPreferences";
    public   String texto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        comentariosList = (ListView) findViewById(R.id.lista_comentarios);

        showCadastro();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("GOPP");
            getSupportActionBar().setSubtitle("Menu Principal");
        }
    }


    private void showCadastro() {

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

                dialog.setView(R.layout.app_dialog_main);


                ra_ti = (RadioButton)findViewById(R.id.ra_ti);
                comentariosSetor = (EditText) findViewById(R.id.comentario_setor);

                dialog.setTitle("Envie uma notificaçao!!!... ")
                        .setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                texto = String.valueOf(comentariosSetor.getText().toString());
                                Toast.makeText(MainActivity.this, "Teste: " + texto, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();

                }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finish();
        }
    }


    @Override
    protected void onResume() {
        //listacomentarios();
        super.onResume();
    }

    public void listacomentarios(){
        try {
            ConnectionComentarios connect = new ConnectionComentarios(MainActivity.this);
            final Comentarios[] comentario = connect.execute().get();

            final ListView comentariosList = (ListView) findViewById(R.id.lista_comentarios);
            AdpterListaComentarios adpter = new AdpterListaComentarios(comentario,this);
            comentariosList.setAdapter(adpter);

            comentariosList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent goComent = new Intent(MainActivity.this,comentario_Activity.class);

                    String posicao = comentario[i].getComentario();
                    int id_coment = comentario[i].getId_comentarios();
                    String nome = comentario[i].getNome();
                    String item = comentario[i].getNome_produto();
                    String data = comentario[i].getData();

                    goComent.putExtra("posicao",posicao);
                    goComent.putExtra("nome",nome);
                    goComent.putExtra("item",item);
                    goComent.putExtra("data",data);
                    goComent.putExtra("ID",id_coment);

                    startActivity(goComent);
                    onPause();
                }

            });

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        User user = User.getSavedUser();

        TextView textUser = (TextView) findViewById(R.id.txtNome);
        textUser.setText(user.getUsername());

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        String strDate = sdf.format(cal.getTime());
        TextView textData = (TextView) findViewById(R.id.txtData);
        textData.setText(strDate);

        ImageView image = (ImageView) findViewById(R.id.imageView);
        Drawable userPhoto = user.getImage(getApplicationContext());
        if(!user.getImageString().equals("")) {
            image.setImageDrawable(userPhoto);
        }else{
            image.setImageDrawable(getNoUserPicture());
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            getSharedPreferences(GOPP_PREFERENCES, 0).edit().clear().apply();

            Intent goToLogin = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(goToLogin);
            finish();
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_scan) {
            Intent goToScan = new Intent(this,ScanActivity.class);
            startActivity(goToScan);
        } else if (id == R.id.expiration_management) {
//            Intent goToExpiration = new Intent(getApplicationContext(), SearchAddressActivity.class);
//            startActivity(goToExpiration);
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.action_set_endereco) {
            Intent goToCadastro = new Intent(getApplicationContext(),AddressRegisterActivity.class);
            startActivity(goToCadastro);
            onPause();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public Drawable getNoUserPicture(){
        Drawable photo = null;
        double random = Math.random() * 3;
        double y = floor(random);

        int x = (int) y;

        switch(x){
            case 0:
                photo = getDrawable(R.drawable.blankorange);
                break;
            case 1:
                photo = getDrawable(R.drawable.blanklightgreen);
                break;
            case 2:
                photo = getDrawable(R.drawable.blankolivegreen);
                break;
        }
        return photo;
    }

}
