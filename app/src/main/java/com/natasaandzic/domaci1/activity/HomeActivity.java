package com.natasaandzic.domaci1.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.natasaandzic.domaci1.R;
import com.natasaandzic.domaci1.adapter.ContactsRecyclerAdapter;
import com.natasaandzic.domaci1.callback.OnUserClickCallback;
import com.natasaandzic.domaci1.db.ContactsContract;
import com.natasaandzic.domaci1.db.ContactsDbHelper;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    public static final String EXTRA_ID = "id";
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_SURNAME = "surname";
    public static final String EXTRA_NUMBER = "number";
    public static final String EXTRA_EMAIL = "email";

    public static final int REQUEST_CODE_MANAGE_CONTACT = 1;

    private Button addNewContactBtn;

    private RecyclerView recyclerView;

    private OnUserClickCallback mOnUserClickCallback;
    private ContactsRecyclerAdapter mContactsRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        addNewContactBtn = findViewById(R.id.btn_home_add_new_contact);

        recyclerView = findViewById(R.id.rv_home_contact_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                lm.getOrientation());
        recyclerView.addItemDecoration(mDividerItemDecoration);

        mOnUserClickCallback = new UserClickCallback();

        mContactsRecyclerAdapter = new ContactsRecyclerAdapter(getCursor(), mOnUserClickCallback);
        recyclerView.setAdapter(mContactsRecyclerAdapter);


        //Pritiskom na dugme Add prelazimo na AddNewContactActivity
        addNewContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, AddNewContactActivity.class);
                startActivityForResult(i, REQUEST_CODE_MANAGE_CONTACT);
            }
        });

    }

    private Cursor getCursor() {

        SQLiteDatabase db = ContactsDbHelper.getInstance(this).getWritableDatabase();

        return db.query(
                ContactsContract.ContactEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                ContactsContract.ContactEntry.COLUMN_NAME + " ASC"
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.v(TAG, "ACTIVITY RESULT");

        if (requestCode == REQUEST_CODE_MANAGE_CONTACT) {
            Log.v(TAG, "RQ MANAGE CONTACT");
        }

        Log.v(TAG, "" + resultCode);

        if (resultCode == Activity.RESULT_OK) {
            Log.v(TAG, "RESULT JE OK");
        }

        if (requestCode == REQUEST_CODE_MANAGE_CONTACT && resultCode == Activity.RESULT_OK) {
            mContactsRecyclerAdapter.setCursor(getCursor());
        }
    }

    private class UserClickCallback implements OnUserClickCallback {

        @Override
        public void onUserClick(int userId, String name, String surname, String number, String email, int position) {

            Log.v(TAG, "name " + "surname");

            Intent intent = new Intent(HomeActivity.this, ContactDetailsActivity.class);
            intent.putExtra(HomeActivity.EXTRA_ID, userId);
            intent.putExtra(HomeActivity.EXTRA_NAME, name);
            intent.putExtra(HomeActivity.EXTRA_SURNAME, surname);
            intent.putExtra(HomeActivity.EXTRA_NUMBER, number);
            intent.putExtra(HomeActivity.EXTRA_EMAIL, email);

            startActivityForResult(intent, REQUEST_CODE_MANAGE_CONTACT);
        }
    }
}