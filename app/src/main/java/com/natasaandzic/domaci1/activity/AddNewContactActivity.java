package com.natasaandzic.domaci1.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.natasaandzic.domaci1.R;
import com.natasaandzic.domaci1.db.ContactsDbHelper;

public class AddNewContactActivity extends AppCompatActivity {

    private Button saveBtn;
    private Button captureBtn;
    private EditText nameEt;
    private EditText surnameEt;
    private EditText numberEt;
    private EditText emailEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        nameEt = findViewById(R.id.et_add_contact_name);
        surnameEt = findViewById(R.id.et_add_contact_surname);
        numberEt = findViewById(R.id.et_add_contact_number);
        emailEt = findViewById(R.id.et_add_contact_email);

        saveBtn = findViewById(R.id.saveButton);
        captureBtn = findViewById(R.id.captureButton);

        saveBtn.setOnClickListener(new View.OnClickListener() {
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

        captureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddNewContactActivity.this, CaptureActivity.class);
                startActivity(i);
            }
        });
    }
}
