package com.example.finalproject;

import static java.lang.Math.sqrt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TourDetailActivity extends AppCompatActivity {
    ImageView imgTour;
    TextView nameTour, descTour, priceTour, txtCount;
    Button addCount, subCount, btnPay ;
    ImageButton btnLoc;
    String KeyImgTour , KeyTotalPrice , KeyNameTour,KeyLoc,KeyCountItems,keyPriceTour="priceTour",keyDescTour,sentNameTour,sentPriceTour;
    int mcount = 1;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_detail);
        preferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);

        imgTour = findViewById(R.id.imgTour);
        nameTour = findViewById(R.id.nameTour);
        descTour = findViewById(R.id.descTour);
        priceTour = findViewById(R.id.priceTour);
        txtCount = findViewById(R.id.txtCount);
        addCount = findViewById(R.id.btnAddCount);
        subCount = findViewById(R.id.btnSubCount);
        btnPay = findViewById(R.id.btnPay);
        btnLoc = findViewById(R.id.btnLoc);

        int KeyImageTour=getIntent().getIntExtra("image",1);
        String KeyNameTour=getIntent().getStringExtra("cityName");
        String KeyPriceTour=getIntent().getStringExtra("cityPrice");
        String KeyDescTour=getIntent().getStringExtra("cityDesc");

        nameTour.setText(KeyNameTour);
        priceTour.setText(KeyPriceTour);
        imgTour.setImageResource(KeyImageTour);
        descTour.setText(KeyDescTour);


        //getDataAdapter();
        // Set initial count value
        //mcount = preferences.getInt("count", 1);
        txtCount.setText(Integer.toString(mcount));

        addCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mcount+=1;
                txtCount.setText(Integer.toString(mcount));
                updateTotalPrice();
            }

        });
        subCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mcount > 1) {
                    mcount-=1;
                    txtCount.setText(Integer.toString(mcount));
                    updateTotalPricem();
                }
            }
        });

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sentNameTour=nameTour.getText().toString();
                sentPriceTour=priceTour.getText().toString();
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("nameTour",sentNameTour);
                editor.putString("priceTour",sentPriceTour);

                editor.commit();
                Intent outIntent=new Intent(TourDetailActivity.this,ReceiptActivity.class);
                startActivity(outIntent);
            }

        });
        btnLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getIntent().hasExtra("locTour")) {
                    String txtLoc = getIntent().getStringExtra("locTour");
                    Uri uri = Uri.parse("geo:0,0?q="+txtLoc );
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri);
                    mapIntent.setPackage("com.google.android.apps.maps");

                    if (mapIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(mapIntent);
                    }
                }
            }

        });
    }

    private void getDataAdapter() {
        if (getIntent().hasExtra("imgTour") && getIntent().hasExtra("nameTour") && getIntent().hasExtra("descTour") && getIntent().hasExtra("priceTour")){
            ;
            String image_tour = getIntent().getStringExtra("imgTour");
            String name_tour = getIntent().getStringExtra("nameTour");
            String desc_tour = getIntent().getStringExtra("descTour");
            int price_tour = getIntent().getIntExtra("priceTour", 0);

            setDataDetail(image_tour, name_tour, desc_tour, price_tour);
        }

    }

    private void setDataDetail(String image_tour, String name_tour, String desc_tour, int price_tour) {
        // Glide.with(this).asBitmap().load(image_tour).into(imgTour);

        nameTour.setText(name_tour);
        descTour.setText(desc_tour);
        priceTour.setText(Integer.toString(price_tour));
    }
    /*private void updateTotalPrice() {
        if (getIntent().hasExtra("priceTour")) {
            int priceTourValue = getIntent().getIntExtra("priceTour", 0);
            int total_price = priceTourValue * mcount;
            priceTour.setText(Integer.toString(total_price));
        }
    }*/

    private void updateTotalPrice() {
        // Retrieve the price value
        int priceTourValue = Integer.parseInt(priceTour.getText().toString().replaceAll("[^0-9]+", ""));


        // Calculate the total price
        int total_price =  priceTourValue * mcount;



        // Set the total price
        priceTour.setText(Integer.toString(total_price));


    }
    private void updateTotalPricem() {
        // Retrieve the price value
        int priceTourValue = Integer.parseInt(priceTour.getText().toString().replaceAll("[^0-9]", ""));
        // Calculate the total price
        int total_price = priceTourValue / mcount;
        // Set the total price
        priceTour.setText(Integer.toString(total_price));


    }
}