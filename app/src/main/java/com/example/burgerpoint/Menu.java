package com.example.burgerpoint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

    private ListView lv;
    private String name[]={"Andy wala bunkabab Burger","Andywala burger","Burger with chips","Special shami burger"};
    private String price[]={"100","120","180","200"};
    private int img[]={R.drawable.andy_wala_bun_kabab,R.drawable.andywala_burger,R.drawable.burger_with_chips,R.drawable.special_shami_burger};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        getSupportActionBar().hide();

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {

            actionBar.setDisplayShowHomeEnabled(true);

        }
        actionBar.setTitle("Burger List");

        lv=(ListView)findViewById(R.id.lv);

        MyAdapter adptr=new MyAdapter(this,name,price,img);
        lv.setAdapter((ListAdapter) adptr);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    Intent intent = new Intent(getApplicationContext(), Burgers.class);
                    // this intent put our 0 index image to another activity
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", img[0]);
                    intent.putExtras(bundle);
                    // now put title and description to another activity
                    intent.putExtra("title", name[0]);
                    intent.putExtra("description", price[0]);
                    // also put your position
                    intent.putExtra("position", "" + 0);
                    startActivity(intent);


                }
                if (position == 1) {
                    Intent intent = new Intent(getApplicationContext(), Burgers.class);
                    // this intent put our 0 index image to another activity
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", img[1]);
                    intent.putExtras(bundle);
                    // now put title and description to another activity
                    intent.putExtra("title", name[1]);
                    intent.putExtra("description", price[1]);
                    // also put your position
                    intent.putExtra("position", "" + 1);
                    startActivity(intent);
                }
                if (position == 2) {
                    Intent intent = new Intent(getApplicationContext(), Burgers.class);
                    // this intent put our 0 index image to another activity
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", img[2]);
                    intent.putExtras(bundle);
                    // now put title and description to another activity
                    intent.putExtra("title", name[2]);
                    intent.putExtra("description", price[2]);
                    // also put your position
                    intent.putExtra("position", "" + 2);
                    startActivity(intent);
                }
                if (position == 3) {
                    Intent intent = new Intent(getApplicationContext(), Burgers.class);
                    // this intent put our 0 index image to another activity
                    Bundle bundle = new Bundle();
                    bundle.putInt("image", img[3]);
                    intent.putExtras(bundle);
                    // now put title and description to another activity
                    intent.putExtra("title", name[3]);
                    intent.putExtra("description", price[3]);
                    // also put your position
                    intent.putExtra("position", "" + 3);
                    startActivity(intent);
                }


            }});
    }

    class MyAdapter extends ArrayAdapter<String>
    {
        Context context;
        String n[],p[];
        int i[];

        MyAdapter(Context c,String na[],String pr[],int im[])
        {
            super(c,R.layout.row,R.id.rt_name,na);

            this.context=c;
            this.i=im;
            this.n=na;
            this.p=pr;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row =inflater.inflate(R.layout.row,parent,false);

            ImageView img=row.findViewById(R.id.rt_image);
            TextView name=row.findViewById(R.id.rt_name);
            TextView price=row.findViewById(R.id.rt_price);

            img.setImageResource(i[position]);
            name.setText(n[position]);
            price.setText(p[position]);
            return  row;
        }
    }
}

