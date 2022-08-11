package com.example.myapplicationghgh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class signup extends AppCompatActivity {
    ImageView img;
    CheckBox Diabetes, Heart, HighBlood,LowBlood;
    AppCompatButton sign;
    TextView answer, goall;
    EditText Email;
    EditText Password;
    EditText Name;
    EditText etAge;
    FirebaseDatabase database;
    FirebaseAuth auth;
    String disease;
    String strEtPassword, strEtbmi, strEtEmail, Age, fullname;
    String weight;


    public void taket(View view) {

        Intent intent = new Intent(getApplicationContext(), bmi.class);
        startActivity(intent);
    }

    public void view() {
        img = findViewById(R.id.img);
        img.animate().alpha(1).setDuration(2000);
        Diabetes = findViewById(R.id.checkBox);
        Heart = findViewById(R.id.checkBox2);
        HighBlood = findViewById(R.id.high);
        LowBlood=findViewById(R.id.low);
        sign = findViewById(R.id.sign);
        answer = findViewById(R.id.bmians);
        Email = findViewById(R.id.loem);
        Password = findViewById(R.id.lopass);
        Name = findViewById(R.id.name1);
        etAge = findViewById(R.id.et_age);
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        goall=findViewById(R.id.gl);
    }

    public void save(View view) {
        if(Diabetes.isChecked()){
            disease=  Diabetes.getText().toString();
        }
        else if(Heart.isChecked()){
            disease= Heart.getText().toString();
        }
        else if (HighBlood.isChecked()){
            disease= HighBlood.getText().toString();

        }
        else if (LowBlood.isChecked()){
            disease= LowBlood.getText().toString();

        }
        else {

            disease=" ";
        }
        strEtbmi= answer.getText().toString();
        strEtEmail = Email.getText().toString();
        strEtPassword = Password.getText().toString();
        fullname = Name.getText().toString();
        Age = etAge.getText().toString();
        if (strEtbmi.isEmpty()){
            answer.setError("Get your bmi first");
        answer.requestFocus();}
        if (fullname.isEmpty()) {
            Name.setError("Please enter your name");
            Name.requestFocus();
        }
        if (strEtEmail.isEmpty()) {
            Email.setError("Enter an email");
            Email.requestFocus();
        }

        if (strEtPassword.isEmpty() || strEtPassword.length() < 6) {
            Password.setError("Enter password of atleast 6 characters");
            Password.requestFocus();

        }

        if (Age.isEmpty()) {
            etAge.setError("Enter age");
            etAge.requestFocus();


        }

        if (!Patterns.EMAIL_ADDRESS.matcher(strEtEmail).matches()) {
            Email.setError("Enter valid email");
            Email.requestFocus();
        } else {

            SharedPreferences sharedPreferences= getSharedPreferences("DietPlan",MODE_PRIVATE );
            sharedPreferences.edit().putBoolean("isStarted", false).commit();
            auth.getInstance().createUserWithEmailAndPassword(Email.getText().toString(), Password.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String userid = task.getResult().getUser().getUid();
                                Map<String,Object> data = new HashMap<>();

                                data.put("name",Name.getText().toString());
                                data.put("age",etAge.getText().toString());
                                data.put("email",Email.getText().toString());
                                data.put("pass",Password.getText().toString());
                                data.put("bmi",answer.getText().toString());
                                data.put("uid",userid);
                                data.put("disease",disease);
                                data.put("weight",weight);

                                Map<String,Object> data2 = new HashMap<>();
                                data2.put("goal",goall.getText().toString());
                                data2.put("weight",weight);
                                data2.put("disease",disease);
                                data2.put("uid",userid);
                                FirebaseFirestore.getInstance().collection("User").document(userid).set(data);
                                FirebaseFirestore.getInstance().collection("pending").document(userid).set(data2);



                                Toast.makeText(signup.this, "User has been registered successfuly", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(signup.this, Dashboard.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(signup.this, "Email is already taken", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();
        view();
        Intent intent = getIntent();
        String ans = intent.getStringExtra("Answer");
        String gol= intent.getStringExtra("Goal");
         weight= intent.getStringExtra("Weight");
        answer.setText(ans);
        goall.setText(gol);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

    }
}