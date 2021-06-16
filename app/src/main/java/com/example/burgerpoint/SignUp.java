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
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    private TextView tx;
    private Button btn;
    private EditText et_name,et_email,et_pwd,et_c_pwd;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        btn=(Button)findViewById(R.id.btn_SignUp);
        tx=(TextView)findViewById(R.id.textView2);
        et_name=(EditText)findViewById(R.id.et_name);
        et_email=(EditText)findViewById(R.id.et_email);
        et_pwd=(EditText)findViewById(R.id.et_Password);
        et_c_pwd=(EditText)findViewById(R.id.et_c_Password);

        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(it);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btSignup();

            }
        });

    }

    private void btSignup() {
        final String email =et_email.getText().toString().trim();
        final String name = et_name.getText().toString().trim();
        final String pwd = et_pwd.getText().toString().trim();
        String c_pwd = et_c_pwd.getText().toString().trim();

        if (email.isEmpty()) {
            et_email.setError("Email is Required");
            et_email.requestFocus();
            return;

        }

        if (name.isEmpty()) {
            et_name.setError("Number is Required");
            et_name.requestFocus();
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

        if (c_pwd.isEmpty()) {
            et_c_pwd.setError("Confrim Password is Required");
            et_c_pwd.requestFocus();
            return;
        }
        if (!c_pwd.equals(pwd)) {
            et_c_pwd.setError("Confrim Password not matches");
            et_c_pwd.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(name,email,pwd);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(SignUp.this, "User has been registered Sucessfully", Toast.LENGTH_LONG).show();
                                        Intent it =new Intent(SignUp.this,Menu.class);
                                        startActivity(it);
                                    }else{
                                        Toast.makeText(SignUp.this, "Failed to register! Try Again", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(SignUp.this, "Failed to register", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}



