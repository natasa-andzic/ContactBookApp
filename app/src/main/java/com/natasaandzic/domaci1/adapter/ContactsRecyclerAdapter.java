package com.natasaandzic.domaci1.adapter;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.natasaandzic.domaci1.R;
import com.natasaandzic.domaci1.callback.OnUserClickCallback;
import com.natasaandzic.domaci1.db.ContactsContract;

public class ContactsRecyclerAdapter extends RecyclerView.Adapter<ContactsRecyclerAdapter.ContactHolder> {

    private static final String TAG = "ContactsRecyclerAdapter";

    private Cursor mCursor;

    //private ArrayList<Contact> mDataSet;

    private OnUserClickCallback mOnUserClickCallback;

    public ContactsRecyclerAdapter(Cursor cursor, OnUserClickCallback callback) {
        mCursor = cursor;
        mOnUserClickCallback = callback;
    }

    @Override
    public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item, parent, false);
        ContactHolder ch = new ContactHolder(view);
        return ch;
    }

    @Override
    public void onBindViewHolder(ContactHolder holder, int position) {

        if (!mCursor.moveToPosition(position)) {
            return;
        }

        holder.nameTv.setText(mCursor.getString(mCursor.getColumnIndex(ContactsContract.ContactEntry.COLUMN_NAME)));
        holder.surnameTv.setText(mCursor.getString(mCursor.getColumnIndex(ContactsContract.ContactEntry.COLUMN_SURNAME)));
    }

    public void setCursor(Cursor cursor) {

        if (mCursor != null) {
            mCursor.close();
        }

        mCursor = cursor;

        if (mCursor != null) {
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }


    public class ContactHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nameTv;
        TextView surnameTv;

        public ContactHolder(View itemView) {
            super(itemView);

            nameTv = itemView.findViewById(R.id.tv_list_name);
            surnameTv = itemView.findViewById(R.id.tv_list_surname);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Log.v(TAG, "CLICK");

            mCursor.moveToPosition(getAdapterPosition());

            int id = mCursor.getInt(mCursor.getColumnIndex(ContactsContract.ContactEntry._ID));
            String name = mCursor.getString(mCursor.getColumnIndex(ContactsContract.ContactEntry.COLUMN_NAME));
            String surname = mCursor.getString(mCursor.getColumnIndex(ContactsContract.ContactEntry.COLUMN_SURNAME));
            String number = mCursor.getString(mCursor.getColumnIndex(ContactsContract.ContactEntry.COLUMN_NUMBER));
            String email = mCursor.getString(mCursor.getColumnIndex(ContactsContract.ContactEntry.COLUMN_EMAIL));

            Log.v(TAG, v.getClass() + "");

            mOnUserClickCallback.onUserClick(
                    id,
                    name,
                    surname,
                    number,
                    email,
                    getAdapterPosition());
        }
    }
}
