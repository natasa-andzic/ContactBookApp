package com.example.natasaandzic.domaci1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by natasaandzic on 124//18.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder> {

    private Context context;
    private List<Person> contactsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView surname;
        public TextView phone;
        public TextView email;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.nameEditText);
            surname = view.findViewById(R.id.lastNameEditText);
            phone = view.findViewById(R.id.phoneEditText);
            email = view.findViewById(R.id.emailEditText);
        }
    }

    public ContactsAdapter(Context context, List<Person> contactsList) {
        this.context = context;
        this.contactsList = contactsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_home_text_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Person person = contactsList.get(position);

        holder.name.setText(person.getName());
        holder.surname.setText(person.getSurname());
        holder.phone.setText(person.getPhone());
        holder.email.setText(person.getEmail());

    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

}
