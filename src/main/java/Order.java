import util.Utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by yb on 2017/9/9 0009.
 */
public class Order implements Comparable<Order> {
//    private BadmintonCourt badmintonCourt;
    private String user;

    private LocalDate localDate;
    private int start;
    private int end;
    private String court;

    private double price;
    private boolean isCalculated;
    private boolean isCanceled;

    static String invCmd = "> Error: the booking is invalid!";

    public Order() {}

    public static Order NewOrder(String[] orderString, BadmintonCourt badmintonCourt) {
        String[] timeSplit = orderString[2].split("~");
        if (timeSplit.length != 2) {
            System.out.println(invCmd);
            return null;
        }
        int start = Utils.parseHour(timeSplit[0]);
        int end = Utils.parseHour(timeSplit[1]);
        if (start < 0 || end < 0 || start >= end) {
//            System.out.println("> Error: the time of the booking is invalid");
            System.out.println(Utils.errorMsg);
            return null;

        }
        LocalDate localDate = Utils.parseLocalDate(orderString[1]);
        if (null == localDate) {
//            System.out.println("> Error: the date of the booking is invalid");
            System.out.println(Utils.errorMsg);

            return null;
        }

        if (!badmintonCourt.isValidCourt(orderString[3])) {
//            System.out.println("> Error: the court of the booking is invalid");
            System.out.println(Utils.errorMsg);

            return null;
        }

        return new Order(orderString[0], localDate, start, end, orderString[3]);

    }

    public Order(String user, LocalDate localDate, int start, int end, String court) {
        this.user = user;
        this.localDate = localDate;
        this.start = start;
        this.end = end;
        this.court = court;
    }

    public boolean isWeekend(LocalDate localDate1) {
        DayOfWeek dayOfWeek = localDate1.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    public void calculate() {
        if (isCalculated()) return;
        if (isWeekend(localDate)) {
            calculateForWeekends();
        } else {
            calculateForWeekdays();
        }
        setCalculated(true);
    }

    public double calculateForWeekends() {
        if (start < 12) {
            if (end <= 12) {
                setPrice(40 * (end - start));
            } else if (end <= 18) {
                setPrice(40 * (12 - start) + 50 * (end - 12));
            } else {
                setPrice(40 * (12 - start) + 50 * 6 + 60 * (end - 18));
            }
        } else if (start < 18) {
            if (end <= 18) {
                setPrice(50 * (end - start));
            } else {
                setPrice(50 * (18 - start) + 60 * (end - 18));
            }
        } else {
            setPrice((end - start) * 60);
        }
        return price;

    }

    public double calculateForWeekdays() {
        if (start < 12) {
            if (end <= 12) {
                setPrice((end - start) * 30);
            } else if (end <= 18) {
                setPrice(30 * (12 - start) + 50 * (end - 12));
            } else if (end <= 20) {
                setPrice(30 * (12 - start) + 50 * 6 + 80 * (end - 12));
            } else {
                setPrice(30 * (12 - start) + 50 * 6 + 80 * 2 + 60 * (end - 20));
            }
        } else if (start < 18) {
            if (end <= 18) {
                setPrice(50 * (end - start));
            } else if (end <= 20) {
                setPrice(50 * (18 - start) + 80 * (end - 18));
            } else {
                setPrice(50 * (18 - start) + 80 * 2 + 60 * (end - 20));
            }
        } else if (start < 20) {
            if (end <= 20) {
                setPrice(80 * (end - start));
            } else {
                setPrice(80 * (20 - start) + 60 * (end - 20));
            }
        } else {
            setPrice(60 * (end - start));
        }
        return price;
    }

    public int compareTo(Order order) {
        if (localDate.getYear() != order.getLocalDate().getYear()) {
            return localDate.getYear() - order.getLocalDate().getYear();
        }
        if (localDate.getMonth() != order.getLocalDate().getMonth()) {
            return localDate.getMonthValue() - order.getLocalDate().getMonthValue();
        }
        if (localDate.getDayOfMonth() != order.getLocalDate().getDayOfMonth()) {
            return localDate.getDayOfMonth() - order.getLocalDate().getDayOfMonth();
        }
       return start - order.getStart();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getCourt() {
        return court;
    }

    public void setCourt(String court) {
        this.court = court;
    }

    public double getPrice() {
        if (!isCalculated) {
            calculate();
        }
        if (!isCanceled) {
            return price;
        }
        if (isWeekend(localDate)) {
            return price / 4.0;
        } else {
            return price / 2.0;
        }
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isCalculated() {
        return isCalculated;
    }

    public void setCalculated(boolean calculated) {
        isCalculated = calculated;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }
}
