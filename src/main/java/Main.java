import java.util.Scanner;
import util.Utils;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BadmintonCourt badmintonCourt = new BadmintonCourtImpl();
        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            if ("\n".equals(command)) {
                badmintonCourt.printSum();
                continue;
            }
            String[] cmdArr = command.split(" ");
            if (cmdArr.length < 4 || cmdArr.length > 5) {
                System.out.println("Invalid Command!");
                continue;
            }

//            boolean argsValid = badmintonCourt.checkArgs(cmdArr);
//            if (!argsValid) continue;
//            int start = Utils.parseHour
//

            Order order = Order.NewOrder(cmdArr);
            if (null == order) continue;
            if (cmdArr.length == 4) {
                badmintonCourt.book(order);
            } else {
                badmintonCourt.cancelBook(order);
            }
        }
    }
}
