package com.reunidos.reunidosux;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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


    ArrayList<String> emails;
    ArrayList<ArrayList<String>> todo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        mDbHelper.deleteAll();
        mDbHelper.insertData("Lista JP","Lista escolar Colegio Francisco Ramirez","5 días");
        mDbHelper.insertData("Lista Dany","Lista escolar Colegio 1","20 hrs");
        mDbHelper.insertData("Lista Rubyano","Lista Seccion A-1 Taller, Universidad de Chile","3 días");
        mDbHelper.insertData("Lista Diego","Arquitectura USACH Seccion 2B","2 días");
        mDbHelper.insertData("Lista Roa","Utiles escolares Subercaseaux Collegge Octavo Basico","10 días");
        mDbHelper.insertData("Lista Juanito","Diseño Grafico, Taller, Universidad de Chile","5 días");


        setContentView(R.layout.activity_listas);




        mListView = (ListView) findViewById(R.id.list_view);
        Cursor res = mDbHelper.getAllData();
        res.moveToFirst();
        String nombre = res.getString(res.getColumnIndexOrThrow(mDbHelper.COL_2));
        String desc = res.getString(res.getColumnIndexOrThrow(mDbHelper.COL_3));
        String tiemp = res.getString(res.getColumnIndexOrThrow(mDbHelper.COL_4));

        if (savedInstanceState == null) {
            emails = new ArrayList<>();
            nombre=nombre+"/"+desc+"/"+tiemp;

            emails.add(nombre);

            while(res.moveToNext()!=false){
                nombre = res.getString(res.getColumnIndexOrThrow(mDbHelper.COL_2));
                desc = res.getString(res.getColumnIndexOrThrow(mDbHelper.COL_3));
                tiemp = res.getString(res.getColumnIndexOrThrow(mDbHelper.COL_4));
                nombre=nombre+"/"+desc+"/"+tiemp;
                emails.add(nombre);

            }


        } else {
            emails = savedInstanceState.getStringArrayList("emails");
        }

        mAdapter = new MyAdapter(this,R.layout.custom_row, emails);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedEmail = mAdapter.getItem(position);

                Toast.makeText(Listas.this, "Funcionalidad no disponible en esta versión", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("emails", emails);
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
                fila = mInflater.inflate(R.layout.custom_row, null);
            }


            TextView mailImageView = (TextView) fila.findViewById(R.id.mailTextView);
            TextView descripcion = (TextView) fila.findViewById(R.id.descripcion);
            TextView tiempo = (TextView) fila.findViewById(R.id.tiempo);
            String correo = getItem(position);
            String[] tokens = correo.split("/");
            int i=0;
            for (String t : tokens){
                if(i==0){mailImageView.setText(t);}
                if(i==1){descripcion.setText(t);}
                if(i==2){tiempo.setText(t);}
                i++;
                }





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
