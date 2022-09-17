package com.yarab.application.Fragments;

import android.app.Dialog;
import android.content.ContentValues;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.yarab.application.Models.Customer;
import com.yarab.application.R;
import com.yarab.application.Utils.DatabaseHelper;


public class SubmitInfoDialogFragment extends DialogFragment implements View.OnClickListener {

    private TextView submit_info_current_counter, submit_info_name;
    private Button submit_info_btn_start_rec, submit_info_btn_play, submit_info_btn_cancel;
    private TextView submit_info_tv_bluetooth_value;
    private Button submit_info_btn_submit;
    Bundle bundle;


    int position;
    String name;
    long case_number;

    DatabaseHelper databaseHelper;
    ContentValues contentValues ;

    SubmitCustomerFragment submitCustomerFragment ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bundle = getArguments();
        if (bundle != null) {
            position = bundle.getInt("position");
            name = bundle.getString(Customer.KEY_NAME);
            case_number = bundle.getLong(Customer.KEY_CASE_NUMBER);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = new Dialog(requireContext());
        dialog.setContentView(R.layout.fragment_submit_info_dialog);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);


        initViews(dialog);

        dialog.create();
        return dialog;
    }


    @Override
    public void onResume() {
        super.onResume();
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.99);
        requireDialog().getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        requireDialog().getWindow().setBackgroundDrawableResource(R.drawable.background_splash_dialog);
    }

    private void initViews(Dialog dialog) {
        submitCustomerFragment = new SubmitCustomerFragment();

        submit_info_name =
                dialog.findViewById(R.id.submit_info_tv_name);
        submit_info_current_counter =
                dialog.findViewById(R.id.submit_info_current_counter);
        submit_info_btn_start_rec =
                dialog.findViewById(R.id.submit_info_btn_start_recording);
        submit_info_btn_play =
                dialog.findViewById(R.id.submit_info_btn_play_recording);
        submit_info_tv_bluetooth_value =
                dialog.findViewById(R.id.submit_info_tv_bluetooth_value);
        submit_info_btn_cancel =
                dialog.findViewById(R.id.submit_info_btn_cancel);
        submit_info_btn_submit =
                dialog.findViewById(R.id.submit_info_btn_submit);

        submit_info_name.setText(name);



        String current_counter = submit_info_current_counter.getText().toString();
        int bluetooth_value = Integer.parseInt(submit_info_tv_bluetooth_value.getText().toString());

        contentValues = new ContentValues();
        contentValues.put(Customer.KEY_CURRENT_COUNTER_NUMBER , current_counter);
        contentValues.put(Customer.KEY_BLUETOOTH_VALUE , bluetooth_value);

        submit_info_btn_start_rec.setOnClickListener(this);
        submit_info_btn_play.setOnClickListener(this);
        submit_info_btn_cancel.setOnClickListener(this);
        submit_info_btn_submit.setOnClickListener(this);

        databaseHelper = new DatabaseHelper(requireContext());

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.submit_info_btn_start_recording) {

        } else if (id == R.id.submit_info_btn_play_recording) {

        } else if (id == R.id.submit_info_btn_cancel) {
            dismiss();
        } else if (id == R.id.submit_info_btn_submit) {
            int i = databaseHelper.update(case_number, contentValues);
            Log.i("Submit Info", "onClick: " + i );
            submitCustomerFragment.notify(position);
            dismiss();
        }

    }
}