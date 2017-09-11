/**
 * Created by yb on 2017/9/9 0009.
 */
public interface BadmintonCourt {
    boolean book(Order order);

    boolean cancelBook(Order order);

    boolean isValidCourt(String name);

    void printSum();
}
