package com.yarab.application.PersianCalender;

/* loaded from: classes.dex */
public class PersianDateParser {
    private String dateString;
    private String delimiter;

    private String normalizeDateString(String str) {
        return str;
    }

    public PersianDateParser(String str) {
        this.delimiter = "/";
        this.dateString = str;
    }

    public PersianDateParser(String str, String str2) {
        this(str);
        this.delimiter = str2;
    }

    public PersianCalendar getPersianDate() {
        checkDateStringInitialValidation();
        String[] splitDateString = splitDateString(normalizeDateString(this.dateString));
        int parseInt = Integer.parseInt(splitDateString[0]);
        int parseInt2 = Integer.parseInt(splitDateString[1]);
        int parseInt3 = Integer.parseInt(splitDateString[2]);
        checkPersianDateValidation(parseInt, parseInt2, parseInt3);
        PersianCalendar persianCalendar = new PersianCalendar();
        persianCalendar.setPersianDate(parseInt, parseInt2, parseInt3);
        return persianCalendar;
    }

    private void checkPersianDateValidation(int i, int i2, int i3) {
        if (i >= 1) {
            if (i2 < 1 || i2 > 12) {
                throw new RuntimeException("month is not valid");
            }
            if (i3 < 1 || i3 > 31) {
                throw new RuntimeException("day is not valid");
            }
            if (i2 > 6 && i3 == 31) {
                throw new RuntimeException("day is not valid");
            }
            if (i2 != 12 || i3 != 30 || PersianCalendarUtils.isPersianLeapYear(i)) {
                return;
            }
            throw new RuntimeException("day is not valid " + i + " is not a leap year");
        }
        throw new RuntimeException("year is not valid");
    }

    private String[] splitDateString(String str) {
        String[] split = str.split(this.delimiter);
        if (split.length == 3) {
            return split;
        }
        throw new RuntimeException("wrong date:" + str + " is not a Persian Date or can not be parsed");
    }

    private void checkDateStringInitialValidation() {
        if (this.dateString != null) {
            return;
        }
        throw new RuntimeException("input didn't assing please use setDateString()");
    }

    public String getDateString() {
        return this.dateString;
    }

    public void setDateString(String str) {
        this.dateString = str;
    }

    public String getDelimiter() {
        return this.delimiter;
    }

    public void setDelimiter(String str) {
        this.delimiter = str;
    }
}
