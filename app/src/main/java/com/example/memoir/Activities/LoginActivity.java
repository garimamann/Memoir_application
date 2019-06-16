package com.example.memoir.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.memoir.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEmail,mPassword;
    private Button mLoginButton;
    private TextView mRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = (EditText)findViewById(R.id.activity_login_id);
        mPassword = (EditText)findViewById(R.id.activity_login_password);
        mLoginButton= (Button)findViewById(R.id.activity_login_submit_button);
        mRegisterButton=(TextView)findViewById(R.id.activity_login_register_text_button);

        mLoginButton.setOnClickListener(this);
        mRegisterButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.activity_login_submit_button:
                getValidation();

                break;


            case R.id.activity_login_register_text_button:
                Intent intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);





                break;


        }

    }

    private void getValidation() {


    }


}
