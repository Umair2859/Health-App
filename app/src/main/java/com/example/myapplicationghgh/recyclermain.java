package com.example.myapplicationghgh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplicationghgh.adapters.adapterr1;
import com.example.myapplicationghgh.userdata.datalist;
import com.example.myapplicationghgh.userdata.datar1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class recyclermain extends AppCompatActivity implements RecyclerViewInterface{
RecyclerView recyclerViewmain;
ArrayList<datar1> datar1ArrayList;
ArrayList food_list;
   int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclermain);
        getSupportActionBar().hide();
        food_list = getIntent().getParcelableArrayListExtra("food_list");

        recyclerViewmain=findViewById(R.id.main);
        ArrayList<datar1> datar1ArrayList= new ArrayList<>();
        adapterr1 adpot= new adapterr1(datar1ArrayList, this, this);
        recyclerViewmain.setAdapter(adpot);
        if (datar1ArrayList==null){
        Toast.makeText(this, "No data found Run the python script to get data", Toast.LENGTH_SHORT).show();}
        for(i=0; i<food_list.size(); i++)
        {
            datar1ArrayList.add(new datar1("Day:  "+(i+1)));
        }
//        for( i =1; i<=30; i++){
//
//
//
//
//        }

        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        recyclerViewmain.setLayoutManager(layoutManager);

    }

    @Override
    public void onitemclick(int position) {
        Intent intent= new Intent(this, recyler2.class);

        Map<String,Object> d = (Map<String, Object>) food_list.get(position) ;
        ArrayList day_food = (ArrayList) d.get("food_data");
        //Log.d("testing lits",d.toString());
        intent.putParcelableArrayListExtra("today_data",day_food);
        startActivity(intent);
    }
}