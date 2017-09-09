import util.Utils;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Created by yb on 2017/9/9 0009.
 */
public class BadmintonCourtImpl implements BadmintonCourtInterface {

    Map<String, List<Order>> orders;
//    List<Order>[] orders;
    static String invCmd = "Invalid Command";
    public void book(String books) {
        String [] bookStr = books.split(" ");
        if (bookStr.length == 4) {
            String[] timeSplit = bookStr[2].split("~");
            if (timeSplit.length != 2) {
                System.out.println(invCmd);
                return;
            }
            int start = Utils.parseHour(timeSplit[0]);
            int end = Utils.parseHour(timeSplit[1]);
            if (start < 0 || end < 0) {
                System.out.println("Time Invalid");
                return;
            }
            LocalDate localDate = Utils.parseLocalDate(bookStr[1]);

        } else if (bookStr.length == 5) {
            if ("C".equals(bookStr[4])) {

            } else {
                System.out.println(invCmd);
            }
        } else {
            System.out.println(invCmd);
        }
    }

    public void cancelBook(String books) {

    }

}
