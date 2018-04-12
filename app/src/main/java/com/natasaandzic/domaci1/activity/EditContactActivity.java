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

public class EditContactActivity extends AppCompatActivity {

    Button saveBtn;
    private EditText nameEt;
    private EditText surnameEt;
    private EditText numberEt;
    private EditText emailEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        saveBtn = findViewById(R.id.btn_edit_contact_save);
        nameEt = findViewById(R.id.et_add_contact_name);
        surnameEt = findViewById(R.id.et_add_contact_surname);
        numberEt = findViewById(R.id.et_add_contact_number);
        emailEt = findViewById(R.id.et_add_contact_email);


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameEt.getText().toString().trim();
                String surname = surnameEt.getText().toString().trim();
                String number = numberEt.getText().toString().trim();
                String email = emailEt.getText().toString().trim();

                ContactsDbHelper.getInstance(EditContactActivity.this).updateContact(id, name, surname, number, email);

                Intent i = new Intent();
                setResult(Activity.RESULT_OK, i);
                finish();
            }
        });

    }
}
