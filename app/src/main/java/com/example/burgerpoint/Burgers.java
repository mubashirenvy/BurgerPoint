package com.example.burgerpoint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Burgers extends AppCompatActivity {

    ImageView imageView;
    TextView title, description,increment,decrement,quantity;
    Button btn;
    int count=1;
    private FirebaseAuth mAuth;

    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burgers);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            // aslo set in menifest
        }

        imageView =(ImageView) findViewById(R.id.vh_img);
        title =(TextView) findViewById(R.id.vh_name);
        description =(TextView) findViewById(R.id.vh_price);
        increment=(TextView) findViewById(R.id.increment);
        decrement=(TextView) findViewById(R.id.decrement);
        quantity=(TextView) findViewById(R.id.days);
        btn=(Button) findViewById(R.id.vh_btn);
        mAuth = FirebaseAuth.getInstance();


        if (position == 0) {
            Intent intent = getIntent();

            Bundle bundle = this.getIntent().getExtras();
            int pic = bundle.getInt("image");
            String aTitle = intent.getStringExtra("title");
            String aDescription = intent.getStringExtra("description");

            imageView.setImageResource(pic);
            title.setText(aTitle);
            description.setText(aDescription);

            actionBar.setTitle(aTitle);
        }

        if (position == 1) {
            Intent intent = getIntent();

            Bundle bundle = this.getIntent().getExtras();
            int pic = bundle.getInt("image");
            String aTitle = intent.getStringExtra("title");
            String aDescription = intent.getStringExtra("description");

            imageView.setImageResource(pic);
            title.setText(aTitle);
            description.setText(aDescription);

            actionBar.setTitle(aTitle);
        }

        if (position == 2) {
            Intent intent = getIntent();

            Bundle bundle = this.getIntent().getExtras();
            int pic = bundle.getInt("image");
            String aTitle = intent.getStringExtra("title");
            String aDescription = intent.getStringExtra("description");

            imageView.setImageResource(pic);
            title.setText(aTitle);
            description.setText(aDescription);

            actionBar.setTitle(aTitle);
        }

        if (position == 3) {
            Intent intent = getIntent();

            Bundle bundle = this.getIntent().getExtras();
            int pic = bundle.getInt("image");
            String aTitle = intent.getStringExtra("title");
            String aDescription = intent.getStringExtra("description");

            imageView.setImageResource(pic);
            title.setText(aTitle);
            description.setText(aDescription);

            actionBar.setTitle(aTitle);
        }

        if (position == 4) {
            Intent intent = getIntent();

            Bundle bundle = this.getIntent().getExtras();
            int pic = bundle.getInt("image");
            String aTitle = intent.getStringExtra("title");
            String aDescription = intent.getStringExtra("description");

            imageView.setImageResource(pic);
            title.setText(aTitle);
            description.setText(aDescription);

            actionBar.setTitle(aTitle);
        }

        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                quantity.setText(""+count);

            }
        });

        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(count==1) {
                    quantity.setText("1");

                }
                else {
                    count--;
                    quantity.setText("" + count);
                }

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name =title.getText().toString().trim();
                String price_st =description.getText().toString().trim();
                String qty =quantity.getText().toString().trim();

                int price=Integer.parseInt(price_st);
                final int qtys=Integer.parseInt(qty);

                final int total_price= price;

                AlertDialog.Builder alert = new AlertDialog.Builder(Burgers.this);
                alert.setTitle("Confirm Your Order");
                alert.setMessage("Burger name: " +name+ " \n Quantity: "+quantity+" \n Total Price: " +total_price);


                alert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Order order = new Order(name,qtys,total_price);
                        String id;
                        id =FirebaseAuth.getInstance().getCurrentUser().getUid();
                        FirebaseDatabase.getInstance().getReference("Users/"+id+"/Orders")
                                .setValue(order).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(), "You have Sucessfully ordered a Burger", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getApplicationContext(), Menu.class);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(getApplicationContext(), "Something went wrong! Try Again", Toast.LENGTH_LONG).show();
                                }
                            }
                        });


                    }
                });

                alert.show();
            }
        });

    }



}


