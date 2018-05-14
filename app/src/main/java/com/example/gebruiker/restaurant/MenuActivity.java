package com.example.gebruiker.restaurant;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback {


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // getting all menu items
        MenuRequest menu = new MenuRequest(this);
        menu.getMenu(this);

    }

    @Override
    public void gotMenu(ArrayList<MenuItem> menuItems) {

        // getting listview, intent and String from intent
        ListView listview = findViewById(R.id.menu);
        Intent intent = getIntent();
        String category = intent.getStringExtra("category");

        // creating new menuitem arraylist
        ArrayList <MenuItem> itemsInCategory = new ArrayList<>();

        // iterating over menu items
        for(int i = 0; i < menuItems.size(); i++) {
            MenuItem item = menuItems.get(i);
            // putting only items of category on which was clicked
            if (item.getCategory().equals(category)){
                itemsInCategory.add(item);
            }
        }

        //set adapter and item click listener
        menuAdapter adapter = new menuAdapter(this, R.layout.activity_menu_item_item, itemsInCategory);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new ListViewOnItemClickListener());
    }

    @Override
    public void gotMenuError(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    private class ListViewOnItemClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // get menu item
            MenuItem item = (MenuItem) parent.getItemAtPosition(position);

            // get values from item
            String name = item.getName();
            int price = item.getPrice();
            String description = item.getDescription();
            String url = item.getImageUrl();

            // put values in new intent
            Intent intent = new Intent(MenuActivity.this, ItemDetail.class);
            intent.putExtra("name", name);
            intent.putExtra("price", price);
            intent.putExtra("description", description);
            intent.putExtra("url", url);

            // start new activity
            startActivity(intent);
        }
    }
}
