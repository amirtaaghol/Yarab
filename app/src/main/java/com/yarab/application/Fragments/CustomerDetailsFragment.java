package com.yarab.application.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yarab.application.MainActivity;
import com.yarab.application.Models.Customer;
import com.yarab.application.R;

public class CustomerDetailsFragment extends Fragment {


    private final String TAG = "Customer_Details_fragment: ";

    TextView city_tv,
            full_name_tv,
            case_number_tv,
            application_tv,
            branch_thickness_tv,
            address_tv,
            status_tv,
            counter_body_number_tv,
            units_number_tv,
            bluetooth_number_tv,
            previous_counter_tv,
            subscription_number_tv,
            block_number_tv,
            group_number_tv,
            plaque_number_tv,
            national_number_tv,
            phone_number_tv;


    String city,
            full_name,
            case_number,
            application,
            branch_thickness,
            address,
            status,
            counter_body_number,
            units_number,
            bluetooth_number,
            previous_counter,
            subscription_number,
            block_number,
            group_number,
            plaque_number,
            national_number,
            phone_number;


    BottomNavigationView bottomNavigationView;
    private MainActivity activity;
    private ActionBar actionBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        setHasOptionsMenu(true);
        //
        Bundle bundle = getArguments();
        if (bundle != null) {
            city = bundle.getString(Customer.KEY_CITY);
            full_name = bundle.getString(Customer.KEY_NAME);
            case_number = bundle.getString(Customer.KEY_CASE_NUMBER);
            application = bundle.getString(Customer.KEY_APPLICATION);
            branch_thickness = bundle.getString(Customer.KEY_BRANCH_THICKNESS);
            address = bundle.getString(Customer.KEY_ADDRESS);
            status = bundle.getString(Customer.KEY_BRANCH_STATUS);
            counter_body_number = bundle.getString(Customer.KEY_COUNTER_BODY_NUMBER);
            units_number = bundle.getString(Customer.KEY_UNITS_NUMBER);
            bluetooth_number = bundle.getString(Customer.KEY_BLUETOOTH_VALUE);
            previous_counter = bundle.getString(Customer.KEY_PREVIOUS_COUNTER_NUMBER);
            subscription_number = bundle.getString(Customer.KEY_SUBSCRIPTION_NUMBER);
            block_number = bundle.getString(Customer.KEY_BLOCK_NUMBER);
            group_number = bundle.getString(Customer.KEY_GROUP_NUMBER);
            plaque_number = bundle.getString(Customer.KEY_PLAQUE_NUMBER);
            national_number = bundle.getString(Customer.KEY_NATIONAL_NUMBER);
            phone_number = bundle.getString(Customer.KEY_PHONE_NUMBER);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_details, container, false);
        initViews(view);
        return view;
    }

    public void initViews(View v) {
        //
        city_tv = v.findViewById(R.id.details_fragment_city_tv);
        full_name_tv = v.findViewById(R.id.details_fragment_full_name_tv);
        case_number_tv = v.findViewById(R.id.details_fragment_case_number_tv);
        application_tv = v.findViewById(R.id.details_fragment_application_tv);
        branch_thickness_tv = v.findViewById(R.id.details_fragment_branch_thickness_tv);
        address_tv = v.findViewById(R.id.details_fragment_address_tv);
        status_tv = v.findViewById(R.id.details_fragment_status_tv);
        counter_body_number_tv = v.findViewById(R.id.details_fragment_counter_body_number_tv);
        units_number_tv = v.findViewById(R.id.details_fragment_units_number_tv);
        bluetooth_number_tv = v.findViewById(R.id.details_fragment_bluetooth_number_tv);
        previous_counter_tv = v.findViewById(R.id.details_fragment_previous_counter_tv);
        subscription_number_tv = v.findViewById(R.id.details_fragment_subscription_number_tv);
        block_number_tv = v.findViewById(R.id.details_fragment_block_number_tv);
        group_number_tv = v.findViewById(R.id.details_fragment_group_number_tv);
        plaque_number_tv = v.findViewById(R.id.details_fragment_plaque_number_tv);
        national_number_tv = v.findViewById(R.id.details_fragment_national_number_tv);
        phone_number_tv = v.findViewById(R.id.details_fragment_phone_number_tv);
        //
        activity = (MainActivity) requireActivity();
        bottomNavigationView = activity.findViewById(R.id.bottom_nav_view);
        if (activity.getSupportActionBar() != null) {
            actionBar = activity.getSupportActionBar();
            actionBar.setTitle(R.string.customer_details_fragment_title);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        setTvs();
    }

    private void setTvs() {
        city_tv.setText(city);
        full_name_tv.setText(full_name);
        case_number_tv.setText(case_number);
        application_tv.setText(application);
        branch_thickness_tv.setText(branch_thickness);
        address_tv.setText(address);
        status_tv.setText(status);
        counter_body_number_tv.setText(counter_body_number);
        units_number_tv.setText(units_number);
        bluetooth_number_tv.setText(block_number);
        previous_counter_tv.setText(previous_counter);
        subscription_number_tv.setText(subscription_number);
        block_number_tv.setText(block_number);
        group_number_tv.setText(group_number);
        plaque_number_tv.setText(plaque_number);
        national_number_tv.setText(national_number);
        phone_number_tv.setText(phone_number);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new SubmitCustomerFragment())
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit();

            bottomNavigationView.setSelectedItemId(R.id.bottom_nav_submit_customer);
        }
        return super.onOptionsItemSelected(item);
    }
}