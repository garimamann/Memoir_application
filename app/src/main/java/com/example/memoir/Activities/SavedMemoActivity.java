package com.example.memoir.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.memoir.R;

public class SavedMemoActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mLogOutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_memo);

        mLogOutButton =(Button)findViewById(R.id.activity_saved_memo_logout_button);

        mLogOutButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_saved_memo_logout_button:
                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);

        }
    }
}
