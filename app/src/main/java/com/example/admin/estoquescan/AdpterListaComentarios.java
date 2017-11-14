package com.example.admin.estoquescan;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.admin.estoquescan.Classes.Comentarios;
import java.util.List;


/**
 * Created by user on 14/11/17.
 */

public class AdpterListaComentarios extends BaseAdapter{

    private final List<Comentarios> comentarios;
    private final Activity act;

    public AdpterListaComentarios(List<Comentarios> comentarios, Activity act) {
        this.comentarios = comentarios;
        this.act = act;
    }



    @Override
    public int getCount() {
        if(comentarios==null||comentarios.isEmpty()) return 0;

        return comentarios.size();
    }

    @Override
    public Object getItem(int position) {
        return comentarios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
       View view = act.getLayoutInflater()
               .inflate(R.layout.lista_comentario_personalizado, viewGroup, false);

        Comentarios comentario = comentarios.get(position);
        TextView nome = (TextView)
                view.findViewById(R.id.lista_comentarios_personalizada_nome);
        TextView descricao =(TextView)
                view.findViewById(R.id.lista_comentarios_personalizada_descricao);
        TextView comentou = (TextView)
                view.findViewById(R.id.lista_comentarios_personalizada_nome_comentou);
        TextView produto = (TextView)
                view.findViewById(R.id.lista_comentarios_personalizada_produto);


        nome.setText(comentario.getComentario());
        descricao.setText(comentario.getData());
        comentou.setText(comentario.getNome());
        produto.setText(comentario.getNome_produto());

        return view;
    }

}
