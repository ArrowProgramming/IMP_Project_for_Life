package com.example.imp_project_for_life;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Admin_login extends AppCompatActivity {
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        Intent g1=getIntent();
        final EditText Admin_login_mail=findViewById(R.id.Ad_login_Email);
        final EditText Admin_login_pass=findViewById(R.id.Ad_login_Password);
        final Button Admin_login_button=findViewById(R.id.Admin_login_btn);
        Admin_login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth = FirebaseAuth.getInstance();
                String email=Admin_login_mail.getText().toString().trim();
                String password=Admin_login_pass.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                //need to be fixed
           //     if (email=="admin1@umt.com" && password =="adminadmin" )
           //     {

          //      }
                        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(Admin_login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(Admin_login.this,"Email or Password is wrong",Toast.LENGTH_SHORT).show();
                            } else {
                                Intent intent = new Intent(Admin_login.this, MainActivity.class);
                                    startActivity(intent);

                            }
                        }

                    });

            }
        });



    }
}