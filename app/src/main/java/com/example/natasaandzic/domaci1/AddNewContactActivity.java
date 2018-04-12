package com.example.natasaandzic.domaci1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

public class AddNewContactActivity extends AppCompatActivity {

    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);

        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // upisi u fajl

                //nameEditText.getText().toString().trim();
                //String name = nameEditText.getText().toString().trim();
                //cv.put(Person.COLUMN_NAME, name);
                //db.insertDataInDatabase(Person.tableName, null, cv);
                //nameEditText.getText().clear();

                // prebaci se na homeactivity
                Intent i = new Intent(AddNewContactActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });
    }
}
