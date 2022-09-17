package com.yarab.application.Models;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;


public class Customer {

    private long id;
    private String city;
    private String name;
    private long case_number;
    private long geographic_number;
    private String application;
    private String branch_thickness;
    private String address;
    private String branch_status;
    private long counter_body_number;
    private long units_number;
    private long capacity;
    private String gps_default;
    private String gps_current;
    private long bluetooth_value;

    // ----------------------
    private long previous_counter_number;
    private long current_counter_number;
    private long subscription_number;
    private long block_number;
    private long group_number;
    private long plaque_number;
    private long national_number;
    private long phone_number;
    // -----------------------

    public static final String KEY_ID = "id";
    public static final String KEY_CITY = "city";
    public static final String KEY_NAME = "name";
    public static final String KEY_CASE_NUMBER = "caseNumber";
    public static final String KEY_GEOGRAPHIC_NUMBER = "geographicNumber";
    public static final String KEY_APPLICATION = "application";
    public static final String KEY_BRANCH_THICKNESS = "branchThickness";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_BRANCH_STATUS = "branchStatus";
    public static final String KEY_COUNTER_BODY_NUMBER = "counterBodyNumber";
    public static final String KEY_UNITS_NUMBER = "unitsNumber";
    public static final String KEY_CAPACITY = "capacity";
    public static final String KEY_GPS_DEFAULT = "gpsDefault";
    public static final String KEY_GPS_CURRENT = "gpsCurrent";
    public static final String KEY_BLUETOOTH_VALUE = "bluetoothValue";
    public static final String KEY_IS_CHECKED = "is_checked";

    // ---------------------------
    public static final String KEY_PREVIOUS_COUNTER_NUMBER = "previousCounterNumber";
    public static final String KEY_CURRENT_COUNTER_NUMBER = "currentCounterNumber";
    public static final String KEY_SUBSCRIPTION_NUMBER = "subscriptionNumber";
    public static final String KEY_BLOCK_NUMBER = "blockNumber";
    public static final String KEY_GROUP_NUMBER = "groupNumber";
    public static final String KEY_PLAQUE_NUMBER = "plaqueNumber";
    public static final String KEY_NATIONAL_NUMBER = "nationalNumber";
    public static final String KEY_PHONE_NUMBER = "phoneNumber";
    // --------------------------

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSubscription_number() {
        return subscription_number;
    }

    public void setSubscription_number(long subscription_number) {
        this.subscription_number = subscription_number;
    }

    public long getPrevious_counter_number() {
        return previous_counter_number;
    }

    public void setPrevious_counter_number(long previous_counter_number) {
        this.previous_counter_number = previous_counter_number;
    }

    public long getCurrent_counter_number() {
        return current_counter_number;
    }

    public void setCurrent_counter_number(long current_counter_number) {
        this.current_counter_number = current_counter_number;
    }

    public long getCase_number() {
        return case_number;
    }

    public void setCase_number(long case_number) {
        this.case_number = case_number;
    }

    public long getBlock_number() {
        return block_number;
    }

    public void setBlock_number(long block_number) {
        this.block_number = block_number;
    }

    public long getGroup_number() {
        return group_number;
    }

    public void setGroup_number(long group_number) {
        this.group_number = group_number;
    }

    public long getPlaque_number() {
        return plaque_number;
    }

    public void setPlaque_number(long plaque_number) {
        this.plaque_number = plaque_number;
    }

    public long getNational_number() {
        return national_number;
    }

    public void setNational_number(long national_number) {
        this.national_number = national_number;
    }

    public long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(long phone_number) {
        this.phone_number = phone_number;
    }

    public long getBluetooth_value() {
        return bluetooth_value;
    }

    public void setBluetooth_value(long bluetooth_value) {
        this.bluetooth_value = bluetooth_value;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getGeographic_number() {
        return geographic_number;
    }

    public void setGeographic_number(long geographic_number) {
        this.geographic_number = geographic_number;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getBranch_thickness() {
        return branch_thickness;
    }

    public void setBranch_thickness(String branch_thickness) {
        this.branch_thickness = branch_thickness;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBranch_status() {
        return branch_status;
    }

    public void setBranch_status(String branch_status) {
        this.branch_status = branch_status;
    }

    public long getCounter_body_number() {
        return counter_body_number;
    }

    public void setCounter_body_number(long counter_body_number) {
        this.counter_body_number = counter_body_number;
    }

    public long getUnits_number() {
        return units_number;
    }

    public void setUnits_number(long units_number) {
        this.units_number = units_number;
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public String getGps_default() {
        return gps_default;
    }

    public void setGps_default(String gps_default) {
        this.gps_default = gps_default;
    }

    public String getGps_current() {
        return gps_current;
    }

    public void setGps_current(String gps_current) {
        this.gps_current = gps_current;
    }


    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(KEY_CITY, city);
        cv.put(KEY_NAME, name);
        cv.put(KEY_CASE_NUMBER, case_number);
        cv.put(KEY_GEOGRAPHIC_NUMBER, geographic_number);
        cv.put(KEY_APPLICATION, application);
        cv.put(KEY_BRANCH_THICKNESS, branch_thickness);
        cv.put(KEY_ADDRESS, address);
        cv.put(KEY_BRANCH_STATUS, branch_status);
        cv.put(KEY_COUNTER_BODY_NUMBER, counter_body_number);
        cv.put(KEY_UNITS_NUMBER, units_number);
        cv.put(KEY_CAPACITY, capacity);
        cv.put(KEY_GPS_DEFAULT, gps_default);
        cv.put(KEY_GPS_CURRENT, gps_current);
        cv.put(KEY_BLUETOOTH_VALUE, bluetooth_value);
        //
        cv.put(KEY_PREVIOUS_COUNTER_NUMBER, previous_counter_number);
        cv.put(KEY_CURRENT_COUNTER_NUMBER, current_counter_number);
        cv.put(KEY_SUBSCRIPTION_NUMBER, subscription_number);
        cv.put(KEY_BLOCK_NUMBER, block_number);
        cv.put(KEY_GROUP_NUMBER, group_number);
        cv.put(KEY_PLAQUE_NUMBER, plaque_number);
        cv.put(KEY_NATIONAL_NUMBER, national_number);
        cv.put(KEY_PHONE_NUMBER, phone_number);
        return cv;
    }

    @SuppressLint("Range")
    public static Customer cursorToCustomer(Cursor cursor) {
        Customer customer = new Customer();
        customer.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
        customer.setCity(cursor.getString(cursor.getColumnIndex(KEY_CITY)));
        customer.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
        customer.setCase_number(cursor.getLong(cursor.getColumnIndex(KEY_CASE_NUMBER)));
        customer.setGeographic_number(cursor.getLong(cursor.getColumnIndex(KEY_GEOGRAPHIC_NUMBER)));
        customer.setApplication(cursor.getString(cursor.getColumnIndex(KEY_APPLICATION)));
        customer.setBranch_thickness(cursor.getString(cursor.getColumnIndex(KEY_BRANCH_THICKNESS)));
        customer.setAddress(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS)));
        customer.setBranch_status(cursor.getString(cursor.getColumnIndex(KEY_BRANCH_STATUS)));
        customer.setCounter_body_number(cursor.getLong(cursor.getColumnIndex(KEY_COUNTER_BODY_NUMBER)));
        customer.setUnits_number(cursor.getLong(cursor.getColumnIndex(KEY_UNITS_NUMBER)));
        customer.setCapacity(cursor.getLong(cursor.getColumnIndex(KEY_CAPACITY)));
        customer.setGps_default(cursor.getString(cursor.getColumnIndex(KEY_GPS_DEFAULT)));
        customer.setGps_current(cursor.getString(cursor.getColumnIndex(KEY_GPS_CURRENT)));
        customer.setBluetooth_value(cursor.getLong(cursor.getColumnIndex(KEY_BLUETOOTH_VALUE)));
        //
        customer.setPrevious_counter_number(cursor.getLong(cursor.getColumnIndex(KEY_PREVIOUS_COUNTER_NUMBER)));
        customer.setCurrent_counter_number(cursor.getLong(cursor.getColumnIndex(KEY_CURRENT_COUNTER_NUMBER)));
        customer.setSubscription_number(cursor.getLong(cursor.getColumnIndex(KEY_SUBSCRIPTION_NUMBER)));
        customer.setBlock_number(cursor.getLong(cursor.getColumnIndex(KEY_BLOCK_NUMBER)));
        customer.setGroup_number(cursor.getLong(cursor.getColumnIndex(KEY_GROUP_NUMBER)));
        customer.setPlaque_number(cursor.getLong(cursor.getColumnIndex(KEY_PLAQUE_NUMBER)));
        customer.setNational_number(cursor.getLong(cursor.getColumnIndex(KEY_NATIONAL_NUMBER)));
        customer.setPhone_number(cursor.getLong(cursor.getColumnIndex(KEY_PHONE_NUMBER)));
        return customer;
    }




}
