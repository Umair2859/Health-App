package com.example.myapplicationghgh;

import static android.hardware.SensorManager.SENSOR_DELAY_NORMAL;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class sensor extends AppCompatActivity implements SensorEventListener {
TextView stepcounter;
private SensorManager sensorManager;
private Sensor sensor;
boolean iscounteron;
int count=0;
String textfv,ab;
TextView previous;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){ //ask for permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 0);
            }
        }
previous=findViewById(R.id.pre);
        stepcounter = findViewById(R.id.count);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        SharedPreferences getsharedPrefernces= getSharedPreferences("MySharedPref", MODE_PRIVATE);
        ab= getsharedPrefernces.getString("goal1", " ");
        previous.setText(ab);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {

            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            iscounteron = true;


        } else {
            stepcounter.setText("not present");
            iscounteron = false;

        }

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
if(sensorEvent.sensor==sensor){
count=(int) sensorEvent.values[0];
stepcounter.setText(String.valueOf(count));
   textfv=stepcounter.getText().toString();
    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
    SharedPreferences.Editor myEdit = sharedPreferences.edit();
    myEdit.putString("goal1", textfv);
    myEdit.apply();

}
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null){
            sensorManager.unregisterListener(this,sensor);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {

           sensorManager.registerListener(this,sensor, SensorManager.SENSOR_DELAY_NORMAL);

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


}