package util;

import java.time.LocalDate;
import java.time.Month;



/**
 * Created by yb on 2017/9/9 0009.
 */
public class Utils {

    public static final String errorMsg = "> Error: the booking is invalid!";
    public static final String successMsg = "> Success: the booking is accepted!";

    public static boolean isClockSharp0(String hourStr, String minuteStr) {
        int hour = Integer.parseInt(hourStr);
        if (hour > 24 || hour < 0) {
//            System.out.println("> Error: the time of booking is invalid");
            System.out.println(errorMsg);
            return false;
        }
        int minute = Integer.parseInt(minuteStr);
        if (minute != 0) {
//            System.out.println("> Error: the time of booking is invalid");
            System.out.println(errorMsg);
            return false;
        } else {
            return true;
        }
    }
    public static boolean isClockSharp(String time) {
        String[] tmp = time.split(":");
        if (tmp.length != 2) {
//            System.out.println("> Error: the time of booking is invalid");
            System.out.println(errorMsg);
            return false;
        } else {
            return isClockSharp0(tmp[0], tmp[1]);
        }
    }


    public static int parseHour(String time) {
        String[] timeArray = time.split(":");
        if (timeArray.length != 2) return -1;
        if (Integer.parseInt(timeArray[1]) != 0) return -1;
        return Integer.parseInt(timeArray[0]);
    }

    public static LocalDate parseLocalDate(String date) {
        LocalDate localDate = null;
        String[] dateArr = date.split("-");
        if (dateArr.length != 3) {
            return null;
        }
        try {
            int year = Integer.parseInt(dateArr[0]);
            int month = Integer.parseInt(dateArr[1]);
            int day = Integer.parseInt(dateArr[2]);
            Month month1 = Month.of(month);

            localDate = LocalDate.of(year, month1, day);
        } catch (Exception e) {
            localDate = null;
        } finally {
            return localDate;
        }

    }


}
