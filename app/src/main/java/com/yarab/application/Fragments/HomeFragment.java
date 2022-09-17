package com.yarab.application.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yarab.application.MainActivity;
import com.yarab.application.Models.Customer;
import com.yarab.application.R;
import com.yarab.application.Utils.DatabaseHelper;

import java.util.List;

public class HomeFragment extends Fragment {

    List<Customer> completeList;
    List<Customer> checkedCustomerList;
    DatabaseHelper databaseHelper ;

    Button btn_enter_submit_customer_fragment;

    MainActivity mainActivity;
    BottomNavigationView bottomNavigationView;

    TextView home_checked_customers_tv ;
    TextView home_not_checked_customers_tv ;

    private FragmentTransaction fragmentTransaction;
    private int checked_size;
    private int unchecked_size;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        databaseHelper = new DatabaseHelper(requireContext());
        completeList = databaseHelper.getAllCustomers();
        int complete_size = completeList.size();

        checkedCustomerList = databaseHelper.getCustomer(Customer.KEY_IS_CHECKED + " = 1",null,null);
        checked_size = checkedCustomerList.size();

        unchecked_size = complete_size - checked_size;

        initViews(view);

        return view;
    }

    private void initViews(View view) {
        //
        home_checked_customers_tv = view.findViewById(R.id.home_checked_customers_tv);
        home_not_checked_customers_tv = view.findViewById(R.id.home_not_checked_customers_tv);

        home_checked_customers_tv.setText(String.valueOf(checked_size));
        home_not_checked_customers_tv.setText(String.valueOf(unchecked_size));

        //
        btn_enter_submit_customer_fragment = view.findViewById(R.id.btn_enter_submit_customers);

        btn_enter_submit_customer_fragment.setOnClickListener(view1 -> {
            mainActivity = (MainActivity) requireActivity();
            bottomNavigationView = mainActivity.findViewById(R.id.bottom_nav_view);

            fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
            SubmitCustomerFragment submitCustomerFragment = new SubmitCustomerFragment();
            fragmentTransaction.replace(R.id.fragment_container, submitCustomerFragment)
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit();

            if (mainActivity.getSupportActionBar() != null) {
                mainActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                bottomNavigationView.setSelectedItemId(R.id.bottom_nav_submit_customer);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
            HomeFragment homeFragment = new HomeFragment();
            fragmentTransaction.replace(R.id.fragment_container, homeFragment)
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit();

            bottomNavigationView.setSelectedItemId(R.id.bottom_nav_home);
        }
        return super.onOptionsItemSelected(item);
    }
}