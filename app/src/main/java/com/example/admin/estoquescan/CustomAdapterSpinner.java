package com.example.admin.estoquescan;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.admin.estoquescan.Classes.Estoque;

import java.util.List;

/**
 * Created by user on 24/10/17.
 */

public class CustomAdapterSpinner extends ArrayAdapter<Estoque> {
    LayoutInflater flater;
    public CustomAdapterSpinner(Activity context, int resource, List<Estoque> list) {
        super(context, resource, list );
        flater = context.getLayoutInflater();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        if(view == null){
            view = flater.inflate(R.layout.new_layout_spnner_01,parent, false);
        }
        Estoque item = getItem(position);

        TextView txtNome = view.findViewById(R.id.layout_spinner);
        txtNome.setText(item.getNome_estoque());

//        TextView txtid = view.findViewById(R.id.layout_spinner_1);
//        txtid.setText(String.valueOf(item.getId_estoque()));
//
//        TextView txtSigla = view.findViewById(R.id.layout_spinner_2);
//        txtSigla.setText(item.getSigla());

        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        if(view == null){
            view = flater.inflate(R.layout.new_layout_spnner_01,parent, false);
        }
        Estoque item = getItem(position);

        TextView txtNome = view.findViewById(R.id.layout_spinner);
        txtNome.setText(item.getNome_estoque());

//        TextView txtid = view.findViewById(R.id.layout_spinner_1);
//        txtid.setText(String.valueOf(item.getId_estoque()));
//
//        TextView txtSigla = view.findViewById(R.id.layout_spinner_2);
//        txtSigla.setText(item.getSigla());
        return view;

    }
}
