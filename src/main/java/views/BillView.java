package views;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.BillViewConstant;

import java.util.Scanner;

public class BillView {
    private static final Logger logger = LogManager.getLogger(BillView.class);
    private static final Scanner scanner = new Scanner(System.in);
    public void displayMain(){

        int choice;
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        while (flag) {
            System.out.println("---------Bill Management-------------------------");
            System.out.println("1. Add a bill");
            System.out.println("2. Delete a bill");
            System.out.println("3. Update a bill");
            System.out.println("4. Display a bill");
            System.out.println("5. Display bill's list");
            System.out.println("6. Back");
            System.out.println("7. Exit");
            System.out.println("Choice: ");
            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case BillViewConstant.ADD_BILL:

                        break;
                    case BillViewConstant.DELETE_BILL:


                    case BillViewConstant.BACK_MAIN_VIEW:
                        new MainView().displayMainView();
                    case BillViewConstant.EXIT:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Wrong choice!!Select again!!Press any key to continue");
                        scanner.nextLine();System.out.println("Wrong choice!!Select again!!");
                }

            } catch (RuntimeException e) {
                logger.fatal("Wrong input choice - " + e);
                scanner.nextLine();
                System.out.println("Select again.Press any key to continue");
                scanner.nextLine();
            }
        }
    }
}
