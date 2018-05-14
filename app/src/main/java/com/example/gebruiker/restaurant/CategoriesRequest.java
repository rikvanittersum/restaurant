package com.example.gebruiker.restaurant;

import android.content.Context;
import android.util.Log;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener{

    Callback callback;
    Context context;

    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotCategoriesError("Could not retrieve categories");

    }

    @Override
    public void onResponse(JSONObject response) {
        // new arraylist
        ArrayList<String> categoriesList = new ArrayList<>();

        try {
            // retrieving categories from JSON
            JSONArray categories = response.getJSONArray("categories");

            // adding categories to arraylist
            for(int i = 0; i < categories.length(); i++) {
                categoriesList.add(categories.get(i).toString());
            }
        // show when failed
        } catch (JSONException e) {
            callback.gotCategoriesError("Error");
        }

        callback.gotCategories(categoriesList);
    }

    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }


    public CategoriesRequest (Context context){
        this.context = context;
    }

    public void getCategories(Callback activity){

        // retrieve categories from JSON
        RequestQueue queue = Volley.newRequestQueue(context);
        callback = activity;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/categories", null, this, this);
        queue.add(jsonObjectRequest);

    }



}
