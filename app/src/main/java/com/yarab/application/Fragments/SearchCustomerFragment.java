package com.yarab.application.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yarab.application.Adapters.CustomerAdapter;
import com.yarab.application.MainActivity;
import com.yarab.application.Models.Customer;
import com.yarab.application.R;
import com.yarab.application.Utils.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;


public class SearchCustomerFragment extends Fragment {


    final String KEY_SPINNER_SEARCH_ITEM = "spinner_search_item";

    Spinner spinner_search_fragment;
    int selected_spinner_item = 0;
    SearchView searchView;
    RecyclerView recyclerView;

    DatabaseHelper databaseHelper;
    CustomerAdapter customerAdapter;

    List<Customer> customerList;

    SharedPreferences preferences;

    BottomNavigationView bottomNavigationView;

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search_customer, container, false);
        //
        preferences = requireActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(KEY_SPINNER_SEARCH_ITEM, 0);
        //
        recyclerView = v.findViewById(R.id.search_fragment_recycler_view);
        customerList = new ArrayList<>();
        databaseHelper = new DatabaseHelper(requireContext());
        customerAdapter = new CustomerAdapter(customerList, requireActivity());
        recyclerView.setAdapter(customerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        //
        spinner_search_fragment = v.findViewById(R.id.search_fragment_spinner);
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(requireContext(), R.array.search_items_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_search_fragment.setAdapter(adapter);

        spinner_search_fragment.setSelection(selected_spinner_item);

        spinner_search_fragment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        selected_spinner_item = 0;
                        editor.putInt(KEY_SPINNER_SEARCH_ITEM, selected_spinner_item);
                        editor.apply();
                        break;
                    case 1:
                        selected_spinner_item = 1;
                        editor.putInt(KEY_SPINNER_SEARCH_ITEM, selected_spinner_item);
                        editor.apply();
                        break;
                    case 2:
                        selected_spinner_item = 2;
                        editor.putInt(KEY_SPINNER_SEARCH_ITEM, selected_spinner_item);
                        editor.apply();
                        break;
                    case 3:
                        selected_spinner_item = 3;
                        editor.putInt(KEY_SPINNER_SEARCH_ITEM, selected_spinner_item);
                        editor.apply();
                        break;
                    case 4:
                        selected_spinner_item = 4;
                        editor.putInt(KEY_SPINNER_SEARCH_ITEM, selected_spinner_item);
                        editor.apply();
                        break;
                    case 5:
                        selected_spinner_item = 5;
                        editor.putInt(KEY_SPINNER_SEARCH_ITEM, selected_spinner_item);
                        editor.apply();
                        break;
                    case 6:
                        selected_spinner_item = 6;
                        editor.putInt(KEY_SPINNER_SEARCH_ITEM, selected_spinner_item);
                        editor.apply();
                        break;
                    case 7:
                        selected_spinner_item = 7;
                        editor.putInt(KEY_SPINNER_SEARCH_ITEM, selected_spinner_item);
                        editor.apply();
                        break;
                }
                customerList = databaseHelper.getAllCustomers();
                customerAdapter = new CustomerAdapter(customerList, requireActivity());
                recyclerView.setAdapter(customerAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        searchView = v.findViewById(R.id.search_fragment_search_view);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                switch (selected_spinner_item) {
                    case 0:
                        customerList = databaseHelper.getAllCustomers();
                        break;
                    case 1:
                        customerList = databaseHelper.getCustomer(Customer.KEY_NAME + " LIKE '%" + newText + "%'", null);
                        break;
                    case 2:
                        customerList = databaseHelper.getCustomer(Customer.KEY_SUBSCRIPTION_NUMBER + " LIKE '" + newText + "%'", null);
                        break;
                    case 3:
                        customerList = databaseHelper.getCustomer(Customer.KEY_CASE_NUMBER + " LIKE '" + newText + "%'", null);
                        break;
                    case 4:
                        customerList = databaseHelper.getCustomer(Customer.KEY_BLOCK_NUMBER + " LIKE '" + newText + "%'", null);
                        break;
                    case 5:
                        customerList = databaseHelper.getCustomer(Customer.KEY_GROUP_NUMBER + " LIKE '" + newText + "%'", null);
                        break;
                    case 6:
                        customerList = databaseHelper.getCustomer(Customer.KEY_PLAQUE_NUMBER + " LIKE '" + newText + "%'", null);
                        break;
                    case 7:
                        customerList = databaseHelper.getCustomer(Customer.KEY_NATIONAL_NUMBER + " LIKE '" + newText + "%'", null);
                        break;
                }
                customerAdapter = new CustomerAdapter(customerList, requireActivity());
                recyclerView.setAdapter(customerAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
                return false;
            }
        });


        //
        MainActivity activity = (MainActivity) requireActivity();

        bottomNavigationView = activity.findViewById(R.id.bottom_nav_view);
        if (activity.getSupportActionBar() != null) {

            ActionBar actionBar = activity.getSupportActionBar();
            actionBar.setTitle(R.string.search_fragment_title);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        return v;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new SubmitCustomerFragment())
                    .setReorderingAllowed(true).addToBackStack(null)
                    .commit();

            bottomNavigationView.setSelectedItemId(R.id.bottom_nav_submit_customer);
        }
        return super.onOptionsItemSelected(item);
    }
}