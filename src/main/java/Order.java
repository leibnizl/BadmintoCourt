import util.Utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by yb on 2017/9/9 0009.
 */
public class Order implements CalculatePrice, Comparable<Order> {
    private String user;

    private LocalDate localDate;
    private int start;
    private int end;
    private Court court;

    private double price;
    private boolean isCalculated;
    private boolean isCanceled;

    static String invCmd = "Invalid Command!";

    public static Order NewOrder(String[] orderString) {
        String[] timeSplit = orderString[2].split("~");
        if (timeSplit.length != 2) {
            System.out.println(invCmd);
            return null;
        }
        int start = Utils.parseHour(timeSplit[0]);
        int end = Utils.parseHour(timeSplit[1]);
        if (start < 0 || end < 0) {
            System.out.println("Time Invalid");
            return null;
        }
        LocalDate localDate = Utils.parseLocalDate(orderString[1]);
        if (null == localDate) {
            System.out.println("Date Invalid");
            return null;
        }
        Court court = Court.getCourt(orderString[2]);
        if (null == court) {
            System.out.println("Court Invalid");
            return null;
        }

        return new Order(orderString[0], localDate, start, end, court);

    }

    public Order(String user, LocalDate localDate, int start, int end, Court court) {
        this.user = user;
        this.localDate = localDate;
        this.start = start;
        this.end = end;
        this.court = court;
    }

    private boolean isWeekend(LocalDate localDate1) {
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

    private void calculateForWeekends() {
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

    }

    private void calculateForWeekdays() {
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
    }

    public int compareTo(Order order) {
        if (localDate.getYear() != order.getLocalDate().getYear()) {
            return localDate.getYear() - order.getLocalDate().getYear();
        }
        if (localDate.getMonth() != order.getLocalDate().getMonth()) {
            return localDate.getMonthValue() - order.getLocalDate().getMonthValue();
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

    public Court getCourt() {
        return court;
    }

    public void setCourt(Court court) {
        this.court = court;
    }

    public double getPrice() {
        return price;
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
