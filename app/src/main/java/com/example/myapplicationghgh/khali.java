package com.example.myapplicationghgh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class khali extends AppCompatActivity {
    ArrayList data =new ArrayList<>();
public void t(View view){
    Log.d("myTag", "on click done");


    FirebaseFirestore.getInstance().collection("User").document(FirebaseAuth.getInstance().getUid()).collection("food_list").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            int i =0;
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Map<String,Object> food = document.getData();
                    data.add(i,food);
                    i++;
                }
                SharedPreferences sharedPreferences= getSharedPreferences("DietPlan",MODE_PRIVATE );
                sharedPreferences.edit().putBoolean("isStarted", true).commit();
                Intent intent= new Intent(khali.this, recyclermain.class);
                intent.putParcelableArrayListExtra("food_list",data);
                startActivity(intent);
                //getIntent().getSerializableExtra("MyClass");
            }
        }
    });
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khali);
        getSupportActionBar().hide();



    }
}