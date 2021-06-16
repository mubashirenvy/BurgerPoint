package com.example.burgerpoint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button sign_btn,log_btn;
    private TextView tx,tx1;
    private EditText et_email,et_pwd;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();
        log_btn=(Button)findViewById(R.id.bt_SignIn);
        tx=(TextView)findViewById(R.id.textView);
        sign_btn=(Button)findViewById(R.id.bt_SignUp);
        et_email=(EditText)findViewById(R.id.et_Email);
        et_pwd=(EditText)findViewById(R.id.et_Password);


        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();

            }
        });

        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it =new Intent(getApplicationContext(),ForgotPassActivity.class);
                startActivity(it);
            }
        });

        sign_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it =new Intent(getApplicationContext(),SignUp.class);
                startActivity(it);
            }
        });

        log_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();

            }
        });
    }

    private void userLogin(){
        String email=et_email.getText().toString().trim();
        String pwd=et_pwd.getText().toString().trim();

        if (email.isEmpty()) {
            et_email.setError("Email is Required");
            et_email.requestFocus();
            return;

        }
        if (pwd.isEmpty()) {
            et_pwd.setError("Password is Required");
            et_pwd.requestFocus();
            return;
        }
        if(pwd.length() < 6){
            et_pwd.setError("Min Password length should be 6 character");
            et_pwd.requestFocus();
            return;

        }

        mAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Successfully Logged In", Toast.LENGTH_LONG).show();
                    Intent it =new Intent(getApplicationContext(),Menu.class);
                    startActivity(it);
                }
                else{
                    Toast.makeText(MainActivity.this,"Failed To LogIn, Please Check your Credentials", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}

