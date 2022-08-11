package com.example.myapplicationghgh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class bmi extends AppCompatActivity {
    double weight, height, resultt;

    String Result;
    EditText Height;
    EditText Weight;
    String goal;
    public void answer(View view){
        weight = Double.parseDouble(Weight.getText().toString());
        height = Double.parseDouble(Height.getText().toString()) / 0.032808;
        resultt = (weight / (height * height)) * 10000;
        Result=(String.valueOf(resultt));
        Toast.makeText(getApplicationContext(), "Your Bmi is"+ Result, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(bmi.this, signup.class);
      String  wt=String.valueOf(weight);
        float f = Float.parseFloat(Result);
        float roundOff = Math. round(f*100)/100;
        String finall= String.valueOf(roundOff);
        if (f<18.5)
        {
            goal=("gain");

        }
        else if (f>=18.5&&f<=25){
            goal=("Healthy");

        }
        else {
            goal=("weight");
        }


        intent.putExtra("Answer",finall);
          intent.putExtra("Goal", goal);
        intent.putExtra("Weight", wt);
        startActivity(intent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        Height = findViewById(R.id.editText);
        Weight = findViewById(R.id.et_weight);
        getSupportActionBar().hide();

    }
}