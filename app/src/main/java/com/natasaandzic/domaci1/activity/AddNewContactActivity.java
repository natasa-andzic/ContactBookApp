package com.natasaandzic.domaci1.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.natasaandzic.domaci1.R;
import com.natasaandzic.domaci1.db.ContactsDbHelper;

public class AddNewContactActivity extends AppCompatActivity {

    private Button saveButton;
    private EditText nameEt;
    private EditText surnameEt;
    private EditText numberEt;
    private EditText emailEt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);

        nameEt = findViewById(R.id.et_add_contact_name);
        surnameEt = findViewById(R.id.et_add_contact_surname);
        numberEt = findViewById(R.id.et_add_contact_number);
        emailEt = findViewById(R.id.et_add_contact_email);

        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameEt.getText().toString().trim();
                String surname = surnameEt.getText().toString().trim();
                String number = numberEt.getText().toString().trim();
                String email = emailEt.getText().toString().trim();

                ContactsDbHelper.getInstance(AddNewContactActivity.this).addContact(name, surname, number, email);

                Intent i = new Intent();
                setResult(Activity.RESULT_OK, i);
                finish();
            }
        });
    }
}
