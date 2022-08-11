package com.example.myapplicationghgh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplicationghgh.adapters.adapter;
import com.example.myapplicationghgh.userdata.datalist;

import java.util.ArrayList;
import java.util.Map;

public class recyler2 extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<datalist> datalists;
    ArrayList daily_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyler2);
        getSupportActionBar().hide();
        daily_list = getIntent().getIntegerArrayListExtra("today_data");

        Log.d("data",daily_list.toString());
        recyclerView=findViewById(R.id.rcl);
        ArrayList<datalist> datalists=new ArrayList<>();
        adapter adopt= new adapter(datalists,this);
        recyclerView.setAdapter(adopt);
       for(int i=0; i<daily_list.size(); i++)
       {
           Map<String,Object> d = (Map<String, Object>) daily_list.get(i);
           datalists.add(new datalist(d.get("name").toString(),  d.get("description").toString(), d.get("sugar").toString(), d.get("calories").toString(),d.get("fats").toString(), d.get("cholesterol").toString()));
       }
        //datalists.add(new datalist("asd","kasd","kjahd","kahd","kuahd"));
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }
}