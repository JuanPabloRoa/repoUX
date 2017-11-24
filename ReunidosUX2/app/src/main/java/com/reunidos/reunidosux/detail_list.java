package com.reunidos.reunidosux;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class detail_list extends AppCompatActivity implements View.OnClickListener{
    ListView mListView;
    TextView detailName;
    TextView title;
    Button boton1;
    MyAdapter mAdapter;
    ArrayList<String> item;
    Boolean estado= true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_list);
        Bundle bundle=getIntent().getExtras();
        String select=bundle.getString("SELECT");
        boton1=(Button)  findViewById(R.id.botone);
        mListView = (ListView) findViewById(R.id.detail_list_view);
        title = (TextView) findViewById(R.id.mailTextView);

        //descripcion=(TextView)  findViewById(R.id.descripcion);
        boton1.setOnClickListener(this);

        //detailName = (TextView) findViewById(R.id.detailName);
        //detailName.setText(select);

        if (savedInstanceState == null) {
            item = new ArrayList<>();
            String[] tokens0 = select.split("_");
            String[] tokens = tokens0[1].split("/");
            String[] tokens1 = tokens0[0].split("/");
            title.setText(tokens1[0]);
            //descripcion.setText(tokens1[2]);
            for (String t : tokens){
                item.add(t);//add item to arraylist
            }

        } else {
            item = savedInstanceState.getStringArrayList("item");
        }
        mAdapter = new detail_list.MyAdapter(this,R.layout.row_detail_list, item);
        mListView.setAdapter(mAdapter);



    }
    public void aceptar() {
        Toast t=Toast.makeText(this,"Se ha añadido exitosamente.", Toast.LENGTH_SHORT);
        t.show();
    }

    public void cancelar() {

    }
    @Override
    public void onClick(View v) {

        if(v == boton1) {
            AlertDialog.Builder confirmar = new AlertDialog.Builder(this);
            confirmar.setTitle("Importante");
            confirmar.setMessage("¿ Esta seguro que desea añadirse a la lista ?");
            confirmar.setCancelable(false);
            confirmar.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                    boton1.setVisibility(View.GONE);
                    aceptar();

                }
            });
            confirmar.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                    cancelar();
                }
            });
            confirmar.show();





        }

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("item", item);
    }

    private class MyAdapter extends ArrayAdapter<String> {

        public MyAdapter(Context context, int resource, List<String> objects1) {
            super(context, resource, objects1);

        }

        public View getView(int position, @Nullable View fila, @NonNull ViewGroup parent) {

            LayoutInflater mInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            if (fila == null){
                // Instanciar la fila
                fila = mInflater.inflate(R.layout.row_detail_list, null);
            }


            TextView itemview = (TextView) fila.findViewById(R.id.itemTextView);
            TextView precioview=(TextView) fila.findViewById(R.id.itemTextViewprecio);
            String item = getItem(position);
            String[] tokens2 = item.split("-");
            int i=0;
            for (String t : tokens2){
                if(i==0){ itemview.setText(t);}
                if(i==1){precioview.setText(t);}
                i++;
            }


            return fila;
        }
    }
}
