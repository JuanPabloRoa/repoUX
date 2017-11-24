package com.reunidos.reunidosux;


import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import android.database.Cursor;


public class Listas extends ActionBarActivity {

    ListView mListView;

    MyAdapter mAdapter;
    basededatosHelper mDbHelper = new basededatosHelper(this);

    Integer[] imgid = {R.drawable.sharpie, R.drawable.lapiz, R.drawable.image3, R.drawable.image4, R.drawable.sharpie,R.drawable.sharpie, R.drawable.lapiz, R.drawable.image3, R.drawable.image4, R.drawable.sharpie};
    ArrayList<String> emails;
    ArrayList<ArrayList<String>> todo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        mDbHelper.deleteAll();
        mDbHelper.insertData("Articulos escolares","Juan Pablo Roa","Lista de cosas para el colegio","_lápiz-10/cuadernos-15/gomas-10/estuches-2");
        mDbHelper.insertData("Lista Seccion A-1 arquitectura","Juan Pablo Roa","Lista para el primer trabajo","_Tiralineas-10/Papel bond-15/Block-10/Silicona-2");
        mDbHelper.insertData("Lista arquitectura USACH","Juan Pablo Roa","Lista para el ultimo trabajo del semestre","_lápiz-10/cuadernos-15/gomas-10/estuches-2");
        mDbHelper.insertData("Lista Seccion B-1 diseño industrial","Juan Pablo Roa","Lista para el trabajo de taller","_Gubias-10/Acrilico-15/Madera-10/Agorex-2");
        mDbHelper.insertData("Lista Seccion B-2 comunicacion y medios USACH","Juan Pablo Roa","Lista para el trabajo de fotografia","_lápiz-10/cuadernos-15/gomas-10/estuches-2");
        mDbHelper.insertData("Lista Seccion A-1 U. de Chile","Juan Pablo Roa","Lista para la primera prueba","_lapiz-10/cuadernos-15/gomas-10/estuches-2");
        mDbHelper.insertData("Lista Colegio Benjamin Subercaaseaux","Juan Pablo Roa","Lista para el trabajo de artes visuales","_lápiz-10/cuadernos-15/gomas-10/estuches-2");
        mDbHelper.insertData("Lista Colegio Francico Ramirez 2do medio","Juan Pablo Roa","Lista para el trabajo de tecnologia","_lápiz-10/cuadernos-15/gomas-10/estuches-2");
        mDbHelper.insertData("Lista Duoc Seccion B-2","Diego Polanco","Lista para el trabajo del profe simpatico","_Tiralinea-10/Block-15/Palos de maqueta-10/Acrilico-2/Silicona-1/");


        setContentView(R.layout.activity_listas);




        mListView = (ListView) findViewById(R.id.list_view);
        Cursor res = mDbHelper.getAllData();
        res.moveToFirst();
        String nombre = res.getString(res.getColumnIndexOrThrow(mDbHelper.COL_2));
        String desc = res.getString(res.getColumnIndexOrThrow(mDbHelper.COL_3));
        String prec = res.getString(res.getColumnIndexOrThrow(mDbHelper.COL_4));
        String items = res.getString(res.getColumnIndexOrThrow(mDbHelper.COL_5));

        if (savedInstanceState == null) {
            emails = new ArrayList<>();
            nombre=nombre+"/"+desc+"/"+prec+"/"+items;

            emails.add(nombre);

            while(res.moveToNext()!=false){
                nombre = res.getString(res.getColumnIndexOrThrow(mDbHelper.COL_2));
                desc = res.getString(res.getColumnIndexOrThrow(mDbHelper.COL_3));
                prec = res.getString(res.getColumnIndexOrThrow(mDbHelper.COL_4));
                items = res.getString(res.getColumnIndexOrThrow(mDbHelper.COL_5));
                nombre=nombre+"/"+desc+"/"+prec+"/"+items;
                emails.add(nombre);

            }


        } else {
            emails = savedInstanceState.getStringArrayList("emails");
        }

        mAdapter = new MyAdapter(this,R.layout.custom_row, emails,imgid);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedEmail = mAdapter.getItem(position);

                Intent intent=new Intent(Listas.this,detail_list.class);
                intent.putExtra("SELECT",selectedEmail);
                startActivity(intent);

            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("emails", emails);
    }

    private class MyAdapter extends ArrayAdapter<String> {
        private final Integer[] imgid;
        public MyAdapter(Context context, int resource, List<String> objects1,Integer[] imgid) {
            super(context, resource, objects1);
            this.imgid=imgid;
        }

        public View getView(int position, @Nullable View fila, @NonNull ViewGroup parent) {

            LayoutInflater mInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            if (fila == null){
                // Instanciar la fila
                fila = mInflater.inflate(R.layout.custom_row, null);
            }


            TextView mailImageView = (TextView) fila.findViewById(R.id.mailTextView);
            ImageView imageView = (ImageView) fila.findViewById(R.id.icon);
            TextView descripcion = (TextView) fila.findViewById(R.id.descripcion);
            TextView precio = (TextView) fila.findViewById(R.id.precio);
            String correo = getItem(position);
            String[] tokens = correo.split("/");
            int i=0;
            for (String t : tokens){
                if(i==0){mailImageView.setText(t);}
                if(i==1){descripcion.setText(t);}
                if(i==2){precio.setText(t);}
                i++;
                }
            imageView.setImageResource(imgid[position]);




            return fila;
        }
    }

    public String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
