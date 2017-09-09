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
//                return;
            } else {
                if (Utils.isClockSharp(timeSplit[0]) && Utils.isClockSharp(timeSplit[1])) {
                    int start = Integer.parseInt(timeSplit[0].split(":")[0]);
                    int end = Integer.parseInt(timeSplit[0].split(":")[0]);
                    LocalDate localDate = new LocalDate(bookStr[1])
                    Order order = new Order(bookStr[0], )
                }
            }
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
