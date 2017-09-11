import util.Utils;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by yb on 2017/9/9 0009.
 */
public class BadmintonCourtImpl implements BadmintonCourt {

    Map<String, List<Order>> orders = new TreeMap<>();
    Set<String> courtSet = new HashSet<>();
    {
        courtSet.add("A");
        courtSet.add("B");
        courtSet.add("C");
        courtSet.add("D");
    }

    public boolean book(Order order ) {
        List<Order> orderList = orders.get(order.getCourt());
        if (null == orderList) orderList = new ArrayList<>();

        for (Order order1: orderList) {
            if (!order1.isCanceled() && isSameDay(order1.getLocalDate(), order.getLocalDate()) && isTimeConflict(order1, order)) {
//                System.out.println("> Error: the time of booking is conflicted!");
                System.out.println("> Error: the booking conflicts with existing bookings!");
                return false;
            }
        }
        orderList.add(order);
        orders.put(order.getCourt(), orderList);
        System.out.println(Utils.successMsg);
        return true;
    }

    public boolean cancelBook(Order order) {
        List<Order> orderList = orders.get(order.getCourt());
        if (null == orderList) orderList = new ArrayList<>();
        boolean flag = false;
        for (Order order1: orderList) {
            if (isSameDay(order1.getLocalDate(), order.getLocalDate())
                    && order1.getStart() == order.getStart()
                    && order1.getEnd() == order.getEnd()
                    && order1.getUser().equals(order.getUser())) {
                if (!order1.isCanceled()) {
                    order1.setCanceled(true);
                    flag = true;
                    System.out.println(Utils.successMsg);
                    break;
                }
            }
        }
        if (!flag) {
            System.out.println("Error: the booking being cancelled does not exist!");
            return false;
        }
        return true;
    }

    public void printSum() {
        System.out.println("> 收入汇总");
        System.out.println("> ---");
        double sum = 0.0;
        int t = 0;
        for (String court: orders.keySet()) {
            if (t > 0) System.out.println(">");
            t++;
            List<Order> orderList = orders.get(court);
            System.out.println("> 场地:" + court);
            if (null == orderList || orderList.isEmpty()) {

                System.out.println("> 小计: 0元");
            } else {
                Collections.sort(orderList);
                double cnt = 0.0;
                for (Order order: orderList) {
                    System.out.print("> " + order.getLocalDate().toString() +
                    " " + order.getStart() + ":00~" + order.getEnd() + ":00 ");
                    double money = order.getPrice();
                    cnt += money;
                    if (order.isCanceled()) System.out.print("违约金 ");
                    System.out.println(money + "元");
                }
                sum += cnt;
                System.out.println("> 小计: " + cnt + "元");
            }

        }
        System.out.println("> ---");
        System.out.println("> 总计: " + sum + "元");
    }

    public boolean isValidCourt(String name) {
        return courtSet.contains(name);
    }

    public boolean isTimeConflict(Order order1, Order order2) {
        if (order1.getStart() < order2.getStart()) {
            return order1.getEnd() > order2.getStart();
        } else {
            return order2.getEnd() > order1.getStart();
        }
    }


    public boolean isSameDay(LocalDate localDate1, LocalDate localDate2) {
        return localDate1.isEqual(localDate2);
    }



}
