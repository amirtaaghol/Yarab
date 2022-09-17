package com.yarab.application.Adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.yarab.application.Fragments.CustomerDetailsFragment;

import com.yarab.application.Fragments.SubmitInfoDialogFragment;
import com.yarab.application.Models.Customer;
import com.yarab.application.R;

import java.util.List;


public class CustomerAdapter extends
        RecyclerView.Adapter<CustomerAdapter.ViewHolder> {



    private List<Customer> customerList;
    private FragmentActivity activity ;
    private FragmentTransaction fragmentTransaction;
    private Bundle bundle;

    public CustomerAdapter(List<Customer> customerList , FragmentActivity activity) {
        this.customerList = customerList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public CustomerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.layout_custom_add_customer, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAdapter.ViewHolder holder, int position) {
        Customer customer = customerList.get(position);


        String city = customer.getCity();
        String full_name = customer.getName();
        String case_number = String.valueOf(customer.getCase_number());
        String application = customer.getApplication();
        String branch_thickness = customer.getBranch_thickness();
        String address = customer.getAddress();
        String status = customer.getBranch_status();
        String counter_body_number = String.valueOf(customer.getCurrent_counter_number());
        String units_number = String.valueOf(customer.getUnits_number());
        String bluetooth_number = String.valueOf(customer.getBluetooth_value());
        String previous_counter = String.valueOf(customer.getPrevious_counter_number());
        String subscription_number = String.valueOf(customer.getSubscription_number());
        String block_number = String.valueOf(customer.getBlock_number()) ;
        String group_number = String.valueOf(customer.getGroup_number());
        String plaque_number = String.valueOf(customer.getPlaque_number()) ;
        String national_number = String.valueOf(customer.getNational_number());
        String phone_number= String.valueOf(customer.getPhone_number());


        TextView customer_name_tv = holder.customer_name_tv;
        customer_name_tv.setText(full_name);

        TextView customer_subscription_tv = holder.customer_subscription_tv;
        customer_subscription_tv.setText(subscription_number);

        TextView customer_case_tv =holder.customer_case_tv;

        customer_case_tv.setText(case_number);

        TextView customer_previous_counter_tv= holder.customer_previous_counter_tv;

        customer_previous_counter_tv.setText(previous_counter);

        TextView customer_current_counter_tv =holder.customer_current_counter_tv;
        customer_current_counter_tv.setText(String.valueOf(customer.getCurrent_counter_number()));

        Button btn_submit = holder.btn_submit;
        btn_submit.setOnClickListener(view -> {
            fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
            SubmitInfoDialogFragment submitInfoDialogFragment = new SubmitInfoDialogFragment();

            bundle = new Bundle();
            bundle.putString(Customer.KEY_NAME , full_name);
            bundle.putInt("position" , position);
            bundle.putLong(Customer.KEY_CASE_NUMBER , Long.parseLong(case_number));

            submitInfoDialogFragment.setArguments(bundle);

            submitInfoDialogFragment.show(fragmentTransaction , "dialog");
        });

        Button btn_details =holder.btn_details;
        btn_details.setOnClickListener(view -> {

            fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
            CustomerDetailsFragment customerDetailsFragment = new CustomerDetailsFragment();

            bundle = new Bundle();
            bundle.putString(Customer.KEY_CITY ,city);
            bundle.putString(Customer.KEY_NAME,full_name);
            bundle.putString(Customer.KEY_CASE_NUMBER ,case_number);
            bundle.putString(Customer.KEY_APPLICATION ,application);
            bundle.putString(Customer.KEY_BRANCH_THICKNESS ,branch_thickness);
            bundle.putString(Customer.KEY_ADDRESS ,address);
            bundle.putString(Customer.KEY_BRANCH_STATUS ,status);
            bundle.putString(Customer.KEY_COUNTER_BODY_NUMBER ,counter_body_number);
            bundle.putString(Customer.KEY_UNITS_NUMBER ,units_number);
            bundle.putString(Customer.KEY_BLUETOOTH_VALUE ,bluetooth_number);
            bundle.putString(Customer.KEY_PREVIOUS_COUNTER_NUMBER ,previous_counter );
            bundle.putString(Customer.KEY_SUBSCRIPTION_NUMBER ,subscription_number);
            bundle.putString(Customer.KEY_BLOCK_NUMBER ,block_number);
            bundle.putString(Customer.KEY_GROUP_NUMBER ,group_number);
            bundle.putString(Customer.KEY_PLAQUE_NUMBER ,plaque_number);
            bundle.putString(Customer.KEY_NATIONAL_NUMBER ,national_number);
            bundle.putString(Customer.KEY_PHONE_NUMBER ,phone_number);

            customerDetailsFragment.setArguments(bundle);

            fragmentTransaction.replace(R.id.fragment_container ,customerDetailsFragment)
                    .setReorderingAllowed(true).addToBackStack(null).commit();
        });
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView customer_name_tv;
        TextView customer_subscription_tv;
        TextView customer_case_tv;
        TextView customer_previous_counter_tv;
        TextView customer_current_counter_tv;
        Button btn_submit;
        Button btn_details;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            customer_name_tv = itemView.findViewById(R.id.customer_name_tv);
            customer_subscription_tv = itemView.findViewById(R.id.customer_subscription_tv);
            customer_case_tv = itemView.findViewById(R.id.customer_case_tv);
            customer_previous_counter_tv = itemView.findViewById(R.id.customer_previous_counter_tv);
            customer_current_counter_tv = itemView.findViewById(R.id.customer_current_counter_tv);
            btn_submit = itemView.findViewById(R.id.btn_submit_customer);
            btn_details = itemView.findViewById(R.id.btn_customer_details);
        }
    }


}
