package views;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import constants.MainViewConstant;


import java.util.Scanner;

public class MainView {
    private static final Logger logger = LogManager.getLogger(MainView.class);
    public void displayMainView() {

        int choice;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("-------------------RESTAURANT-----------------------");
            System.out.println("1. Menu Management");
            System.out.println("2. Bill Management");
            System.out.println("3. Exit");
            System.out.println("Choice: ");
            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case MainViewConstant.MENU_MANAGEMENT:
                        new MenuView().displayMain();
                        break;
                    case MainViewConstant.BILL_MANAGEMENT:
                        new BillView().displayMain();
                        break;
                    case MainViewConstant.EXIT:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Wrong choice!Press any key to continue");
                        scanner.nextLine();
                }


            } catch (RuntimeException e) {
                logger.fatal("Wrong input choice - " + e);
                scanner.nextLine();
                System.out.println("Press any key to continue");
                scanner.nextLine();
            }
        }
    }
}
