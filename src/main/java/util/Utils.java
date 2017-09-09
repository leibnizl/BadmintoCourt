package util;

/**
 * Created by yb on 2017/9/9 0009.
 */
public class Utils {
    public static boolean isClockSharp0(String hourStr, String minuteStr) {
        int hour = Integer.parseInt(hourStr);
        if (hour > 24 || hour < 0) {
            System.out.println("Time Invalid");
            return false;
        }
        int minute = Integer.parseInt(minuteStr);
        if (minute != 0) {
            System.out.println("Time Invalid");
            return false;
        } else {
            return true;
        }
    }
    public static boolean isClockSharp(String time) {
        String[] tmp = time.split("");
        if (tmp.length != 2) {
            System.out.println("Time Invalid");
            return false;
        } else {
            return isClockSharp0(tmp[0], tmp[1]);
        }
    }



}
