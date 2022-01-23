package com.example.imp_project_for_life;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class UserSignUp extends AppCompatActivity {


    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);
        Intent g1=getIntent();
        final EditText sign_upEmail=findViewById(R.id.SignUp_email);
        final EditText sign_Main_Password=findViewById(R.id.Signup_M_pas);
        final EditText sign_Confirm_Password=findViewById(R.id.Sign_up_C_pass);
        final Button clicksingup=findViewById(R.id.Sign_UP_btn);
        final TextView AlreadAccount=findViewById(R.id.Already_Account);

        clicksingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth = FirebaseAuth.getInstance();
                String email = sign_upEmail.getText().toString().trim();
                String password = sign_Main_Password.getText().toString().trim();
                String confirmPass=sign_Confirm_Password.getText().toString().trim();


                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
            if(password.equals(confirmPass)){
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(UserSignUp.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(UserSignUp.this, "createUserWithEmail:onComplete:" +task.isSuccessful(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
            else
                {
                    Toast.makeText(UserSignUp.this, "Both Password Dont Match ", Toast.LENGTH_SHORT).show();
                }

            }
        });
        AlreadAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(UserSignUp.this,UserLogin.class);
                startActivity(in);
            }
        });
    }
}
/////////////////////////////////////////////////
