import java.util.Scanner;
import util.Utils;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BadmintonCourt badmintonCourt = new BadmintonCourtImpl();
        while (true) {
            String command = scanner.nextLine();
            if (command.length() == 0) {
                badmintonCourt.printSum();
                break;
            }
            String[] cmdArr = command.split(" ");
            if (cmdArr.length < 4 || cmdArr.length > 5) {
                System.out.println(Utils.errorMsg);
                continue;
            }

            Order order = Order.NewOrder(cmdArr, badmintonCourt);
            if (null == order) continue;
            if (cmdArr.length == 4) {
                badmintonCourt.book(order);
            } else {
                badmintonCourt.cancelBook(order);
            }
        }
    }
}
