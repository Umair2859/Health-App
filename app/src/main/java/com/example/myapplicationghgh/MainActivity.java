package com.example.myapplicationghgh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    ImageView iv;
    FirebaseAuth auth;
    EditText loginem;
    EditText loginpass;
    Button button;
    String em,  ps;
    public void view(){
        auth= FirebaseAuth.getInstance();
        loginem=findViewById(R.id.loem);
        loginpass=findViewById(R.id.lopass);
        button = findViewById(R.id.sv);

    }
    public void enter(View view) {
        em = loginem.getText().toString();
        ps = loginpass.getText().toString();
        if (em.isEmpty()) {
            loginem.setError("Enter an email");
            loginem.requestFocus();
        }
        if (ps.isEmpty()) {
            loginpass.setError("Enter password");
            loginpass.requestFocus();
        } else {
            auth.signInWithEmailAndPassword(loginem.getText().toString().trim(), loginpass.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(MainActivity.this, Dashboard.class);
                        startActivity(intent);


                    } else {
                        Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }

    }

public void next(View view ){
        Intent intent= new Intent(MainActivity.this, signup.class);
        startActivity(intent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        iv = findViewById(R.id.iv);
        iv.animate().alpha(1).setDuration(3000);
view();
    }
}