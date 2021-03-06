package com.example.admin.estoquescan;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.estoquescan.Classes.Corredor;

import java.util.List;

/**
 * Created by user on 25/10/17.
 */

public class CustomAdapterSpinnerCorredores extends BaseAdapter {

    private List<Corredor> corredores;
    private final Context context;

    public CustomAdapterSpinnerCorredores(Context context, List<Corredor> corredores){
       this.context = context;
       this.corredores = corredores;

    }

    public void setCorredores(List<Corredor> corredores){
        //this.corredores.clear();
        this.corredores = corredores;
       notifyDataSetChanged();


    }

    @Override
    public int getCount() {
        if(corredores == null || corredores.isEmpty()) return 0;
        return corredores.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return corredores.get(i).getId_corredor();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Corredor corredor = corredores.get(position);
        TextView viewCor = new TextView(context);
        viewCor.setText(corredor.toString());
        viewCor.setBackgroundResource(R.drawable.ic_spinner);
        return viewCor;
    }
}
