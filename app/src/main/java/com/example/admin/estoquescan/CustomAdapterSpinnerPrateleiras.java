package com.example.admin.estoquescan;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.estoquescan.Classes.Prateleira;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 26/10/17.
 */

public class CustomAdapterSpinnerPrateleiras extends BaseAdapter {

    private List<Prateleira> prateleiras;
    private final Context context;

    public CustomAdapterSpinnerPrateleiras(Context context, List<Prateleira> prateleiras){
        this.prateleiras = prateleiras;
        this.context = context;
    }

    public void setPrateleiras(ArrayList<Prateleira> prateleiras){
        //this.prateleiras.clear();
        this.prateleiras = prateleiras;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(prateleiras == null || prateleiras.isEmpty()) return 0;
        return prateleiras.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return prateleiras.get(i).getId_prateleira();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Prateleira prat = prateleiras.get(position);
        TextView ViewPra = new TextView(context);
        ViewPra.setText(prat.toString());

        return ViewPra;
    }
}
