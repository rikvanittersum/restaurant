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

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    Callback callback;
    Context context;

    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotMenuError("Could not retrieve menu items");

    }

    @Override
    public void onResponse(JSONObject response) {
        ArrayList<MenuItem> menu = new ArrayList<>();

        try {
            JSONArray items = response.getJSONArray("items");

            for(int i = 0; i < items.length(); i++) {
                JSONObject object = items.getJSONObject(i);
                String name = object.getString("name");
                String description = object.getString("description");
                String image_url = object.getString("image_url");
                int price = object.getInt("price");
                String category = object.getString("category");

                MenuItem menuitem = new MenuItem(name, description, image_url, price, category);

                menu.add(menuitem);

            }

        } catch (JSONException e) {
            callback.gotMenuError("Error");
        }

        callback.gotMenu(menu);
    }

    public interface Callback {
        void gotMenu(ArrayList<MenuItem> menuItems);
        void gotMenuError(String message);
    }

    public MenuRequest (Context context){
        this.context = context;
    }

    public void getMenu(Callback activity){

        RequestQueue queue = Volley.newRequestQueue(context);
        callback = activity;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/menu", null, this, this);
        queue.add(jsonObjectRequest);

    }
}
