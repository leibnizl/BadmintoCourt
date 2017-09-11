
import org.junit.Assert;
import org.junit.Test;

public class TestBadmintonCourtImpl {

    @Test
    public void testBook() {
        BadmintonCourt badmintonCourt = new BadmintonCourtImpl();
        Order order = Order.NewOrder("U002 2017-08-01 19:00~22:00 A".split(" "), badmintonCourt);
        Assert.assertTrue(badmintonCourt.book(order));

        order = Order.NewOrder("U002 2017-08-01 19:00~22:00 A".split(" "), badmintonCourt);
        Assert.assertFalse(badmintonCourt.book(order));

        order = Order.NewOrder("U002 2017-08-01 19:00~22:00 B".split(" "), badmintonCourt);
        Assert.assertTrue(badmintonCourt.book(order));
    }


    @Test
    public void testCancelBook() {
        BadmintonCourt badmintonCourt = new BadmintonCourtImpl();
        Order order = Order.NewOrder("U002 2017-08-01 19:00~22:00 A".split(" "), badmintonCourt);
        Assert.assertTrue(badmintonCourt.book(order));

        order = Order.NewOrder("U002 2017-08-01 19:00~22:00 A C".split(" "), badmintonCourt);
        Assert.assertTrue(badmintonCourt.cancelBook(order));

        order = Order.NewOrder("U002 2017-08-01 19:00~22:00 A C".split(" "), badmintonCourt);
        Assert.assertFalse(badmintonCourt.cancelBook(order));
    }

    @Test
    public void testIsValidCourt() {
        BadmintonCourt badmintonCourt = new BadmintonCourtImpl();

        Assert.assertTrue(badmintonCourt.isValidCourt("A"));
        Assert.assertFalse(badmintonCourt.isValidCourt("E"));
        Assert.assertFalse(badmintonCourt.isValidCourt("fdE"));
        Assert.assertFalse(badmintonCourt.isValidCourt(""));

    }

    @Test
    public void testIsTimeConflict() {
        BadmintonCourtImpl badmintonCourt = new BadmintonCourtImpl();

        Order order1 = new Order();
        Order order2 = new Order();
        Order order3 = new Order();
        Order order4 = new Order();

        order1.setStart(12);
        order1.setEnd(15);

        order2.setStart(11);
        order2.setEnd(12);

        order3.setStart(14);
        order3.setEnd(18);

        order4.setStart(15);
        order4.setEnd(17);

        Assert.assertFalse(badmintonCourt.isTimeConflict(order1, order2));
        Assert.assertTrue(badmintonCourt.isTimeConflict(order1, order3));
        Assert.assertFalse(badmintonCourt.isTimeConflict(order2, order3));
        Assert.assertTrue(badmintonCourt.isTimeConflict(order3, order4));
    }


}
