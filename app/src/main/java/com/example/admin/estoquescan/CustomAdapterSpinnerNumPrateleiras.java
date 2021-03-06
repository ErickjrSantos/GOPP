package com.example.admin.estoquescan;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.estoquescan.Classes.NumeroPrateleira;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 01/11/17.
 */

public class CustomAdapterSpinnerNumPrateleiras extends BaseAdapter {

    private List<NumeroPrateleira> numPrateleiras;
    private final Context context;

    public CustomAdapterSpinnerNumPrateleiras(Context context, List<NumeroPrateleira> numPrateleiras){
       this.numPrateleiras = numPrateleiras;
       this.context = context;
   }
    public void setNumeroPrateleiras(ArrayList<NumeroPrateleira> numeroPrateleiras){
        //this.numPrateleiras.clear();
        this.numPrateleiras = numeroPrateleiras;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(numPrateleiras == null || numPrateleiras.isEmpty()) return 0;
        return numPrateleiras.size();}

    @Override
    public Object getItem(int i) {return null;}

    @Override
    public long getItemId(int i) {return numPrateleiras.get(i).getId_endereco();}

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        NumeroPrateleira pretNum = numPrateleiras.get(position);
        TextView numPRA = new TextView(context);
        numPRA.setText(pretNum.toString());
        numPRA.setBackgroundResource(R.drawable.ic_spinner);
        return numPRA;
    }
}
