import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class TestOrder {



    @Test
    public void testIsWeekend() {
        Order order = new Order();

        Assert.assertFalse(order.isWeekend(LocalDate.of(2017, 9,12)));
        Assert.assertTrue(order.isWeekend(LocalDate.of(2017, 9, 30)));
    }
    @Test
    public void testCompartTo() {
        Order order1 = new Order();
        Order order2 = new Order();
        Order order3 = new Order();
        Order order4 = new Order();

        order1.setStart(11);
        order1.setEnd(15);
        order1.setLocalDate(LocalDate.of(2017, 9, 11));

        order2.setStart(12);
        order2.setEnd(15);
        order2.setLocalDate(LocalDate.of(2017, 9, 11));

        order3.setStart(11);
        order3.setEnd(15);
        order3.setLocalDate(LocalDate.of(2017, 9, 10));

        order4.setStart(11);
        order4.setEnd(15);
        order4.setLocalDate(LocalDate.of(2017, 9, 11));

        Assert.assertTrue(order1.compareTo(order2) < 0);
        Assert.assertTrue(order1.compareTo(order3) > 0);
        Assert.assertEquals(order1.compareTo(order4), 0);


    }

    @Test
    public void testNewOrder() {
        BadmintonCourt badmintonCourt = new BadmintonCourtImpl();
        String cmd = "U001 2016-06-02 22:00~22:00 A";
        Assert.assertNull(Order.NewOrder(cmd.split(" "), badmintonCourt));

        cmd = "U001 2017-02-29 22:00~22:00 A";
        Assert.assertNull(Order.NewOrder(cmd.split(" "), badmintonCourt));

        cmd = "U001 2017-02-28 21:00~22:00 E";
        Assert.assertNull(Order.NewOrder(cmd.split(" "), badmintonCourt));

        cmd = "U001 2017-02-28 21:00~22:00 B";
        Assert.assertNotNull(Order.NewOrder(cmd.split(" "), badmintonCourt));

    }

    @Test
    public void testCalculateForWeekends() {
        Order order = new Order();
        order.setLocalDate(LocalDate.of(2017, 9, 10));
        order.setStart(18);
        order.setEnd(22);
        Assert.assertTrue(Math.abs(order.calculateForWeekends() - 240) < 0.00000001);


    }

    @Test
    public void testCalculateForWeekdays() {
        Order order = new Order();
        order.setLocalDate(LocalDate.of(2017, 9, 12));
        order.setStart(18);
        order.setEnd(22);
        Assert.assertTrue(Math.abs(order.calculateForWeekdays() - 280) < 0.00000001);
    }

}
