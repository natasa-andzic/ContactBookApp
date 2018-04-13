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

    private int id;
    private String name;
    private String surname;
    private String number;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        id = getIntent().getIntExtra(ContactDetailsActivity.EXTRA_ID, 0);
        name = getIntent().getStringExtra(ContactDetailsActivity.EXTRA_NAME);
        surname = getIntent().getStringExtra(ContactDetailsActivity.EXTRA_SURNAME);
        number = getIntent().getStringExtra(ContactDetailsActivity.EXTRA_NUMBER);
        email = getIntent().getStringExtra(ContactDetailsActivity.EXTRA_EMAIL);

        saveBtn = findViewById(R.id.btn_edit_contact_save);

        nameEt = findViewById(R.id.et_add_contact_name);
        nameEt.setText(name);
        surnameEt = findViewById(R.id.et_add_contact_surname);
        surnameEt.setText(surname);
        numberEt = findViewById(R.id.et_add_contact_number);
        numberEt.setText(number);
        emailEt = findViewById(R.id.et_add_contact_email);
        emailEt.setText(email);


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = nameEt.getText().toString().trim();
                surname = surnameEt.getText().toString().trim();
                number = numberEt.getText().toString().trim();
                email = emailEt.getText().toString().trim();

                ContactsDbHelper.getInstance(EditContactActivity.this).
                        updateContact(id, name, surname, number, email);

                Intent i = new Intent(EditContactActivity.this, ContactDetailsActivity.class);
                i.putExtra(ContactDetailsActivity.EXTRA_ID, id);
                i.putExtra(ContactDetailsActivity.EXTRA_NAME, name);
                i.putExtra(ContactDetailsActivity.EXTRA_SURNAME, surname);
                i.putExtra(ContactDetailsActivity.EXTRA_NUMBER, number);
                i.putExtra(ContactDetailsActivity.EXTRA_EMAIL, email);
                setResult(Activity.RESULT_OK, i);
                finish();

            }
        });

    }
}
