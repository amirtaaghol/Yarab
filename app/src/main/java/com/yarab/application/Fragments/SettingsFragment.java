package com.yarab.application.Fragments;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.yarab.application.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.security.auth.login.LoginException;


public class SettingsFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "Settings Fragment :";

    Button btn_account_title, btn_bluetooth_title, btn_about_us_title;
    EditText username, password;
    Button btn_sign_in;
    Button btn_pair;
    SwitchCompat mSwitch;
    Spinner spinner;

    private final int REQ_EN_BT = 12;

    private List<String> pairedAddresses;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IntentFilter bt = new IntentFilter(BluetoothDevice.ACTION_FOUND);

        requireActivity().registerReceiver(btReceiver, bt);

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (bluetoothAdapter == null) {
            Toast.makeText(requireContext(), "Your device does not support Bluetooth", Toast.LENGTH_SHORT).show();
        }

        if (bluetoothAdapter != null && !bluetoothAdapter.isEnabled()) {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, REQ_EN_BT);
        }

        if (bluetoothAdapter != null && checkPermission()) {
            Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

            if (pairedDevices.size() > 0) {
                pairedAddresses = new ArrayList<>();
                for (BluetoothDevice device : pairedDevices) {
                    pairedAddresses.add(device.getAddress());
                }
            }
//            bluetoothAdapter.cancelDiscovery();
        }

    }

    private final BroadcastReceiver btReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                if (checkPermission()) {
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    String deviceName = device.getName();
                    String deviceAddress = device.getAddress();
                }
            }
        }
    };

    private boolean checkPermission() {
        // might need permission bluetooth connect
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            new AlertDialog.Builder(requireContext()).setMessage("نیاز به دسترسی بلوتوث").setCancelable(false)
                    .setPositiveButton("تایید", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            requestPermission();
                        }
                    }).show();
            return false;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        requireActivity().unregisterReceiver(btReceiver);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQ_EN_BT) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                ///
            } else {
                Toast.makeText(requireContext(), "دسترسی مورد نیاز داده نشد", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void requestPermission() {
        // might need permission bluetooth connect
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.BLUETOOTH}, REQ_EN_BT);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_EN_BT && resultCode == Activity.RESULT_OK) {
            Log.i(TAG, "onActivityResult: ok");
        }

        if (requestCode == REQ_EN_BT && resultCode == Activity.RESULT_CANCELED) {
            Log.i(TAG, "onActivityResult: canceled");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        initViews(v);
        return v;
    }

    private void initViews(View v) {
        btn_account_title = v.findViewById(R.id.setting_fragment_account_title_button);
        btn_bluetooth_title = v.findViewById(R.id.setting_fragment_bluetooth_title_button);
        btn_about_us_title = v.findViewById(R.id.setting_fragment_about_us_title_button);
        //
        username = v.findViewById(R.id.setting_fragment_username_edit_text);
        password = v.findViewById(R.id.setting_fragment_password_edit_text);
        btn_sign_in = v.findViewById(R.id.setting_fragment_btn_sign_in);
        //
        btn_pair = v.findViewById(R.id.setting_fragment_btn_pair);
        mSwitch = v.findViewById(R.id.setting_fragment_switch);
        spinner = v.findViewById(R.id.setting_fragment_spinner);

        btn_sign_in.setOnClickListener(this);

        btn_pair.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //
        if (v.getId() == R.id.setting_fragment_btn_sign_in) {

            if (username.getText().toString().isEmpty()) {
                username.requestFocus();
                username.setError("نام کاربری را وارد کنید");
            } else if (password.getText().toString().isEmpty()) {
                password.requestFocus();
                password.setError("پسورد را وارد کنید");
            }
            //
        } else if (v.getId() == R.id.setting_fragment_btn_pair) {
            //
        }

    }


}