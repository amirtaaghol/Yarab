package com.yarab.application.Fragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.yarab.application.R;


public class PairDialogFragment extends DialogFragment {


    ListView listView ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = new Dialog(requireContext());

        dialog.setContentView(R.layout.fragment_pair_dialog);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        initViews(dialog);
        return dialog;
    }

    private void initViews(Dialog dialog) {
        listView = dialog.findViewById(R.id.pair_dialog_fragment_lv);
    }

    @Override
    public void onResume() {
        super.onResume();
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.99);
        requireDialog().getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        requireDialog().getWindow().setBackgroundDrawableResource(R.drawable.background_splash_dialog);

    }
}