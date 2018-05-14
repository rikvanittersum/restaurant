package com.example.gebruiker.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ItemDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        // getting views
        TextView nameDetail = findViewById(R.id.nameDetail);
        TextView priceDetail= findViewById(R.id.priceDetail);
        TextView descritionDetail = findViewById(R.id.descritionDetail);
        ImageView imageDetail = (ImageView) findViewById(R.id.imageDetail);

        // get intent
        Intent intent = getIntent();

        // retrieve values from intent
        String name = intent.getStringExtra("name");
        String description = intent.getStringExtra("description");
        int price = intent.getIntExtra("price", 1);
        String url = intent.getStringExtra("url");

        // setting textviews
        nameDetail.setText(name);
        priceDetail.setText("€ " + price);
        descritionDetail.setText(description);

        //setting image via picasso
        Picasso.get().load(url).into(imageDetail);
    }

}
