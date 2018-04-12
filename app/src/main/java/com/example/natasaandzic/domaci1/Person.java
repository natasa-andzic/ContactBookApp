package com.example.natasaandzic.domaci1;

/**
 * Created by natasaandzic on 124//18.
 */

public class Person {

    public static final String tableName = "Tabela";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_SURNAME = "Surname";
    public static final String COLUMN_PHONE = "Phone";
    public static final String COLUMN_EMAIL = "Email";

    private int id;
    private String name;
    private String surname;
    private String phone;
    private String email;

    public static final String CREATE_TABLE =
            "CREATE TABLE" + tableName + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_NAME + " TEXT, "
                    + COLUMN_SURNAME + " TEXT, "
                    + COLUMN_PHONE + " TEXT,"
                    + COLUMN_EMAIL + " TEXT "
                        + ")";


    public Person() {

    }

    public Person(int id, String name, String surname, String phone, String email) {

        this.id=id;
        this.name=name;
        this.surname=surname;
        this.phone=phone;
        this.email=email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}