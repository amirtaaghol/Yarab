package com.yarab.application.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.yarab.application.Models.Customer;
import com.yarab.application.PersianCalender.PersianCalendar;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {


    public static void importFromExcel(DatabaseHelper databaseHelper, Sheet sheet) {

        for (Iterator<Row> rowIterator = sheet.rowIterator(); rowIterator.hasNext(); ) {

            sheet.setRightToLeft(true);
            Row row = rowIterator.next();

            ContentValues values = new ContentValues();

            row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.NUMERIC);
            row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.NUMERIC);

            row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);
            row.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.STRING);

            row.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.NUMERIC);
            row.getCell(9, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.NUMERIC);
            row.getCell(10, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.NUMERIC);
            row.getCell(11, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.NUMERIC);

            row.getCell(12, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.NUMERIC);
            row.getCell(13, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.NUMERIC);
            row.getCell(14, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.NUMERIC);
            row.getCell(15, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.NUMERIC);
            row.getCell(16, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.NUMERIC);
            row.getCell(17, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.NUMERIC);
            row.getCell(18, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.NUMERIC);
            row.getCell(19, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.NUMERIC);
            row.getCell(20, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.NUMERIC);
            row.getCell(21, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellType(CellType.NUMERIC);


            values.put(Customer.KEY_CITY,
                    row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            values.put(Customer.KEY_NAME,
                    row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            values.put(Customer.KEY_CASE_NUMBER,
                    row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue());
            values.put(Customer.KEY_GEOGRAPHIC_NUMBER,
                    row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue());
            values.put(Customer.KEY_APPLICATION,
                    row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            values.put(Customer.KEY_BRANCH_THICKNESS,
                    row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            values.put(Customer.KEY_ADDRESS,
                    row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            values.put(Customer.KEY_BRANCH_STATUS,
                    row.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            values.put(Customer.KEY_COUNTER_BODY_NUMBER,
                    row.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue());
            values.put(Customer.KEY_UNITS_NUMBER,
                    row.getCell(9, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue());
            values.put(Customer.KEY_CAPACITY,
                    row.getCell(10, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue());

            values.put(Customer.KEY_GPS_DEFAULT,
                    row.getCell(11, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue());
            values.put(Customer.KEY_GPS_CURRENT,
                    row.getCell(12, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue());

            values.put(Customer.KEY_BLUETOOTH_VALUE,
                    row.getCell(13, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue());
            values.put(Customer.KEY_PREVIOUS_COUNTER_NUMBER,
                    row.getCell(14, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue());
            values.put(Customer.KEY_CURRENT_COUNTER_NUMBER,
                    row.getCell(15, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue());
            values.put(Customer.KEY_SUBSCRIPTION_NUMBER,
                    row.getCell(16, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue());
            values.put(Customer.KEY_BLOCK_NUMBER,
                    row.getCell(17, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue());
            values.put(Customer.KEY_GROUP_NUMBER,
                    row.getCell(18, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue());
            values.put(Customer.KEY_PLAQUE_NUMBER,
                    row.getCell(19, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue());
            values.put(Customer.KEY_NATIONAL_NUMBER,
                    row.getCell(20, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue());
            values.put(Customer.KEY_PHONE_NUMBER,
                    row.getCell(21, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue());

            try {
                if (databaseHelper.insert(DatabaseHelper.TABLE_CUSTOMERS, values) < 0) {
                    return;
                }
            } catch (OutOfMemoryError error) {

                Log.i("excel helper", "import From Excel failed: " + error.getMessage());
            }
        }
    }


    public static XSSFWorkbook exportToExcel(List<Customer> customerList) {
        XSSFWorkbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("مشترکین");
        sheet.setRightToLeft(true);
        Row row = sheet.createRow(0);

        row.createCell(0).setCellValue("شهر");
        row.createCell(1).setCellValue("نام");
        row.createCell(2).setCellValue("پرونده");
        row.createCell(3).setCellValue("کد جغرافیایی");
        row.createCell(4).setCellValue("کاربری");
        row.createCell(5).setCellValue("قطر انشعاب");
        row.createCell(6).setCellValue("آدرس");
        row.createCell(7).setCellValue("وضعیت انشعاب");
        row.createCell(8).setCellValue("شماره بدنه کنتور");
        row.createCell(9).setCellValue("تعداد واحد");
        row.createCell(10).setCellValue("ظرفیت");
        row.createCell(11).setCellValue("جی پی اس قبلی");
        row.createCell(12).setCellValue("جی پی اس جدید");
        row.createCell(13).setCellValue("بلوتوث");
        row.createCell(14).setCellValue("کنتور قبلی");
        row.createCell(15).setCellValue("کنتور فعلی");
        row.createCell(16).setCellValue("شماره اشتراک");
        row.createCell(17).setCellValue("گروه");
        row.createCell(18).setCellValue("بلوک");
        row.createCell(19).setCellValue("پلاک");
        row.createCell(20).setCellValue("کد ملی");
        row.createCell(21).setCellValue("تلفن");


        int i = 1;
        for (Customer customer : customerList) {
            Row row1 = sheet.createRow(i);
            row1.createCell(0).setCellValue(customer.getCity());
            row1.createCell(1).setCellValue(customer.getName());
            row1.createCell(2).setCellValue(customer.getCase_number());
            row1.createCell(3).setCellValue(customer.getGeographic_number());
            row1.createCell(4).setCellValue(customer.getApplication());
            row1.createCell(5).setCellValue(customer.getBranch_thickness());
            row1.createCell(6).setCellValue(customer.getAddress());
            row1.createCell(7).setCellValue(customer.getBranch_status());
            row1.createCell(8).setCellValue(customer.getCounter_body_number());
            row1.createCell(9).setCellValue(customer.getUnits_number());
            row1.createCell(10).setCellValue(customer.getCapacity());
            row1.createCell(11).setCellValue(customer.getGps_default());
            row1.createCell(12).setCellValue(customer.getGps_current());
            row1.createCell(13).setCellValue(customer.getBluetooth_value());
            row1.createCell(14).setCellValue(customer.getPrevious_counter_number());
            row1.createCell(15).setCellValue(customer.getCurrent_counter_number());
            row1.createCell(16).setCellValue(customer.getSubscription_number());
            row1.createCell(17).setCellValue(customer.getGroup_number());
            row1.createCell(18).setCellValue(customer.getBlock_number());
            row1.createCell(19).setCellValue(customer.getPlaque_number());
            row1.createCell(20).setCellValue(customer.getNational_number());
            row1.createCell(21).setCellValue(customer.getPhone_number());
            i++;
        }
        return workbook;
    }
}
