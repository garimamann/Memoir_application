package com.example.memoir.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.memoir.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser currentUser = mAuth.getCurrentUser();

        final Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                  Intent intent;
                if(currentUser!=null)
                {
                    intent  = new Intent(getApplicationContext(),SavedMemoActivity.class);


                }

                else{
                     intent = new Intent(getApplicationContext(),LoginActivity.class);


                }
                startActivity(intent);

            }
        },3000);

    }
}
