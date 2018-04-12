package com.natasaandzic.domaci1.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.natasaandzic.domaci1.R;

public class ContactDetailsActivity extends AppCompatActivity {

    Button delete;
    Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        delete = findViewById(R.id.deleteButton);
        edit = findViewById(R.id.editButton);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ContactDetailsActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ContactDetailsActivity.this, EditContactActivity.class);
                startActivity(i);
            }
        });
    }
}
