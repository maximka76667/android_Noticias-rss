package com.example.muyinteresante;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.muyinteresanteNoTocar.DescargaNoticiasRSS;
import com.example.muyinteresanteNoTocar.NoticiaRSS;
import com.example.muyinteresanteNoTocar.iNoticiaRSS;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements iNoticiaRSS {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        descargarNoticias();
    }

    @Override
    public void onRecibeNoticiasRSS(ArrayList<NoticiaRSS> listaNoticias) {
        NoticiasAdapter adapter = new NoticiasAdapter(MainActivity.this, R.layout.noticia, listaNoticias);

        // Obtener ListView principal
        ListView listView_noticias = this.findViewById(R.id.listView);

        // Asignar onItemClickListener
        listView_noticias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uri uri = Uri.parse(listaNoticias.get(position).getEnlace());
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });

        // Asignar adaptador
        listView_noticias.setAdapter(adapter);

        Log.i("lista de noticias", listaNoticias.toString());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_actualizar) { // Opcion de menu Actualizar
            descargarNoticias();
        }

        return super.onOptionsItemSelected(item);
    }

    // Se lanza la tarea que descarga, generara la lista de noticias
    // y al finalizar entregara al metodo onRecibeNoticiasRSS
    public void descargarNoticias() {
        new DescargaNoticiasRSS(this, this).execute("http://feeds.feedburner.com/Muyinteresantees?format=xml", NoticiaRSS.RSS_MUY_INTERESANTE);
    }

}
