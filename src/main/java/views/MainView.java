package views;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.MainViewConstant;
import utils.Message;


import java.util.Scanner;

public class MainView {
    private static final Logger logger = LogManager.getLogger(MainView.class);

    public void displayMainView() {

        int choice;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(Message.MAIN_VIEW_MENU);
            System.out.println(Message.INPUT_CHOICE);
            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case MainViewConstant.MENU_MANAGEMENT_INDEX:
                        new MenuView().displayMain();
                        break;
                    case MainViewConstant.BILL_MANAGEMENT_INDEX:
                        new BillView().displayMain();
                        break;
                    case MainViewConstant.EXIT_INDEX:
                        System.exit(0);
                        break;
                    default:
                        System.out.println(Message.CHOICE_NOT_EXISTED + "." + Message.PRESS_TO_CONTINUE);
                        scanner.nextLine();
                }


            } catch (RuntimeException e) {
                logger.fatal("displayMainView() - " + e);
                scanner.nextLine();
                System.out.println(Message.PRESS_TO_CONTINUE);
                scanner.nextLine();
            }
        }
    }
}
