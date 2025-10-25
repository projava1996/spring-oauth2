package com.authorizationserver.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

@Slf4j
public class DateUtils {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

    public static final String DATE_FORMAT_DD_MM_YYYY = "dd/MM/yyyy";
    public static String FORMAT_DDMMYYYY = "dd/MM/yyyy";
    public static String FORMAT_YYYYMMDD = "yyyyMMdd";
    public static String FORMAT_YYYYMMDD_HHmmss = "dd/MM/yyyy HH:mm:ss";
    public static String FORMAT_YYYYMMDD_HHmm = "dd/MM/yyyy HH:mm";

    public static String FORMAT_YYYYMMDD_HHmmss_2 = "yyyy-MM-dd HH:mm:ss";

    protected static final int YEAR_OFFSET = 100;

    public static Date floor(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.AM_PM, Calendar.AM);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date ceil(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.set(Calendar.AM_PM, Calendar.PM);
        cal.set(Calendar.HOUR, 11);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    public static String toString(Date date, String pattern) {
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        return sf.format(date);
    }

    public static String nowDate() {
        Date date = new Date();
        return DateUtils.convertDateToString(date, "dd/MM/yyyy");
    }

    public static String nowDateDetail(Date date) {
        if (date == null) {
            date = new Date();
        }
        return DateUtils.convertDateToString(date, "dd/MM/yyyy hh:mm:ss");
    }

    public static String convertDateToString(Date date) {
        String formattedDate = dateFormat.format(date);
        return formattedDate;

    }

    public static String convertDateToString(Date date,
                                             String datePattern) {
        try {
            DateFormat fmt = new SimpleDateFormat(datePattern);
            return fmt.format(date);
        } catch (Exception e) {
            return "";
        }
    }

    public static String convertLocalDateDateToString(LocalDate date,
                                                      String datePattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
            return date.format(formatter);
        } catch (Exception e) {
            return "";
        }
    }

    public static String convertLocalDateDateTimeToString(LocalDateTime date,
                                                          String datePattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
            return date.format(formatter);
        } catch (Exception e) {
            return "";
        }
    }

    public static String convertLocalDateDateTimeToString(LocalDate date,
                                                          String datePattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
            return date.format(formatter);
        } catch (Exception e) {
            return "";
        }
    }

    public static String toUTC(Date date) {
        return toString(date, "yyyy-MM-dd'T'HH:mm:ss");
    }

    public static Date string2Date(String date, String dateFormat) {
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        try {
            return df.parse(date);
        } catch (ParseException e) {
            System.out.printf("Unable to parse date: %s\n", date);
            return null;
        }
    }

    public static Date currentDate() {
        return new Date(System.currentTimeMillis());
    }

    public static String date2Str(Date date) {
        if (date == null) {
            return null;
        } else {
            String format = "yyyy-MM-dd";
            DateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(date);
        }
    }

    public static String date2StrFormat(Date date, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static String date2StrFormat(Timestamp date, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static Date str2Date(String dateStr) throws ParseException {
        String format = "yyyy-MM-dd";
        return (new SimpleDateFormat(format)).parse(dateStr);
    }

    public static Date str2DateFormat(String dateStr, String format) throws ParseException {
        return (new SimpleDateFormat(format)).parse(dateStr);
    }

    public static Calendar calendarFor(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(1, year);
        cal.set(2, month);
        cal.set(5, day);
        return cal;
    }

    public static int getYear() {
        Calendar cal = Calendar.getInstance();
        return cal.get(1);
    }

    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(1);
    }

    public static Date plusDate(Date startDate, int date) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(5, date);
        return cal.getTime();
    }

    public static Date plusMinute(Date startDate, int minute) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(12, minute);
        return cal.getTime();
    }

    public static Date plusMonth(Date startDate, int month) {
        if (startDate == null) {
            startDate = new Date();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(2, month);
        return cal.getTime();
    }

    public static Calendar getLastDayPreviousMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(2);
        int year = cal.get(1);
        int day = 1;
        Calendar lastMonthCal = calendarFor(year, month, day);
        lastMonthCal.add(5, -1);
        return lastMonthCal;
    }

    public static String getMonthVn(int month) {
        return month < 10 ? "0" + month : "" + month;
    }

    public static Date CalendarToDate(Calendar cal) {
        Date date = cal.getTime();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = format1.format(date);
        Date inActiveDate = null;
        try {
            inActiveDate = format1.parse(date1);
        } catch (ParseException var6) {
            ParseException e1 = var6;
            e1.printStackTrace();
        }
        return inActiveDate;
    }

    public static String CalendarToString(Calendar cal) {
        Date date = cal.getTime();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        return format1.format(date);
    }

    public static Date ngayDauNam(Integer nam) {
        return CalendarToDate(calendarFor(nam, 0, 1));
    }

    public static LocalDate convertToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static Integer subtractDate(Date date1, Date date2) {
        LocalDate localDate1 = convertToLocalDate(date1);
        LocalDate localDate2 = convertToLocalDate(date2);
        Period period = Period.between(localDate2, localDate1);
        return period.getDays();
    }

    public static Integer subtract(Date dt1, Date dt2) {
        long diff = Math.abs(dt1.getTime() - dt2.getTime());
        long diffDays = diff / 86400000L;
        return Integer.valueOf(String.valueOf(diffDays));
    }

    public static Long minusDate(Date date1, Date date2) {
        return date1 != null && date2 != null ? TimeUnit.DAYS.convert(date1.getTime() - date2.getTime(), TimeUnit.MILLISECONDS) : null;
    }

    public static XMLGregorianCalendar getXMLGregorianCalendar(String date, String format) throws ParseException, DatatypeConfigurationException {
        DateFormat dformat = new SimpleDateFormat(format);
        Date dDate = dformat.parse(date);
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(dDate);
        XMLGregorianCalendar xmlGregCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        return xmlGregCal;
    }

    public static Date convertStringToDate(String strDate, String pattern) {
        if (CommonUtil.isNullOrEmpty(strDate)) {
            return null;
        } else {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
                return dateFormat.parse(strDate);
            } catch (ParseException var3) {
                ParseException e = var3;
                e.printStackTrace();
                return null;
            }
        }
    }

    public static int calculateAge(Date birthDate) {
        if (birthDate == null) {
            return 0;
        } else {
            LocalDate currentDate = convertToLocalDate(new Date());
            LocalDate birthday = convertToLocalDate(birthDate);
            return Period.between(birthday, currentDate).getYears();
        }
    }

    public static Date getDateWithoutTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTime();
    }

    public static String date2StringByPattern(Date date, String pattern) {
        if (date != null && !CommonUtil.isNullOrEmpty(pattern)) {
            DateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        } else {
            return null;
        }
    }

    public static LocalDateTime getStartTimeOfTheDay(LocalDate startDate) {
        var dateFrom = startDate != null ? startDate.atStartOfDay() : LocalDateTime.now();
        return dateFrom;
    }

    public static LocalDateTime getEndTimeOfTheDay(LocalDate endDate) {
        var dateTo = endDate != null ? LocalDateTime.of(endDate, LocalTime.MAX) : LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.MAX);
        return dateTo;
    }

    public static String getStringDateByFormat(Date date, String format) {
        if (date == null) {
            return null;
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            return formatter.format(date);
        }
    }

    public static Timestamp plusMonth(Timestamp date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(2, months);
        return new Timestamp(cal.getTime().getTime());
    }

    public static Timestamp plusDay(Timestamp date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(5, days);
        return new Timestamp(cal.getTime().getTime());
    }

    public static Timestamp plusYear(Timestamp date, int years) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(1, years);
        return new Timestamp(cal.getTime().getTime());
    }

    public static XMLGregorianCalendar toXmlGregorianCalendar(final Date date) {
        return toXmlGregorianCalendar(date.getTime());
    }

    public static XMLGregorianCalendar toXmlGregorianCalendar(final Timestamp date) {
        return toXmlGregorianCalendar(date.getTime());
    }

    public static XMLGregorianCalendar toXmlGregorianCalendar(final long date) {
        try {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTimeInMillis(date);
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        } catch (DatatypeConfigurationException var3) {
            System.out.println("Unable to convert date '%s' to an XMLGregorianCalendar object");
            return null;
        }
    }

    public static Timestamp toTimestamp(java.sql.Date date) {
        return new Timestamp(date.getTime());
    }

    public static Timestamp toTimestamp(Date date) {
        return new Timestamp(date.getTime());
    }

    public static LocalDate getFromDateSearchFromQueryParam(String fromDate) {
        var fromDateValid = LocalDate.now().minusYears(YEAR_OFFSET);
        if (!StringUtils.isBlank(fromDate)) {
            try {
                fromDateValid = LocalDate.parse(fromDate, DateTimeFormatter.ofPattern(DateUtils.DATE_FORMAT_DD_MM_YYYY));
            } catch (DateTimeParseException e) {
                log.info("convert data error: {}", e.getMessage());
            }
        }
        return fromDateValid;
    }

    public static LocalDate getToDateSearchFromQueryParam(String toDate) {
        var toDateValid = LocalDate.now().plusYears(YEAR_OFFSET);
        if (!StringUtils.isBlank(toDate)) {
            try {
                toDateValid = LocalDate.parse(toDate, DateTimeFormatter.ofPattern(DateUtils.DATE_FORMAT_DD_MM_YYYY));
            } catch (DateTimeParseException e) {
                log.info("convert data error: {}", e.getMessage());
            }
        }
        return toDateValid;
    }

    public static LocalDate string2LocalDate(String toDate) {
        if (StringUtils.isBlank(toDate)) {
            return null;
        }
        try {
            var toDateValid = LocalDate.parse(toDate, DateTimeFormatter.ofPattern(DateUtils.DATE_FORMAT_DD_MM_YYYY));
            return toDateValid;
        } catch (DateTimeParseException e) {
            System.out.println("Unable to convert date '%s' to a LocalDate object" + e.getMessage());
            return null;
        }
    }

    public static LocalDate string2LocalDate(String toDate, String format) {
        if (StringUtils.isBlank(toDate)) {
            return null;
        }
        try {
            var toDateValid = LocalDate.parse(toDate, DateTimeFormatter.ofPattern(format));
            return toDateValid;
        } catch (DateTimeParseException e) {
            System.out.println("Unable to convert date '%s' to a LocalDate object" + e.getMessage());
            return null;
        }
    }

    public static boolean isOlderThanHours(OffsetDateTime createdTime, int i) {
        if (createdTime == null) {
            return false;
        }
        OffsetDateTime now = OffsetDateTime.now();
        long diffInHours = Duration.between(createdTime, now).toHours();
        return diffInHours > i;
    }

    public static boolean isInLastBusinessDays(OffsetDateTime createdTime, int i) {
        if (createdTime == null) {
            return false;
        }
        long diffInDays = Duration.between(createdTime, getLastNumberBusinessDay(i)).toDays();
        return diffInDays <= i && diffInDays >= 0;
    }

    public static OffsetDateTime getLastNumberBusinessDay(int i) {
        OffsetDateTime date = OffsetDateTime.now();
        int businessDaysCount = 0;

        // Go back day by day until we've found i business days
        while (businessDaysCount < i) {
            date = date.minusDays(1);

            if (!(date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                businessDaysCount++;
            }
        }

        return date;
    }

    public static boolean isToday(OffsetDateTime createdTime) {
        if (createdTime == null) {
            return false;
        }
        OffsetDateTime now = OffsetDateTime.now();
        return createdTime.getYear() == now.getYear() &&
                createdTime.getMonth() == now.getMonth() &&
                createdTime.getDayOfMonth() == now.getDayOfMonth();
    }

    public static boolean isInLast24Hours(OffsetDateTime createdTime) {
        if (createdTime == null) {
            return false;
        }
        OffsetDateTime now = OffsetDateTime.now();
        long diffInHours = Duration.between(createdTime, now).toHours();
        return diffInHours <= 24 && diffInHours >= 0;
    }

    public static boolean isAcknowledgedInMoreThan30Minutes(OffsetDateTime createdTime, OffsetDateTime dueDate) {
        if (createdTime == null || dueDate == null) {
            return false;
        }
        long diffInMinutes = Duration.between(createdTime, dueDate).toMinutes();
        return diffInMinutes > 30;
    }

    public static boolean isNotAcknowledged(OffsetDateTime createdTime, OffsetDateTime dueDate) {
        if (createdTime == null) {
            return false;
        }
        if (dueDate == null) {
            return true;
        }
        long diffInMinutes = Duration.between(createdTime, dueDate).toMinutes();
        return diffInMinutes < 0; // Not acknowledged if the due date is before the created time
    }

    public static LocalDateTime stringToLocalDateTime(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        try {
            return LocalDateTime.parse(value, DateTimeFormatter.ofPattern(FORMAT_YYYYMMDD_HHmmss_2));
        } catch (DateTimeParseException e) {
            System.out.println("Unable to convert date '%s' to a LocalDateTime object" + e.getMessage());
            return null;
        }
    }

    public static Date localDateToDate(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static String displayTimeZone(TimeZone tz) {

        long hours = TimeUnit.MILLISECONDS.toHours(tz.getRawOffset());
        long minutes = TimeUnit.MILLISECONDS.toMinutes(tz.getRawOffset())
                - TimeUnit.HOURS.toMinutes(hours);
        // avoid -4:-30 issue
        minutes = Math.abs(minutes);

        String result = "";
        if (hours > 0) {
            result = String.format("(GMT+%d:%02d) %s", hours, minutes, tz.getID());
        } else {
            result = String.format("(GMT%d:%02d) %s", hours, minutes, tz.getID());
        }

        return result;
    }
}