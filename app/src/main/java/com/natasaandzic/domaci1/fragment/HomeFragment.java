package com.natasaandzic.domaci1.fragment;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.natasaandzic.domaci1.R;
import com.natasaandzic.domaci1.activity.AddNewContactActivity;
import com.natasaandzic.domaci1.activity.ContactDetailsActivity;
import com.natasaandzic.domaci1.adapter.ContactsRecyclerAdapter;
import com.natasaandzic.domaci1.callback.OnUserClickCallback;
import com.natasaandzic.domaci1.db.ContactsContract;
import com.natasaandzic.domaci1.db.ContactsDbHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment1";

    public static final String EXTRA_ID = "id";
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_SURNAME = "surname";
    public static final String EXTRA_NUMBER = "number";
    public static final String EXTRA_EMAIL = "email";

    public static final int REQUEST_CODE_MANAGE_CONTACT = 1;

    private OnUserClickCallback mOnUserClickCallback;
    private ContactsRecyclerAdapter mContactsRecyclerAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home,
                container, false);


        Button addNewContactBtn = (Button) rootView.findViewById(R.id.btn_home_add_new_contact);

        RecyclerView recyclerView = rootView.findViewById(R.id.rv_home_contact_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(lm);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                lm.getOrientation());
        recyclerView.addItemDecoration(mDividerItemDecoration);

        mOnUserClickCallback = new HomeFragment.UserClickCallback();

        mContactsRecyclerAdapter = new ContactsRecyclerAdapter(getCursor(), mOnUserClickCallback);
        recyclerView.setAdapter(mContactsRecyclerAdapter);


        //Pritiskom na dugme Add prelazimo na AddNewContactActivity
        addNewContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AddNewContactActivity.class);
                startActivityForResult(i, REQUEST_CODE_MANAGE_CONTACT);
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }

    private Cursor getCursor() {

        SQLiteDatabase db = ContactsDbHelper.getInstance(getActivity()).getWritableDatabase();

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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

            Intent intent = new Intent(getActivity(), ContactDetailsActivity.class);
            intent.putExtra(HomeFragment.EXTRA_ID, userId);
            intent.putExtra(HomeFragment.EXTRA_NAME, name);
            intent.putExtra(HomeFragment.EXTRA_SURNAME, surname);
            intent.putExtra(HomeFragment.EXTRA_NUMBER, number);
            intent.putExtra(HomeFragment.EXTRA_EMAIL, email);

            startActivityForResult(intent, REQUEST_CODE_MANAGE_CONTACT);
        }
    }

}
