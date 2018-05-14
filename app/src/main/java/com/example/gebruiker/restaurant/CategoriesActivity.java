package com.example.gebruiker.restaurant;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {



    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        // retrieving categories from categorieRequest
        CategoriesRequest categories = new CategoriesRequest(this);
        categories.getCategories(this);

        // set on item click listener
        listview = findViewById(R.id.listview);
        listview.setOnItemClickListener(new ListViewItemClickListener());
    }

    @Override
    public void gotCategories(ArrayList<String> categories) {

        // setting listview adapter after retrieving categories
        ListView listview = findViewById(R.id.listview);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_category, R.id.CategoryItem, categories);
        listview.setAdapter(adapter);
    }

    @Override
    public void gotCategoriesError(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    public class ListViewItemClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // creating intent for new activity and put extra category in  intent
            Intent intent = new Intent (CategoriesActivity.this,MenuActivity.class);
            String category = (String) parent.getItemAtPosition(position);
            intent.putExtra("category", category);
            startActivity(intent);
        }
    }
}
