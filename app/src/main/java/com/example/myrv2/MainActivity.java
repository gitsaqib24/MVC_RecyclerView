package com.example.myrv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.service.controls.actions.FloatAction;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    myContactAdapter adapter;
    RecyclerView myRecyclerView;
    ArrayList<contact_Model> myArr;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myArr = new ArrayList<>();
        fab= findViewById(R.id.fcb);

        myRecyclerView = findViewById(R.id.myRecyclerViewid);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myArr.add(new contact_Model(R.drawable.a1,"Saqib","03039717635"));
        myArr.add(new contact_Model(R.drawable.a1,"Kashif","03457532665"));
        myArr.add(new contact_Model(R.drawable.a1,"Asif","03039717635"));
        myArr.add(new contact_Model(R.drawable.a1,"Latif","03457532665"));
        myArr.add(new contact_Model(R.drawable.a1,"Ahmad","03039717635"));
        myArr.add(new contact_Model(R.drawable.a1,"Alisher","03457532665"));
        myArr.add(new contact_Model(R.drawable.a1,"Sohail","03039717635"));
        myArr.add(new contact_Model(R.drawable.a1,"Arif","03457532665"));
        myArr.add(new contact_Model(R.drawable.a1,"Gul Rehman","03039717635"));
        myArr.add(new contact_Model(R.drawable.a1,"Abad","03457532665"));
        myArr.add(new contact_Model(R.drawable.a1,"Dilshad","03039717635"));
        myArr.add(new contact_Model(R.drawable.a1,"Ameer Hamza","03457532665"));



        adapter = new myContactAdapter(this,myArr);
        myRecyclerView.setAdapter(adapter);






        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.addlayout);
                EditText txt1,txt2;
                txt1 = dialog.findViewById(R.id.dname);
                txt2 = dialog.findViewById(R.id.dnumber);
                Button btn1 = dialog.findViewById(R.id.dbtn);
                dialog.show();
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int img;
                        String name ="";
                        String number="";
                        img = R.drawable.a1;
                        name= txt1.getText().toString();
                        number = txt2.getText().toString();
                        if(!name.equals("") && !number.equals("") )
                        {
                            myArr.add(new contact_Model(img,name,number));
                            adapter.notifyItemInserted(myArr.size()-1);
                            myRecyclerView.scrollToPosition(myArr.size()-1);
                            dialog.dismiss();
                        }else {
                            Toast.makeText(MainActivity.this, "Please Enter Name and Number", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}