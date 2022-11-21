package com.example.muyinteresante;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.muyinteresanteNoTocar.AsignaImagenDeURL;
import com.example.muyinteresanteNoTocar.NoticiaRSS;

import java.util.ArrayList;
import java.util.List;

public class NoticiasAdapter extends ArrayAdapter<NoticiaRSS> {

    protected Activity activity;
    protected int layoutId;
    protected ArrayList<NoticiaRSS> noticias;

    public NoticiasAdapter(@NonNull Context context, int resource, @NonNull List<NoticiaRSS> objects) {
        super(context, resource, objects);
        this.activity = (Activity) context;
        this.layoutId = resource;
        this.noticias = (ArrayList<NoticiaRSS>) objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(layoutId, null);
        }

        NoticiaRSS noticia = noticias.get(position);

        TextView textView_titulo = setTextByViewId(convertView, R.id.titulo, noticia.getTitulo());
        TextView textView_fecha = setTextByViewId(convertView, R.id.fecha, noticia.getFechaComoStringFormateado());
        TextView textView_descripcion = setTextByViewId(convertView, R.id.descripcion, noticia.getDescripcion());

        AsignaImagenDeURL tarea = new AsignaImagenDeURL(convertView.findViewById(R.id.imagen), activity);
        tarea.execute(noticia.getUrlImagen());

        return convertView;
    }

    public TextView setTextByViewId(View view, int resource, String text) {
        TextView textView = view.findViewById(resource);
        textView.setText(text);
        return textView;
    }
}
