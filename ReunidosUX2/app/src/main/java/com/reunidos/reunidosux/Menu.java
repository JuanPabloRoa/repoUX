package com.reunidos.reunidosux;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;
import com.synnapps.carouselview.ViewListener;

public class Menu extends AppCompatActivity implements View.OnClickListener {
    CarouselView carouselView;

    Button boton1,boton2,boton3;

    TextView carouselLabel;

    int[] sampleImages = {R.drawable.sharpie, R.drawable.lapiz, R.drawable.image3, R.drawable.image4, R.drawable.sharpie};
    String[] sampleTitles = {"Bonito", "Barato", "Bueno", "Asombroso", "Extraordinario"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);





        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);
        carouselLabel = (TextView) findViewById(R.id.carouselLabel);
        boton1=(Button)  findViewById(R.id.button1);
        boton1.setOnClickListener(this);
        boton3=(Button)  findViewById(R.id.button3);
        boton3.setOnClickListener(this);
        carouselView.setOnClickListener(this);
        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(Menu.this, "Funcionalidad no disponible en esta versión", Toast.LENGTH_SHORT).show();
            }
        });



    }


    // To set custom views

    @Override
    public void onClick(View v) {
        Toast bar = Toast.makeText(Menu.this, "Funcionalidad no disponible en esta versión", Toast.LENGTH_SHORT);

        switch (v.getId()){
            case R.id.button1:
                Intent intentListas=new Intent(Menu.this,Listas.class);

                startActivity(intentListas);
                break;
            case R.id.button3:

                bar.show();
                break;
            case R.id.carouselView:

                bar.show();
                break;
        }

    }
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {

            imageView.setImageResource(sampleImages[position]);
       }
    };
}