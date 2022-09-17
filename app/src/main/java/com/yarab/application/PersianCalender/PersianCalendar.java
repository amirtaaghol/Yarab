package com.yarab.application.PersianCalender;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/* loaded from: classes.dex */
public class PersianCalendar extends GregorianCalendar {
    private static final long serialVersionUID = 5541422440580682494L;
    private String delimiter = "/";
    private int persianDay;
    private int persianMonth;
    private int persianYear;

    private long convertToMilis(long j) {
        return (j * 86400000) + PersianCalendarConstants.MILLIS_JULIAN_EPOCH + PersianCalendarUtils.ceil(getTimeInMillis() - PersianCalendarConstants.MILLIS_JULIAN_EPOCH, 8.64E7d);
    }

    public PersianCalendar(long j) {
        setTimeInMillis(j);
    }

    public PersianCalendar() {
        setTimeInMillis(Calendar.getInstance().getTimeInMillis());
    }

    protected void calculatePersianDate() {
        long julianToPersian = PersianCalendarUtils.julianToPersian(((long) Math.floor(getTimeInMillis() - PersianCalendarConstants.MILLIS_JULIAN_EPOCH)) / 86400000);
        long j = julianToPersian >> 16;
        int i = ((int) (65280 & julianToPersian)) >> 8;
        int i2 = (int) (julianToPersian & 255);
        if (j <= 0) {
            j--;
        }
        this.persianYear = (int) j;
        this.persianMonth = i;
        this.persianDay = i2;
    }

    public boolean isPersianLeapYear() {
        return PersianCalendarUtils.isPersianLeapYear(this.persianYear);
    }

    public void setPersianDate(int i, int i2, int i3) {
        this.persianYear = i;
        this.persianMonth = i2 + 1;
        this.persianDay = i3;
        setTimeInMillis(convertToMilis(PersianCalendarUtils.persianToJulian(this.persianYear > 0 ? this.persianYear : this.persianYear + 1, this.persianMonth - 1, this.persianDay)));
    }

    public int getPersianYear() {
        return this.persianYear;
    }

    public int getPersianMonth() {
        return this.persianMonth;
    }

    public String getPersianMonthName() {
        return PersianCalendarConstants.persianMonthNames[this.persianMonth];
    }

    public int getPersianDay() {
        return this.persianDay;
    }

    public int getPersianDayCountPerMonth() {
        if (this.persianMonth < 7) {
            return 31;
        }
        return ((this.persianMonth <= 6 || this.persianMonth >= 12) && !isPersianLeapYear()) ? 29 : 30;
    }

    public String getPersianWeekDayName() {
        int i = get(7);
        if (i == 7) {
            return PersianCalendarConstants.persianWeekDays[0];
        }
        switch (i) {
            case 1:
                return PersianCalendarConstants.persianWeekDays[1];
            case 2:
                return PersianCalendarConstants.persianWeekDays[2];
            case 3:
                return PersianCalendarConstants.persianWeekDays[3];
            case 4:
                return PersianCalendarConstants.persianWeekDays[4];
            case 5:
                return PersianCalendarConstants.persianWeekDays[5];
            default:
                return PersianCalendarConstants.persianWeekDays[6];
        }
    }

    public String getPersianLongDate() {
        return getPersianWeekDayName() + "  " + this.persianDay + "  " + getPersianMonthName() + "  " + this.persianYear;
    }

    public String getPersianLongDateAndTime() {
        return getPersianLongDate() + " ساعت " + get(11) + ":" + get(12) + ":" + get(13);
    }

    public String getPersianShortDate() {
        return "" + formatToMilitary(this.persianYear) + this.delimiter + formatToMilitary(getPersianMonth() + 1) + this.delimiter + formatToMilitary(this.persianDay);
    }

    public String getPersianShortDateTime() {
        return "" + formatToMilitary(this.persianYear) + this.delimiter + formatToMilitary(getPersianMonth() + 1) + this.delimiter + formatToMilitary(this.persianDay) + " " + formatToMilitary(get(11)) + ":" + formatToMilitary(get(12)) + ":" + formatToMilitary(get(13));
    }

    public String getPersianShortDateTimeWithDelimiter() {
        return "" + formatToMilitary(this.persianYear) + this.delimiter + formatToMilitary(getPersianMonth() + 1) + this.delimiter + formatToMilitary(this.persianDay) + this.delimiter + formatToMilitary(get(11)) + this.delimiter + formatToMilitary(get(12)) + this.delimiter + formatToMilitary(get(13));
    }

    private String formatToMilitary(int i) {
        if (i < 9) {
            return "0" + i;
        }
        return String.valueOf(i);
    }

    public void addPersianDate(int i, int i2) {
        if (i2 == 0) {
            return;
        }
        if (i < 0 || i >= 15) {
            throw new IllegalArgumentException();
        }
        if (i == 1) {
            setPersianDate(this.persianYear + i2, getPersianMonth() + 1, this.persianDay);
        } else if (i == 2) {
            setPersianDate(this.persianYear + (((getPersianMonth() + 1) + i2) / 12), ((getPersianMonth() + 1) + i2) % 12, this.persianDay);
        } else {
            add(i, i2);
            calculatePersianDate();
        }
    }

    public void parse(String str) {
        PersianCalendar persianDate = new PersianDateParser(str, this.delimiter).getPersianDate();
        setPersianDate(persianDate.getPersianYear(), persianDate.getPersianMonth(), persianDate.getPersianDay());
    }

    public String getDelimiter() {
        return this.delimiter;
    }

    public void setDelimiter(String str) {
        this.delimiter = str;
    }

    @Override // java.util.Calendar
    public String toString() {
        String gregorianCalendar = super.toString();
        return gregorianCalendar.substring(0, gregorianCalendar.length() - 1) + ",PersianDate=" + getPersianShortDate() + "]";
    }

    @Override // java.util.GregorianCalendar, java.util.Calendar
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // java.util.GregorianCalendar, java.util.Calendar
    public int hashCode() {
        return super.hashCode();
    }

    @Override // java.util.Calendar
    public void set(int i, int i2) {
        super.set(i, i2);
        calculatePersianDate();
    }

    @Override // java.util.Calendar
    public void setTimeInMillis(long j) {
        super.setTimeInMillis(j);
        calculatePersianDate();
    }

    @Override // java.util.GregorianCalendar, java.util.Calendar
    public void setTimeZone(TimeZone timeZone) {
        super.setTimeZone(timeZone);
        calculatePersianDate();
    }
}
