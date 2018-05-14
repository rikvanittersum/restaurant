package com.example.gebruiker.restaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class menuAdapter extends ArrayAdapter {

    private ArrayList <MenuItem> items;
    private Context context;
    int resource;

    public menuAdapter (@NonNull Context context, int resource, @NonNull ArrayList objects) {
        super(context, resource, objects);

        items = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_menu_item_item, parent, false);
        }

        // getting views
        TextView nameView = (TextView) convertView.findViewById(R.id.MenuItem);
        TextView priceView = (TextView) convertView.findViewById(R.id.ItemPrice);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.menuImage);

        // get values for views
        MenuItem item = items.get(position);
        String name = item.getName();
        int price = item.getPrice();
        String url = item.getImageUrl();

        // setting textviews
        nameView.setText(name);
        priceView.setText("€ " + price);

        // setting image with url via picasso
        Picasso.get().load(url).into(imageView);

        return convertView;
    }
}
