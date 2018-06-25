package com.natasaandzic.domaci1.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.natasaandzic.domaci1.R;
import com.natasaandzic.domaci1.db.ContactsDbHelper;

public class ContactDetailsActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_EDIT_CONTACT = 1;

    public static final String EXTRA_ID = "id";
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_SURNAME = "surname";
    public static final String EXTRA_NUMBER = "number";
    public static final String EXTRA_EMAIL = "email";

    private TextView nameTv;
    private TextView surnameTv;
    private TextView numberTv;
    private TextView emailTv;

    private int id;
    private String name;
    private String surname;
    private String number;
    private String email;

    private boolean shouldUpdateUIOnResume;

    private Button deleteBtn;
    private Button editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        id = getIntent().getIntExtra(EXTRA_ID, 0);
        name = getIntent().getStringExtra(EXTRA_NAME);
        surname = getIntent().getStringExtra(EXTRA_SURNAME);
        number = getIntent().getStringExtra(EXTRA_NUMBER);
        email = getIntent().getStringExtra(EXTRA_EMAIL);

        deleteBtn = findViewById(R.id.btn_contact_details_delete);
        editBtn = findViewById(R.id.btn_contact_details_edit);

        nameTv = findViewById(R.id.tv_contact_details_name_value);
        nameTv.setText(name);
        surnameTv = findViewById(R.id.tv_contact_details_surname_value);
        surnameTv.setText(surname);
        numberTv = findViewById(R.id.tv_contact_details_phone_value);
        numberTv.setText(number);
        emailTv = findViewById(R.id.tv_contact_details_email_value);
        emailTv.setText(email);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(ContactDetailsActivity.this,HomeFragment1.class);
                //i.putExtra(ContactDetailsActivity.EXTRA_ID, id);
                ContactsDbHelper.getInstance(ContactDetailsActivity.this).deleteContact(id);
                Intent newIntent = new Intent();
                //Na ovoj liniji ne setuje request code, nego rezultat akcije to je Activity.RESULT_OK
                //setResult(REQUEST_CODE_EDIT_CONTACT, newIntent);
                setResult(Activity.RESULT_OK);
                finish();
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ContactDetailsActivity.this, EditContactActivity.class);

                intent.putExtra(ContactDetailsActivity.EXTRA_ID, id);
                intent.putExtra(ContactDetailsActivity.EXTRA_NAME, name);
                intent.putExtra(ContactDetailsActivity.EXTRA_SURNAME, surname);
                intent.putExtra(ContactDetailsActivity.EXTRA_NUMBER, number);
                intent.putExtra(ContactDetailsActivity.EXTRA_EMAIL, email);
                startActivityForResult(intent, ContactDetailsActivity.REQUEST_CODE_EDIT_CONTACT);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode != REQUEST_CODE_EDIT_CONTACT || data == null){
            return;
        }

       /* int status = data.getIntExtra(ContactDetailsActivity.EXTRA_CONTACT_EDIT_STATUS, -1);

        if (status == ContactDetailsActivity.CONTACT_EDITED){
;*/
            name = data.getStringExtra(EXTRA_NAME);
            surname = data.getStringExtra(EXTRA_SURNAME);
            number = data.getStringExtra(EXTRA_NUMBER);
            email = data.getStringExtra(EXTRA_EMAIL);

            shouldUpdateUIOnResume = true;
        }


    @Override
    protected void onResume() {
        super.onResume();

        if (shouldUpdateUIOnResume) {
            shouldUpdateUIOnResume = false;

            nameTv.setText(name);
            surnameTv.setText(surname);
            numberTv.setText(number);
            emailTv.setText(email);
        }

    }
        @Override
    public void onBackPressed() {
        //Ako pozovemo onbackpressed on ce da overriduje nas result ok sa result canceled
        //super.onBackPressed();

        //Intent i = new Intent();
        setResult(Activity.RESULT_OK);
        finish();
    }
}
