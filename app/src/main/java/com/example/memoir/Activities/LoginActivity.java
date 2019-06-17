package com.example.memoir.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.memoir.R;
import com.example.memoir.Utils.ToastUtils;
import com.example.memoir.Utils.ValidationsUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEmail,mPassword;
    private Button mLoginButton;
    private TextView mRegisterButton;
    private ValidationsUtils mValidationUtil;
    private ToastUtils mToastUtil;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mValidationUtil = ValidationsUtils.getInstance();
        mToastUtil = ToastUtils.getInstance();

        mAuth = FirebaseAuth.getInstance();

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
        String email,passValue;
        email = mEmail.getText().toString().trim();
        passValue = mPassword.getText().toString().trim();

        if(mValidationUtil.isEmptyOrNull(email) || mValidationUtil.isEmptyOrNull(passValue)){
            mToastUtil.toast(getApplicationContext(),"Fileds cannot be left empty");
            if(mValidationUtil.isEmptyOrNull(email)){
                //email files highlight
            }
            if(mValidationUtil.isEmptyOrNull(passValue)){
                //pass filed highlight
            }
        }

       else{
           signIn(email,passValue);
        }


    }

    private void signIn(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("LoginActivity", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(getApplicationContext(),SpeechToMemoActivity.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("LoginActivity", "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Invalid Credentials",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });

    }


}
