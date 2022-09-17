package com.yarab.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.database.sqlite.SQLiteDatabase;


import android.os.Bundle;
import android.view.MenuItem;

import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.yarab.application.Fragments.CustomerDetailsFragment;
import com.yarab.application.Fragments.HomeFragment;
import com.yarab.application.Fragments.ImportExportFragment;
import com.yarab.application.Fragments.SettingsFragment;
import com.yarab.application.Fragments.SubmitCustomerFragment;
import com.yarab.application.Models.Customer;
import com.yarab.application.Utils.DatabaseHelper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    //for two time press to exit
    private long backPressedTime;
    Toast exitMessage;
    //
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;
    //
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getActionBar() !=null)
            getActionBar().hide();

        //toolbar set up
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.title_home));

        setSupportActionBar(toolbar);

        //Bottom Navigation jobs
        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setSelectedItemId(R.id.bottom_nav_home);
        bottomNavigationView.setOnItemSelectedListener(this);





        //kkkkkkkk

        // home fragment set up
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HomeFragment homeFragment = new HomeFragment();
        fragmentTransaction.replace(R.id.fragment_container, homeFragment)
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit();

        // for assuring database creation
        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        if (db.isOpen()) db.close();


    }

    // bottom navigation items listener
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        String tag = "";
        Fragment selected_fragment = null;

        if (itemId == R.id.bottom_nav_home) {
            selected_fragment = new HomeFragment();
            tag = "HOME_FRAGMENT";
            toolbar.setTitle(getResources().getString(R.string.title_home));

        } else if (itemId == R.id.bottom_nav_submit_customer) {
            selected_fragment = new SubmitCustomerFragment();
            tag = "SUBMIT_CUSTOMER_FRAGMENT";
            toolbar.setTitle(getResources().getString(R.string.title_add_customer));

        } else if (itemId == R.id.bottom_nav_import_export) {
            selected_fragment = new ImportExportFragment();
            tag = "IMPORT_EXPORT_FRAGMENT";
            toolbar.setTitle(getResources().getString(R.string.title_import_export));

        } else if (itemId == R.id.bottom_nav_settings) {
            selected_fragment = new SettingsFragment();
            tag = "SETTINGS_FRAGMENT";
            toolbar.setTitle(getResources().getString(R.string.title_settings));
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (selected_fragment != null)
            fragmentTransaction.replace(R.id.fragment_container, selected_fragment, tag)
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit();

        if (selected_fragment != null && getSupportActionBar() != null) {
            if (selected_fragment.getTag() == "HOME_FRAGMENT") {
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            } else if (selected_fragment.getTag() == "SUBMIT_CUSTOMER_FRAGMENT") {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            } else if (selected_fragment.getTag() == "IMPORT_EXPORT_FRAGMENT") {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            } else if (selected_fragment.getTag() == "SETTINGS_FRAGMENT") {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment())
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit();

            toolbar.setTitle(getResources().getString(R.string.title_home));
            bottomNavigationView.setSelectedItemId(R.id.bottom_nav_home);
        }
        return super.onOptionsItemSelected(item);
    }

    // for two time press back to exit
    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            exitMessage.cancel();
            super.onBackPressed();
            return;
        } else {
            exitMessage = Toast.makeText(getBaseContext(), "برای خروج دوباره دکمه بازگشت را فشار دهید", Toast.LENGTH_SHORT);
            exitMessage.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

}