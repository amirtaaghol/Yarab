package com.yarab.application.PersianCalender;

/* loaded from: classes.dex */
public class PersianCalendarUtils {
    public static long persianToJulian(long j, int i, int i2) {
        double d = j - 474;
        double ceil = ((ceil(d, 2820.0d) + 474) * 682) - 110;
        Double.isNaN(ceil);
        Double.isNaN(d);
        return (((ceil(d, 2820.0d) + 474) - 1) * 365) + ((long) Math.floor(ceil / 2816.0d)) + 1948320 + (((long) Math.floor(d / 2820.0d)) * 1029983) + (i < 7 ? i * 31 : (i * 30) + 6) + i2;
    }

    public static boolean isPersianLeapYear(int i) {
        double ceil = ceil(i - 474, 2820.0d) + 474;
        Double.isNaN(ceil);
        return ceil((ceil + 38.0d) * 682.0d, 2816.0d) < 682;
    }

    public static long julianToPersian(long j) {
        long j2;
        double d;
        double d2;
        double persianToJulian = j - persianToJulian(475L, 0, 1);
        long ceil = ceil(persianToJulian, 1029983.0d);
        if (ceil != 1029982) {
            double d3 = ceil;
            Double.isNaN(d3);
            j2 = (long) Math.floor(((d3 * 2816.0d) + 1031337.0d) / 1028522.0d);
        } else {
            j2 = 2820;
        }
        Double.isNaN(persianToJulian);
        long floor = (((long) Math.floor(persianToJulian / 1029983.0d)) * 2820) + 474 + j2;
        long persianToJulian2 = (j + 1) - persianToJulian(floor, 0, 1);
        if (persianToJulian2 > 186) {
            d = persianToJulian2 - 6;
            d2 = 30.0d;
        } else {
            d = persianToJulian2;
            d2 = 31.0d;
        }
        Double.isNaN(d);
        int ceil2 = (int) (Math.ceil(d / d2) - 1.0d);
        return ((int) (j - (persianToJulian(floor, ceil2, 1) - 1))) | (floor << 16) | (ceil2 << 8);
    }

    public static long ceil(double d, double d2) {
        return (long) (d - (d2 * Math.floor(d / d2)));
    }
}
