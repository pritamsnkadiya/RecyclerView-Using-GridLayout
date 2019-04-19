package com.example.pritam.recyclerviewusinggridlayout;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    public static Context APP_Context = null;
    RecyclerView.LayoutManager layoutManager;
    List<Photo> personUtilsList;

    RequestQueue rq;

    String request_url ="https://jsonplaceholder.typicode.com/photos";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        MainActivity.APP_Context = getApplicationContext ();
        rq = Volley.newRequestQueue(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager (this);

        recyclerView.setLayoutManager(layoutManager);

        personUtilsList = new ArrayList<>();

        sendRequest();

    }

    public static Context getAppContext(){
        return APP_Context;
    }
    public void sendRequest(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, request_url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i = 0; i < response.length(); i++){

                    Photo personUtils = new Photo ();

                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        personUtils.setTitle (jsonObject.getString("title"));
                        personUtils.setThumbnailUrl (jsonObject.getString("thumbnailUrl"));
                        personUtils.setUrl (jsonObject.getString("url"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    personUtilsList.add(personUtils);

                }

                mAdapter = new CustomRecyclerAdapter (MainActivity.this, personUtilsList);

                recyclerView.setAdapter(mAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Volley Error: ", String.valueOf (error));
            }
        });

        rq.add(jsonArrayRequest);

    }

}