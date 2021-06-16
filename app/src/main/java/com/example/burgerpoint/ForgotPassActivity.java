package com.example.burgerpoint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassActivity extends AppCompatActivity {

    private Button btn;
    private EditText et_email;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();

        et_email=(EditText)findViewById(R.id.editTextTextEmailAddress2);

        btn=findViewById(R.id.btSignUp);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_email.getText().toString().trim();
                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ForgotPassActivity.this, "Password Reset Link send to your Email", Toast.LENGTH_LONG).show();
                            Intent it = new Intent(ForgotPassActivity.this, MainActivity.class);
                            startActivity(it);
                        } else {
                            Toast.makeText(ForgotPassActivity.this, "Error Occurred! while Resetting Password ", Toast.LENGTH_LONG).show();
                        }
                    }});

            }
        });
    }
}