package com.example.natasaandzic.domaci1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    Button addNewContact;
    Button showContact;

    private RecyclerView recyclerView;
    private TextView noContactsView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addNewContact = findViewById(R.id.addNewContact);
        showContact = findViewById(R.id.showContact);

        //Pritiskom na dugme Add prelazimo na AddNewContactActivity
        addNewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, AddNewContactActivity.class);
                startActivity(i);
            }
        });

        //Pritiskom na dugme Show prelazimo na ContactDetailsActivity
        showContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, ContactDetailsActivity.class);
                startActivity(i);
            }
        });

    } }