package com.example.natasaandzic.domaci1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by natasaandzic on 34//18.
 */

public class Database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    public static final String databaseName = "Database.db";
    public static final String tableName = "Tabela";
    public static final String col1= "ID";
    public static final String col2 = "Name";
    public static final String col3 = "Surname";
    public static final String col4 = "Phone";
    public static final String col5 = "Email";

    public Database(Context context) {

        super(context, databaseName, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Tabela(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SURNAME TEXT, PHONE TEXT, EMAIL TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Tabela");
        onCreate(db);
    }

    public long insertDataInDatabase(String name, String surname, String phone, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col2, name);
        cv.put(col3, surname);
        cv.put(col4, phone);
        cv.put(col5, email);
        long id = db.insert(tableName, null, cv);
        db.close();
        return id;
    }

    public Person getPerson(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Person.tableName,
                new String[]{Person.COLUMN_ID, Person.COLUMN_NAME, Person.COLUMN_SURNAME, Person.COLUMN_PHONE,
                Person.COLUMN_EMAIL}, Person.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Person person = new Person(
                cursor.getInt(cursor.getColumnIndex(Person.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Person.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndex(Person.COLUMN_SURNAME)),
                cursor.getString(cursor.getColumnIndex(Person.COLUMN_PHONE)),
                cursor.getString(cursor.getColumnIndex(Person.COLUMN_EMAIL))
                );

        cursor.close();

        return person;
    }

    public List<Person> getAllContacts() {
        List<Person> contacts = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Person.tableName + " ORDER BY " +
                Person.COLUMN_NAME + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Person person = new Person();
                person.setId(cursor.getInt(cursor.getColumnIndex(Person.COLUMN_ID)));
                person.setName(cursor.getString(cursor.getColumnIndex(Person.COLUMN_NAME)));
                person.setSurname(cursor.getString(cursor.getColumnIndex(Person.COLUMN_SURNAME)));
                person.setPhone(cursor.getString(cursor.getColumnIndex(Person.COLUMN_PHONE)));
                person.setEmail(cursor.getString(cursor.getColumnIndex(Person.COLUMN_EMAIL)));
                contacts.add(person);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return contacts;
    }

    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + Person.tableName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    public int updateContact(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Person.COLUMN_NAME, person.getName());
        values.put(Person.COLUMN_SURNAME, person.getSurname());
        values.put(Person.COLUMN_PHONE, person.getPhone());
        values.put(Person.COLUMN_EMAIL, person.getEmail());

        // updating row
        return db.update(Person.tableName, values, Person.COLUMN_ID + " = ?",
                new String[]{String.valueOf(person.getId()) });
    }

    public void deleteContact(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Person.tableName, Person.COLUMN_ID + " = ?",
                new String[]{String.valueOf(person.getId())});
        db.close();
    }
}

