import util.Utils;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by yb on 2017/9/9 0009.
 */
public class BadmintonCourtImpl implements BadmintonCourt {

    Map<Court, List<Order>> orders;
    Set<String> courtSet;
//    List<Order>[] orders;
    static String invCmd = "Command Invalid";
    public void book(Order order ) {
        List<Order> orderList = orders.get(order.getCourt());
        for (Order order1: orderList) {
            if (isSameDay(order1.getLocalDate(), order.getLocalDate()) && isTimeConflict(order1, order)) {
                System.out.println("Time Conflict");
                return;
            }

        }
        orderList.add(order);
        orders.put(order.getCourt(), orderList);
        System.out.println("book succeed!");
    }

    public void cancelBook(Order order) {
        List<Order> orderList = orders.get(order.getCourt());
        for (Order order1: orderList) {
            if (isSameDay(order1.getLocalDate(), order.getLocalDate())
                    && order1.getStart() == order.getStart()
                    && order1.getEnd() == order.getEnd()) {
                if (!order1.isCanceled()) {
                    order1.setCanceled(true);
                }
            }
        }
    }

    public void printSum() {

    }
    
    private boolean isTimeConflict(Order order1, Order order2) {
        if (order1.getStart() < order2.getStart()) {
            return order1.getEnd() < order2.getStart();
        } else {
            return order2.getEnd() < order1.getStart();
        }
    }

    private boolean isTimeConflict(int start1, int end1, int start2, int end2) {
        if (start2 < end1) return false;
        return true;
    }
//    private void cancelOrder(LocalDate localDate, int start, int end, List<Order> orderList) {
//        for (Order order: orderList) {
//            if (isSameDay(localDate, order) && order.getStart() == start && order.getEnd() == end && !order.isCanceled()) {
//                System.out.println("The order is canceled");
//                order.setCalculated(true);
//                return;
//            }
//        }
//        System.out.println("The order is not found");
//    }

    private boolean isSameDay(LocalDate localDate1, LocalDate localDate2) {
        return localDate1.isEqual(localDate2);
    }





    public boolean checkCourt(String name) {
        return containCourt(name);
    }

    private boolean containCourt(String name) {
        return courtSet.contains(name);
    }

}
