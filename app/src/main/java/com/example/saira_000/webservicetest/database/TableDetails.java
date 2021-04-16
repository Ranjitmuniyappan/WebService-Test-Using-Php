package com.example.saira_000.webservicetest.database;

public class TableDetails {

    public static abstract class useraccount
    {
        protected static String TABLE_NAME = "useraccount";
        protected static String ID = "id";
        protected static String FIRSTNAME = "firstname";
        protected static String LASTNAME = "lastname";
        protected static String EMAIL = "email";
        protected static String PHONE = "phone";
        protected static String PASSWORD = "password";

        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " " + "(" +
                ID + " TEXT," +
                FIRSTNAME + " TEXT," +
                LASTNAME + " TEXT," +
                EMAIL + " TEXT," +
                PHONE + " TEXT," +
                PASSWORD + " TEXT)";
    }
}
