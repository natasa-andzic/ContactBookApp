package com.natasaandzic.domaci1.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.natasaandzic.domaci1.db.ContactsContract.ContactEntry;

public class ContactsDbHelper extends SQLiteOpenHelper {

    private static final String TAG = "ContactsDbHelper";

    public static final String DATABASE_NAME = "contacts.com.natasaandzic.domaci1.db";
    public static final int DATABASE_VERSION = 4;

    private static ContactsDbHelper mInstance;

    public static synchronized ContactsDbHelper getInstance(Context context){
        if (mInstance == null){
            mInstance = new ContactsDbHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    private ContactsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_CONTACT_TABLE = "CREATE TABLE " +
                ContactEntry.TABLE_NAME + " (" +
                ContactEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ContactEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                ContactEntry.COLUMN_SURNAME + " TEXT, " +
                ContactEntry.COLUMN_NUMBER + " TEXT, " +
                ContactEntry.COLUMN_EMAIL + " TEXT" +
                ");";

        db.execSQL(SQL_CREATE_CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ContactEntry.TABLE_NAME);
        onCreate(db);
    }

    public long addContact(String name, String surname, String number, String email){

        ContentValues cv = new ContentValues();
        cv.put(ContactsContract.ContactEntry.COLUMN_NAME, name);
        cv.put(ContactsContract.ContactEntry.COLUMN_SURNAME, surname);
        cv.put(ContactEntry.COLUMN_NUMBER, number);
        cv.put(ContactsContract.ContactEntry.COLUMN_EMAIL, email);

        long rowId = this.getWritableDatabase().insert(ContactsContract.ContactEntry.TABLE_NAME, null, cv);

        return rowId;
    }

    public void deleteContact(long id){
        this.getWritableDatabase().execSQL("DELETE FROM "+ ContactEntry.TABLE_NAME+" WHERE _id='"+id+"'");
    }

    /* First make a ContentValues object :

        ContentValues cv = new ContentValues();
        cv.put("Field1","Bob"); //These Fields should be your String values of actual column names
        cv.put("Field2","19");
        cv.put("Field2","Male");
        Then use the update method, it should work now:

        myDB.update(TableName, cv, "_id="+id, null);

        i

        https://www.androidhive.info/2011/11/android-sqlite-database-tutorial/

        i

        https://www.youtube.com/watch?v=dvDTmJtGE_I */

    public void updateContact(long id, String name, String surname, String number, String email){


                  String s =      "UPDATE " + ContactEntry.TABLE_NAME +" SET "
                        + ContactEntry.COLUMN_NAME + " ='" + name + "', "
                        + ContactEntry.COLUMN_SURNAME + " ='" + surname + "', "
                        + ContactEntry.COLUMN_NUMBER + " ='" + number + "', "
                        + ContactEntry.COLUMN_EMAIL + " ='" + email + "' "
                        + "WHERE " + ContactEntry._ID + " ='" + id + "'";

        Log.v(TAG, s);

        this.getWritableDatabase()
                .execSQL(s);
    }

    public void finalize(){

    }


}
