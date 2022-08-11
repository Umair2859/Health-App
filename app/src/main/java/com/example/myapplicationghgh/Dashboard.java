package com.example.myapplicationghgh;

import static com.google.android.gms.tasks.Tasks.await;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;
import com.google.protobuf.Empty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dashboard extends AppCompatActivity {
   TextView a,b,c,d;
   FirebaseDatabase firebaseDatabase;
   FirebaseAuth firebaseAuth;
   ArrayList data = new ArrayList<>();
public void tttt(View view){

    SharedPreferences sharedPreferences= getSharedPreferences("DietPlan",MODE_PRIVATE );
    boolean isStarted = sharedPreferences.getBoolean("isStarted",true);
    if(isStarted)
    {
        FirebaseFirestore.getInstance().collection("User").document(FirebaseAuth.getInstance().getUid()).collection("food_list").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                int i = 0;
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Map<String,Object> food = document.getData();
                        data.add(i,food);
                        i++;

                    }
                    Log.d("Data",data.toString());
                    Intent intenttt = new Intent(Dashboard.this, recyclermain.class);
                    intenttt.putParcelableArrayListExtra("food_list",data);
                    startActivity(intenttt);
                }
                else{
                    Log.d("error From DashBoard",task.getException().toString());
                }
            }
        });
    }else{
        Intent intent = new Intent(Dashboard.this, khali.class);
        startActivity(intent);
    }




}
   public void tt(View view){



   }
public void intentt(View view){
    Intent intent = new Intent(Dashboard.this, sensor.class);
    startActivity(intent);
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
       getSupportActionBar().hide();
        a=findViewById(R.id.name);
        c=findViewById(R.id.bmi);
        d=findViewById(R.id.dis);
        b=findViewById(R.id.textView20a);
        firebaseAuth = FirebaseAuth.getInstance();
   firebaseDatabase= FirebaseDatabase.getInstance();
        FirebaseFirestore.getInstance().collection("User").document(FirebaseAuth.getInstance().getUid()).addSnapshotListener(new EventListener<DocumentSnapshot>(){
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    Map<String,Object> data = snapshot.getData();

                    float f = Float.parseFloat(data.get("bmi").toString());
                    float roundOff = Math. round(f*100)/100;
                    String finall= String.valueOf(roundOff);

                    a.setText(data.get("name").toString());
                    c.setText(finall);
                    d.setText(data.get("disease").toString());


                    if (f<18.5)
                    {
                        b.setText("Underweight");

                    }
                    else if (f>=18.5&&f<=25){
                        b.setText("Healthy");

                    }
                    else {
                        b.setText("Overweight");
                    }

                } else {
                    Toast.makeText(Dashboard.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                }
            }

        });



}


}