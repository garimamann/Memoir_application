package com.example.memoir.Activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.memoir.R;
import com.example.memoir.Utils.ToastUtils;
import com.example.memoir.Utils.ValidationsUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private EditText mFirstName,mLastName,mEmail,mPassword,mConfirmPassword;
    private Button mRegisterButton;
    private ImageView mProfileImage;
    private ValidationsUtils mValidationsUtils;
    private ToastUtils mToastUtils;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Initializing utils
        mValidationsUtils = ValidationsUtils.getInstance();

        //Initializing widgets
        mFirstName = (EditText) findViewById(R.id.activity_register_first_name_value);
        mLastName = (EditText) findViewById(R.id.activity_register_last_name_value);
        mEmail = (EditText) findViewById(R.id.activity_register_email_value);
        mPassword = (EditText) findViewById(R.id.activity_register_password_value);
        mConfirmPassword = (EditText) findViewById(R.id.activity_register_confirm_password_value);
        mRegisterButton = (Button) findViewById(R.id.activity_register_register_button);
        mProfileImage = (ImageView) findViewById(R.id.activity_register_profile_image_value);


        mAuth = FirebaseAuth.getInstance();


        mRegisterButton.setOnClickListener(this);




    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_register_register_button:
                mValidateFileds();


        }
    }

    private void mValidateFileds() {
        String firstNameValue, lastNameValue, emailValue, passValue, confirmPasswordValue;
        emailValue = mEmail.getText().toString().trim();
        passValue = mPassword.getText().toString().trim();
        firstNameValue = mFirstName.getText().toString().trim();
        lastNameValue = mLastName.getText().toString().trim();
        confirmPasswordValue = mConfirmPassword.getText().toString().trim();

        if (mValidationsUtils.isEmptyOrNull(emailValue) || mValidationsUtils.isEmptyOrNull(firstNameValue) || mValidationsUtils.isEmptyOrNull(lastNameValue) || mValidationsUtils.isEmptyOrNull(passValue) || mValidationsUtils.isEmptyOrNull(confirmPasswordValue)) {
            mToastUtils.toast(getApplicationContext(), "Fields left Empty");
            if (mValidationsUtils.isEmptyOrNull(emailValue)) {
                mToastUtils.toast(getApplicationContext(), "Eamil value empty");
            }
            if (mValidationsUtils.isEmptyOrNull(firstNameValue)) {
                mToastUtils.toast(getApplicationContext(), "First name value empty");
            }
            if (mValidationsUtils.isEmptyOrNull(lastNameValue)) {
                mToastUtils.toast(getApplicationContext(), "Last Name value empty");
            }
            if (mValidationsUtils.isEmptyOrNull(passValue)) {
                mToastUtils.toast(getApplicationContext(), "Password value value empty");
            }
            if (mValidationsUtils.isEmptyOrNull(confirmPasswordValue)) {
                mToastUtils.toast(getApplicationContext(), "Confirm password value empty");
            }
        } else if (!mValidationsUtils.isEmail(emailValue)) {
            mToastUtils.toast(getApplicationContext(), "Invalid Email");
        } else if (!mValidationsUtils.isPasswordLenghtValid(passValue, 6)) {
            mToastUtils.toast(getApplicationContext(), "Invalid Password");
        } else {

            performRegistration(emailValue,passValue);


        }
    }

    private void performRegistration(final String email, final String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            mToastUtils.toast(getApplicationContext(),"Registeration succesful");
                            mSignInUser( email ,password);

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "Registeration failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });

    }


    private void mSignInUser(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            mToastUtils.toast(getApplicationContext(),"SignIn succesful");

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(getApplicationContext(), "SignIn failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });



    }




}

