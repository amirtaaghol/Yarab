package com.yarab.application.Fragments;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;


import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.yarab.application.Models.Customer;
import com.yarab.application.PersianCalender.PersianCalendar;
import com.yarab.application.R;
import com.yarab.application.Utils.DatabaseHelper;
import com.yarab.application.Utils.ExcelHelper;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ImportExportFragment extends Fragment implements View.OnClickListener {


    Button btn_import_from_net,
            btn_import_from_excel,
            btn_export_to_net,
            btn_export_to_excel;

    DatabaseHelper databaseHelper;

    private final static int PERMISSION_REQUEST_MEMORY_ACCESS = 0;
    private static String fileType = "";
    private static final String extensionXLS = "XLS";
    private static final String extensionXLSX = "XLSX";
    ActivityResultLauncher<Intent> filePicker;

    ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        filePicker = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        Uri uri = intent.getData();
                        importFromExcel(getActivity(), uri);
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_import_export, container, false);
        initViews(view);
        databaseHelper = new DatabaseHelper(requireContext());
        return view;
    }

    private void initViews(View view) {
        btn_import_from_net = view.findViewById(R.id.btn_import_from_internet);
        btn_import_from_excel = view.findViewById(R.id.btn_import_from_excel);
        btn_export_to_net = view.findViewById(R.id.btn_export_to_internet);
        btn_export_to_excel = view.findViewById(R.id.btn_export_to_excel);
        btn_import_from_net.setOnClickListener(this);
        btn_import_from_excel.setOnClickListener(this);
        btn_export_to_net.setOnClickListener(this);
        btn_export_to_excel.setOnClickListener(this);
    }

    private boolean checkPermission() {

        if (ActivityCompat.checkSelfPermission(requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            new AlertDialog.Builder(getContext()).setCancelable(false)
                    .setMessage("برای خواندن فایل اکسل نیازمند دسترسی به حافظه می باشد").setPositiveButton("قبول", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            requestStoragePermission();
                        }
                    }).show();
        }
        return false;
    }

    public void importFromExcel(Context context, Uri uri) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("waiting");
        builder.setCancelable(false);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        try {
            InputStream inputStream;
            Workbook wb = null;
            try {
                inputStream = context.getContentResolver().openInputStream(uri);

                if (fileType.equals(extensionXLS))
                    wb = new HSSFWorkbook(inputStream);
                else if (fileType.equals(extensionXLSX))
                    wb = new XSSFWorkbook(inputStream);
                inputStream.close();
            } catch (IOException e) {
                Log.i("Import Fragment:", "importFromExcel failed " + e.getMessage());
            }

            Workbook finalWb = wb;


            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    databaseHelper = new DatabaseHelper(getContext());
                    Sheet sheet1 = finalWb.getSheetAt(0);

                    databaseHelper.open();
                    databaseHelper.delete();
                    databaseHelper.close();
                    databaseHelper.open();
                    ExcelHelper.importFromExcel(databaseHelper, sheet1);
                    databaseHelper.close();



                    requireActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            alertDialog.dismiss();
                            Toast.makeText(requireContext(), "عملیات با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });


        } catch (Exception exception) {
            Log.i("Import Fragment:", "importFromExcel failed " + exception.getMessage());

        }
    }

    private void chooseFile() {
        try {
            Intent fileIntent = new Intent(Intent.ACTION_GET_CONTENT);
            fileIntent.addCategory(Intent.CATEGORY_OPENABLE);

            if (fileType == extensionXLS)
                fileIntent.setType("application/vnd.ms-excel");
            else
                fileIntent.setType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

            filePicker.launch(fileIntent);
        } catch (Exception e) {
            Log.i("chooseFile:  error", e.getMessage());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_MEMORY_ACCESS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openFilePicker();
            } else {
                Toast.makeText(getContext(), "No Permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void requestStoragePermission() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_MEMORY_ACCESS);
        }
    }


    public void openFilePicker() {
        try {
            if (checkPermission()) {
                chooseFile();
            }
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void exportExcel() {

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setMessage("waiting");
        builder.setCancelable(false);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                //
                List<Customer> customerList = databaseHelper.getAllCustomers();
                PersianCalendar persianCalendar = new PersianCalendar();
                persianCalendar.setDelimiter("_");

                File path = new File(requireActivity().getExternalFilesDir(null).getAbsolutePath(), "Export");
                File file = new File(path, "Export_" + persianCalendar.getPersianShortDateTimeWithDelimiter() + ".xlsx");

                if (!path.exists())
                    path.mkdirs();

                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    XSSFWorkbook workbook = ExcelHelper.exportToExcel(customerList);
                    workbook.write(fos);
                    fos.flush();
                    fos.close();
                    //
                    Uri uri = Uri.fromFile(file);
                    Intent sendIntent = new Intent(Intent.ACTION_SEND);
                    sendIntent.setData(uri);
                    startActivity(Intent.createChooser(sendIntent, "اشتراگ گذاری فایل از طریق "));


                } catch (IOException e) {
                    e.printStackTrace();
                }
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "فایل در مسیر" + "\n" + file.getAbsolutePath() + "\n" + "ذخیره شد", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });

            }
        });

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_import_from_internet) {

        } else if (id == R.id.btn_import_from_excel) {

            showAlertDialog();

        } else if (id == R.id.btn_export_to_internet) {

        } else if (id == R.id.btn_export_to_excel) {
            exportExcel();
        }
    }


    private void showAlertDialog() {
        Dialog dialog = new Dialog(requireContext());

        dialog.setContentView(R.layout.layout_import_alert_dialog);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_splash_dialog);
        dialog.setCancelable(false);

        dialog.findViewById(R.id.import_dialog_btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fileType = extensionXLSX;
                dialog.dismiss();
                openFilePicker();
            }
        });

        dialog.findViewById(R.id.import_dialog_btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.9);
        dialog.getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }


}