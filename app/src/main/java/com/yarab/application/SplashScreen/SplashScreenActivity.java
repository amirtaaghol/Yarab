package com.yarab.application.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.yarab.application.MainActivity;
import com.yarab.application.R;
import com.yarab.application.Utils.InternetHelper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SplashScreenActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    ExecutorService executorService = Executors.newSingleThreadExecutor();

    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        progressBar = findViewById(R.id.splash_pb);
        executorService.execute(() -> check());
    }

    public void check() {
        if (InternetHelper.isOnline()) {
            handler.postDelayed(() -> {
                startActivity(new Intent(getBaseContext() , MainActivity.class));
                finish();
            },100);
        } else {
            runOnUiThread(() -> {
                progressBar.setVisibility(View.GONE);
                showAlertDialog();
            });
        }
    }

    private void showAlertDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_splash_dialog);
        dialog.findViewById(R.id.dialog_btn_exit).setOnClickListener(view -> System.exit(0));
        dialog.findViewById(R.id.dialog_btn_retry).setOnClickListener(view -> {
            progressBar.setVisibility(View.VISIBLE);
            check();
            dialog.dismiss();
        });
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_splash_dialog);
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
        dialog.getWindow().setLayout(width , ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }



}