package com.example.admin.estoquescan;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.admin.estoquescan.Classes.Comentarios;



/**
 * Created by user on 14/11/17.
 */

public class AdpterListaComentarios extends BaseAdapter{

    private final Comentarios[] comentarios;
    private final Activity act;


    public AdpterListaComentarios(Comentarios[] comentarios, Activity act) {
        this.comentarios = comentarios;
        this.act = act;
    }

    @Override
    public int getCount() {
        if(comentarios==null||comentarios.length<=0) return 0;

        return comentarios.length;
    }

    @Override
    public Object getItem(int position) {
        return comentarios[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
       View view = act.getLayoutInflater()
               .inflate(R.layout.lista_comentario_personalizado, viewGroup, false);

        Comentarios comentario = comentarios[position];
        TextView nome =
                view.findViewById(R.id.lista_comentarios_personalizada_nome);
        TextView descricao =
                view.findViewById(R.id.lista_comentarios_personalizada_descricao);
        TextView comentou =
                view.findViewById(R.id.lista_comentarios_personalizada_nome_comentou);
        TextView produto =
                view.findViewById(R.id.lista_comentarios_personalizada_produto);


        nome.setText(comentario.getComentario());
        descricao.setText(comentario.getData());
        comentou.setText(comentario.getNome());
        produto.setText(comentario.getNome_produto());

        return view;
    }

}
