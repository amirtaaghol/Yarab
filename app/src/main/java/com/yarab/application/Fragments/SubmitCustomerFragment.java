package com.yarab.application.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yarab.application.Adapters.CustomerAdapter;
import com.yarab.application.Models.Customer;
import com.yarab.application.R;
import com.yarab.application.Utils.DatabaseHelper;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class SubmitCustomerFragment extends Fragment {


    private final String KEY_SPINNER_SELECTED_ITEM = "spinnerSelectedItem";

    private int spinner_selected_item = -1;

    private DatabaseHelper databaseHelper;
    private List<Customer> customerList;
    private RecyclerView recyclerView;

    private CustomerAdapter customerAdapter;
    Spinner spinner;

    SharedPreferences pref;
    private LinearLayoutManager manager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_submit_customer, container, false);

        //preference editor
        pref = requireActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();


        //recycler view init
        manager = new LinearLayoutManager(requireContext());
        recyclerView = view.findViewById(R.id.add_customer_fragment_recycler_view);
        databaseHelper = new DatabaseHelper(requireContext());
        customerList = new ArrayList<>();
        customerAdapter = new CustomerAdapter(customerList, requireActivity());
        recyclerView.setAdapter(customerAdapter);
        recyclerView.setLayoutManager(manager);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                LinearLayoutManager manager1 = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (manager1 != null) {
                    editor.putInt("last_position", manager1.findLastVisibleItemPosition());
                    editor.apply();
                }
            }
        });


        //spinner
        spinner = view.findViewById(R.id.add_customer_fragment_spinner);
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(requireContext(), R.array.sort_items_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        int selected = pref.getInt(KEY_SPINNER_SELECTED_ITEM, 0);
        spinner.setSelection(selected);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        spinner_selected_item = 0;
                        customerList = databaseHelper.getAllCustomers();

                        editor.putInt(KEY_SPINNER_SELECTED_ITEM, spinner_selected_item);
                        editor.apply();
                        break;
                    case 1:
                        spinner_selected_item = 1;
                        customerList = databaseHelper.getCustomer(null, null, Customer.KEY_IS_CHECKED + " DESC");

                        editor.putInt(KEY_SPINNER_SELECTED_ITEM, spinner_selected_item);
                        editor.apply();
                        break;
                    case 2:
                        spinner_selected_item = 2;
                        customerList = databaseHelper.getCustomer(null, null, Customer.KEY_IS_CHECKED + " ASC");

                        editor.putInt(KEY_SPINNER_SELECTED_ITEM, spinner_selected_item);
                        editor.apply();
                        break;
                    case 3:
                        spinner_selected_item = 3;
                        customerList = databaseHelper.getCustomer(null, null, Customer.KEY_PLAQUE_NUMBER + " ASC");

                        editor.putInt(KEY_SPINNER_SELECTED_ITEM, spinner_selected_item);
                        editor.apply();
                        break;
                    case 4:
                        spinner_selected_item = 4;
                        customerList = databaseHelper.getCustomer(null, null, Customer.KEY_SUBSCRIPTION_NUMBER + " ASC");

                        editor.putInt(KEY_SPINNER_SELECTED_ITEM, spinner_selected_item);
                        editor.apply();
                        break;
                    case 5:
                        spinner_selected_item = 5;
                        customerList = databaseHelper.getCustomer(null, null, Customer.KEY_CASE_NUMBER + " ASC");

                        editor.putInt(KEY_SPINNER_SELECTED_ITEM, spinner_selected_item);
                        editor.apply();
                        break;
                    case 6:
                        spinner_selected_item = 6;
                        customerList = databaseHelper.getCustomer(null, null, Customer.KEY_BLOCK_NUMBER + " ASC");

                        editor.putInt(KEY_SPINNER_SELECTED_ITEM, spinner_selected_item);
                        editor.apply();
                        break;
                }
                customerAdapter = new CustomerAdapter(customerList, requireActivity());
                recyclerView.setAdapter(customerAdapter);
                recyclerView.setLayoutManager(manager);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                recyclerView.scrollToPosition(pref.getInt("last_position", 0));
            }
        }, 100);
    }

    @Override
    public void onDetach() {
        customerList.clear();
        recyclerView = null;
        customerAdapter = null;
        super.onDetach();
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();
    }

    // menu for search icon in toolbar
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.submit_customer_toolbar_menu, menu);
    }

    // handling search icon clicked
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.submit_customer_search) {
            FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
            SearchCustomerFragment fragment = new SearchCustomerFragment();
            fragmentTransaction.replace(R.id.fragment_container, fragment).setReorderingAllowed(true).addToBackStack(null).commit();
        }
        return super.onOptionsItemSelected(item);
    }

    public void notify(int position) {
        if (customerAdapter != null)
            customerAdapter.notifyItemChanged(position);
    }

}