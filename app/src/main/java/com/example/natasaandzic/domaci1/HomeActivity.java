package com.example.natasaandzic.domaci1;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;

public class HomeActivity extends AppCompatActivity {

    private ContactsAdapter myAdapter;
    private List<Person> contactsList = new ArrayList<Person>();
    Button addNewContact;

    private RecyclerView recyclerView;
    private TextView noContactsView;

    private Database db;

   // String[] peopleArray;
   // List<String> peopleList = new ArrayList<String>();

  //  String line = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addNewContact = findViewById(R.id.addNewContact);
        recyclerView = findViewById(R.id.recycler_view);
        noContactsView = findViewById(R.id.noContactsView);

        db = new Database(this);

        contactsList.addAll(db.getAllContacts());

        addNewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, AddNewContactActivity.class);
                startActivity(i);
            }
        });

        // ako imamo textview za svaki item u listi
      /*  contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, EditContactActivity.class);
                startActivity(i);
            }
        });*/


        myAdapter = new ContactsAdapter(this, contactsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(myAdapter);

        toggleEmptyNotes();

        /**
         * On long press on RecyclerView item, open alert dialog
         * with options to choose
         * Edit and Delete
         * */
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    /**
     * Inserting new note in db
     * and refreshing the list
     */
    private void createNewContact(String name, String surname, String phone, String email) {
        // inserting note in db and getting
        // newly inserted note id
        long id = db.insertDataInDatabase(name, surname, phone, email);

        // get the newly inserted note from db
        Person person = db.getPerson(id);

        if (person != null) {
            // adding new note to array list at 0 position
            contactsList.add(0, person);

            // refreshing the list
            myAdapter.notifyDataSetChanged();

            toggleEmptyNotes();
        }
    }

    /**
     * Updating note in db and updating
     * item in the list by its position
     */
    private void updateContact(String name, String surname, String phone, String email, int position) {
        Person person = contactsList.get(position);
        // updating note text
        person.setName(name);
        person.setSurname(surname);
        person.setPhone(phone);
        person.setEmail(email);

        // updating note in db
        db.updateContact(person);

        // refreshing the list
        contactsList.set(position, person);
        myAdapter.notifyItemChanged(position);

        toggleEmptyNotes();
    }

    /**
     * Deleting note from SQLite and removing the
     * item from the list by its position
     */
    private void deleteContact(int position) {
        // deleting the note from db
        db.deleteContact(contactsList.get(position));

        // removing the note from the list
        contactsList.remove(position);
        myAdapter.notifyItemRemoved(position);

        toggleEmptyNotes();
    }


    /**
     * Toggling list and empty notes view
     */
    private void toggleEmptyNotes() {
        // you can check notesList.size() > 0

        if (db.getNotesCount() > 0) {
            noContactsView.setVisibility(View.GONE);
        } else {
            noContactsView.setVisibility(View.VISIBLE);
        }
    }

        /*try {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getAssets().open("text.txt")));
            while((line = bufferedReader.readLine()) != null) {
                peopleList.add(line);
            }

            peopleArray = new String[peopleList.size()];
            peopleList.toArray(peopleArray);

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.contact_list_item, R.id.textView,peopleArray);
            listView.setAdapter(arrayAdapter);

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file");
        }
        catch(IOException ex) {
            System.out.println("Error reading file");
        }
    }*/

        private void prepareData() {
            Person p1 = new Person(1, "Natasa", "Andzic", "111222333", "email.email.com");
            contactsList.add(p1);
            Person p2 = new Person(2, "Natasa", "Andzic", "111222333", "email.email.com");
            contactsList.add(p2);
            Person p3 = new Person(3, "Natasa", "Andzic", "111222333", "email.email.com");
            contactsList.add(p3);
            Person p4 = new Person(4, "Natasa", "Andzic", "111222333", "email.email.com");
            contactsList.add(p4);
            Person p5 = new Person(5, "Natasa", "Andzic", "111222333", "email.email.com");
            contactsList.add(p5);
            Person p6 = new Person(6, "Natasa", "Andzic", "111222333", "email.email.com");
            contactsList.add(p6);
            Person p7 = new Person(7, "Natasa", "Andzic", "111222333", "email.email.com");
            contactsList.add(p7);
            Person p8 = new Person(8, "Natasa", "Andzic", "111222333", "email.email.com");
            contactsList.add(p8);
            Person p9 = new Person(9, "Natasa", "Andzic", "111222333", "email.email.com");
            contactsList.add(p9);
            Person p10 = new Person(10, "Natasa", "Andzic", "111222333", "email.email.com");
            contactsList.add(p10);

            myAdapter.notifyDataSetChanged();
        }}