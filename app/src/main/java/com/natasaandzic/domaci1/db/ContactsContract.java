package com.natasaandzic.domaci1.db;

import android.provider.BaseColumns;

public class ContactsContract {

    private ContactsContract() {}

    public static final class ContactEntry implements BaseColumns {
        public static final String TABLE_NAME = "contact";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SURNAME = "surname";
        public static final String COLUMN_NUMBER = "number";
        public static final String COLUMN_EMAIL = "email";
    }
}
