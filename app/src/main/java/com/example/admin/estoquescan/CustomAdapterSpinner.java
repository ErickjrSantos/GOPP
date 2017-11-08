package com.example.admin.estoquescan;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.admin.estoquescan.Classes.Estoque;
import java.util.List;


/**
 * Created by user on 24/10/17.
 */

public class CustomAdapterSpinner extends BaseAdapter {

    private List<Estoque> estoques;
    private final Context context;

    public CustomAdapterSpinner(Context context, List<Estoque> estoques){
        this.context = context;
        this.estoques = estoques;
    }

    public void setEstoques(List<Estoque> estoques){

        this.estoques = estoques;
        notifyDataSetChanged(); //notificar que mudou a porra toda
    }

    @Override
    public int getCount() {
        if(estoques == null || estoques.isEmpty()) return 0;
        return estoques.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return estoques.get(i).getId_estoque();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Estoque estoque = estoques.get(position);
        TextView  viewEst = new TextView(context);
        viewEst.setText(estoque.toString());
        viewEst.setBackgroundResource(R.drawable.ic_spinner);
        return viewEst;
    }

}

