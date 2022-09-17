package com.yarab.application.Utils;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.yarab.application.Models.Customer;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    private SQLiteDatabase db;

    private static final String TAG = "DATABASE_HELPER :";

    private static final String DB_NAME = "Customers_DataBase";
    private static final int DB_VERSION = 1;

    public static final String TABLE_CUSTOMERS = "table_customers";

    private static final String[] columns = {
            Customer.KEY_ID,
            Customer.KEY_CITY,
            Customer.KEY_NAME,
            Customer.KEY_CASE_NUMBER,
            Customer.KEY_GEOGRAPHIC_NUMBER,
            Customer.KEY_APPLICATION,
            Customer.KEY_BRANCH_THICKNESS,
            Customer.KEY_ADDRESS,
            Customer.KEY_BRANCH_STATUS,
            Customer.KEY_COUNTER_BODY_NUMBER,
            Customer.KEY_UNITS_NUMBER,
            Customer.KEY_CAPACITY,
            Customer.KEY_GPS_DEFAULT,
            Customer.KEY_GPS_CURRENT,
            Customer.KEY_BLUETOOTH_VALUE,
            Customer.KEY_PREVIOUS_COUNTER_NUMBER,
            Customer.KEY_CURRENT_COUNTER_NUMBER,
            Customer.KEY_SUBSCRIPTION_NUMBER,
            Customer.KEY_BLOCK_NUMBER,
            Customer.KEY_GROUP_NUMBER,
            Customer.KEY_PLAQUE_NUMBER,
            Customer.KEY_NATIONAL_NUMBER,
            Customer.KEY_PHONE_NUMBER,
            Customer.KEY_IS_CHECKED
    };

    private static final String cmd = "CREATE TABLE IF NOT EXISTS '" + TABLE_CUSTOMERS + "'('"
            + Customer.KEY_ID + "'INTEGER PRIMARY KEY UNIQUE NOT NULL, '"
            + Customer.KEY_CITY + "'TEXT, '"
            + Customer.KEY_NAME + "'TEXT, '"
            + Customer.KEY_CASE_NUMBER + "'NUMERIC, '"
            + Customer.KEY_GEOGRAPHIC_NUMBER + "'TEXT, '"
            + Customer.KEY_APPLICATION + "'TEXT, '"
            + Customer.KEY_BRANCH_THICKNESS + "'TEXT, '"
            + Customer.KEY_ADDRESS + "'TEXT, '"
            + Customer.KEY_BRANCH_STATUS + "'TEXT, '"
            + Customer.KEY_COUNTER_BODY_NUMBER + "'NUMERIC, '"
            + Customer.KEY_UNITS_NUMBER + "'NUMERIC, '"
            + Customer.KEY_CAPACITY + "'NUMERIC, '"
            + Customer.KEY_GPS_DEFAULT + "'TEXT, '"
            + Customer.KEY_GPS_CURRENT + "'TEXT, '"
            + Customer.KEY_BLUETOOTH_VALUE + "'NUMERIC, '"
            + Customer.KEY_PREVIOUS_COUNTER_NUMBER + "'NUMERIC, '"
            + Customer.KEY_CURRENT_COUNTER_NUMBER + "'NUMERIC, '"
            + Customer.KEY_SUBSCRIPTION_NUMBER + "'NUMERIC, '"
            + Customer.KEY_BLOCK_NUMBER + "'NUMERIC, '"
            + Customer.KEY_GROUP_NUMBER + "'NUMERIC, '"
            + Customer.KEY_PLAQUE_NUMBER + "'NUMERIC, '"
            + Customer.KEY_NATIONAL_NUMBER + "'NUMERIC, '"
            + Customer.KEY_PHONE_NUMBER + "'NUMERIC, '"
            + Customer.KEY_IS_CHECKED + "'NUMERIC "
            + ")";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(cmd);
        Log.i(TAG, " Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMERS);
        onCreate(sqLiteDatabase);
        Log.i(TAG, " Table Deleted");
    }

    public void open() {
        if (null == db || !db.isOpen()) {
            try {
                db = getWritableDatabase();
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {
        if (db.isOpen() && db != null) {
            db.close();
        }
    }

    public void delete() {
        db.execSQL("DELETE FROM " + TABLE_CUSTOMERS);
    }

    public int update(long case_number, ContentValues contentValues) {
        try {
            db = getWritableDatabase();
            int updated = db.update(TABLE_CUSTOMERS, contentValues,
                    Customer.KEY_CASE_NUMBER + " = " + case_number, null);
            if (db.isOpen()) db.close();
            return updated;
        } catch (SQLiteException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int insert(String tableName, ContentValues contentValues) {

        try {
            db = getWritableDatabase();
            long updated = db.insert(tableName, null, contentValues);
            Log.i(TAG, updated + " inserted");
            if (db.isOpen()) db.close();
            return (int) updated;
        } catch (Exception exception) {
            Log.i(TAG, "Error inserting" + exception.getMessage());
            return 0;
        }
    }

    public List<Customer> getAllCustomers() {
        db = getReadableDatabase();
        List<Customer> customerList = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CUSTOMERS, null);
        if (cursor.moveToFirst()) {
            do {
                Customer customer = Customer.cursorToCustomer(cursor);
                customerList.add(customer);
            } while (cursor.moveToNext());
        }
        cursor.close();
        if (db.isOpen()) db.close();
        return customerList;
    }

    public List<Customer> getCustomer(String selection, String[] selArg) {
        db = getReadableDatabase();
        List<Customer> customerList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_CUSTOMERS, columns, selection, selArg, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Customer customer = Customer.cursorToCustomer(cursor);
                customerList.add(customer);
            } while (cursor.moveToNext());
        }
        cursor.close();
        if (db.isOpen()) db.close();
        return customerList;
    }

    public List<Customer> getCustomer(String selection, String[] selArg, String orderBy) {
        db = getReadableDatabase();
        List<Customer> customerList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_CUSTOMERS, columns, selection, selArg, null, null, orderBy);
        if (cursor.moveToFirst()) {
            do {
                Customer customer = Customer.cursorToCustomer(cursor);
                customerList.add(customer);
            } while (cursor.moveToNext());
        }
        cursor.close();
        if (db.isOpen()) db.close();
        return customerList;
    }

}
